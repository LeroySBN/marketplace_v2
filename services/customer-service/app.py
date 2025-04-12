import os
from flask import Flask, request, jsonify
from flask_graphql import GraphQLView
import graphene
from database import db_session, init_db
from models import Customer
from schema import schema
from auth import requires_auth

app = Flask(__name__)
app.debug = True

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
    app.run(host='0.0.0.0', port=5000)
