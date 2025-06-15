import os
from flask import Flask, request, jsonify
from flask_graphql import GraphQLView
import pika
import json
from database import db_session, init_db
from models import Order
from schema import schema
from auth import requires_auth

app = Flask(__name__)
app.debug = True

def publish_order(order_data):
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host=os.getenv('RABBITMQ_HOST', 'localhost'))
    )
    channel = connection.channel()
    channel.queue_declare(queue='cart_notifications')
    
    channel.basic_publish(
        exchange='',
        routing_key='cart_notifications',
        body=json.dumps(order_data)
    )
    connection.close()

@app.route('/health')
def health_check():
    return jsonify({"status": "healthy"})

app.add_url_rule(
    '/graphql',
    view_func=requires_auth(GraphQLView.as_view(
        'graphql',
        schema=schema,
        graphiql=True
    ))
)

@app.teardown_appcontext
def shutdown_session(exception=None):
    db_session.remove()

if __name__ == '__main__':
    init_db()
    app.run(host='0.0.0.0', port=5001)
