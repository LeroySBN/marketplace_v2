"""model.py
This module defines a user class
"""
from uuid import uuid4
from datetime import datetime

time_format = '%Y-%m-%d %H:%M:%S'


class User:
    """Defines a user with attributes
    """
    username = ''
    email = ''
    phone = ''
    password = ''
    is_active = False

    def __init__(self):
        """Initializes a new user
        """
        self.id = str(uuid4())
        self.created_at = self.updated_at = datetime.utcnow()

    def save(self):
        """Updates updated_at with current time when instance is changed
        """
        self.updated_at = datetime.utcnow()
