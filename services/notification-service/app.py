import os
import json
import pika
import africastalking
from flask import Flask
import requests
from sendgrid import SendGridAPIClient
from sendgrid.helpers.mail import Mail

app = Flask(__name__)

# Initialize Africa's Talking
username = os.getenv('AT_USERNAME')
api_key = os.getenv('AT_API_KEY')
africastalking.initialize(username, api_key)
sms = africastalking.SMS

# Initialize SendGrid
sendgrid_client = SendGridAPIClient(os.getenv('SENDGRID_API_KEY'))

def send_email(to_email, subject, content):
    message = Mail(
        from_email=os.getenv('FROM_EMAIL'),
        to_emails=to_email,
        subject=subject,
        html_content=content
    )
    try:
        response = sendgrid_client.send(message)
        print(f"Email sent: {response.status_code}")
        return True
    except Exception as e:
        print(f"Error sending email: {str(e)}")
        return False

def send_sms(phone, message):
    try:
        response = sms.send(message, [phone])
        print(f"SMS sent: {response}")
        return True
    except Exception as e:
        print(f"Error sending SMS: {str(e)}")
        return False

def get_customer_details(customer_code):
    customer_service_url = os.getenv('CUSTOMER_SERVICE_URL', 'http://localhost:5000')
    response = requests.post(
        f"{customer_service_url}/graphql",
        json={
            'query': '''
                query GetCustomer($code: String!) {
                    customer(code: $code) {
                        email
                        phone
                    }
                }
            ''',
            'variables': {
                'code': customer_code
            }
        }
    )
    data = response.json()
    return data['data']['customer']

def handle_order_notification(order_data):
    customer = get_customer_details(order_data['customer_code'])
    
    # Send email notification
    email_content = f"""
    <h2>Order Confirmation</h2>
    <p>Your order has been successfully placed!</p>
    <h3>Order Details:</h3>
    <ul>
        {''.join([f"<li>{item['name']} - Quantity: {item['quantity']} - Price: ${item['price']}</li>" for item in order_data['items']])}
    </ul>
    <p>Total Amount: ${order_data['total_amount']}</p>
    <p>Your items will be shipped soon.</p>
    """
    send_email(customer['email'], "Order Confirmation", email_content)
    
    # Send SMS notification
    sms_message = f"Your order of ${order_data['total_amount']} has been confirmed. You will receive shipping details soon."
    send_sms(customer['phone'], sms_message)

def handle_inventory_notification(inventory_data):
    if inventory_data['quantity'] <= inventory_data['low_stock_threshold']:
        # Alert vendor about low stock
        vendor_service_url = os.getenv('VENDOR_SERVICE_URL', 'http://localhost:5003')
        vendor_response = requests.get(f"{vendor_service_url}/vendors/{inventory_data['vendor_id']}")
        vendor = vendor_response.json()
        
        email_content = f"""
        <h2>Low Stock Alert</h2>
        <p>Product {inventory_data['product_name']} (SKU: {inventory_data['sku']}) is running low.</p>
        <p>Current quantity: {inventory_data['quantity']}</p>
        <p>Threshold: {inventory_data['low_stock_threshold']}</p>
        """
        send_email(vendor['email'], "Low Stock Alert", email_content)

def callback(ch, method, properties, body):
    data = json.loads(body)
    event_type = method.routing_key
    
    if event_type == 'order.created':
        handle_order_notification(data)
    elif event_type == 'inventory.updated':
        handle_inventory_notification(data)

def start_consuming():
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host=os.getenv('RABBITMQ_HOST', 'localhost'))
    )
    channel = connection.channel()
    
    # Declare exchanges
    channel.exchange_declare(exchange='notifications', exchange_type='topic')
    
    # Declare queues
    channel.queue_declare(queue='order_notifications')
    channel.queue_declare(queue='inventory_notifications')
    
    # Bind queues to exchange
    channel.queue_bind(exchange='notifications', queue='order_notifications', routing_key='order.*')
    channel.queue_bind(exchange='notifications', queue='inventory_notifications', routing_key='inventory.*')
    
    # Start consuming from both queues
    channel.basic_consume(queue='order_notifications', on_message_callback=callback, auto_ack=True)
    channel.basic_consume(queue='inventory_notifications', on_message_callback=callback, auto_ack=True)
    
    print('Notification service is waiting for messages...')
    channel.start_consuming()

if __name__ == '__main__':
    from threading import Thread
    consumer_thread = Thread(target=start_consuming)
    consumer_thread.start()
    app.run(host='0.0.0.0', port=5002)
