import os
import json
import pika
import africastalking
from flask import Flask
import requests

app = Flask(__name__)

# Initialize Africa's Talking
username = os.getenv('AT_USERNAME')
api_key = os.getenv('AT_API_KEY')
africastalking.initialize(username, api_key)
sms = africastalking.SMS

def get_customer_phone(customer_code):
    customer_service_url = os.getenv('CUSTOMER_SERVICE_URL', 'http://localhost:5000')
    response = requests.post(
        f"{customer_service_url}/graphql",
        json={
            'query': '''
                query GetCustomer($code: String!) {
                    customer(code: $code) {
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
    return data['data']['customer']['phone']

def callback(ch, method, properties, body):
    order_data = json.loads(body)
    customer_code = order_data['customer_code']
    phone = get_customer_phone(customer_code)
    
    message = f"New order received for {order_data['item']} worth {order_data['amount']}"
    
    try:
        response = sms.send(message, [phone])
        print(f"SMS sent: {response}")
    except Exception as e:
        print(f"Error sending SMS: {str(e)}")

def start_consuming():
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host=os.getenv('RABBITMQ_HOST', 'localhost'))
    )
    channel = connection.channel()
    channel.queue_declare(queue='order_notifications')
    channel.basic_consume(
        queue='order_notifications',
        on_message_callback=callback,
        auto_ack=True
    )
    print('Notification service is waiting for messages...')
    channel.start_consuming()

if __name__ == '__main__':
    from threading import Thread
    consumer_thread = Thread(target=start_consuming)
    consumer_thread.start()
    app.run(host='0.0.0.0', port=5002)
