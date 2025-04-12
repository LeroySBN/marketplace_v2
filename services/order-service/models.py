from sqlalchemy import Column, Integer, String, Float, DateTime, JSON, ForeignKey
from database import Base
from datetime import datetime

class Order(Base):
    __tablename__ = 'orders'
    id = Column(Integer, primary_key=True)
    customer_code = Column(String(50))
    cart_id = Column(Integer)
    status = Column(String(20), default='PENDING')  # PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    total_amount = Column(Float)
    shipping_address = Column(JSON)
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

class OrderItem(Base):
    __tablename__ = 'order_items'
    id = Column(Integer, primary_key=True)
    order_id = Column(Integer, ForeignKey('orders.id'))
    product_id = Column(Integer)
    vendor_id = Column(Integer)
    quantity = Column(Integer)
    price = Column(Float)
    product_data = Column(JSON)  # Snapshot of product data at time of order
    status = Column(String(20), default='PENDING')  # PENDING, CONFIRMED, SHIPPED
    created_at = Column(DateTime, default=datetime.utcnow)
