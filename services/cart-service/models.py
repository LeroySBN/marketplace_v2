from sqlalchemy import Column, Integer, String, Float, DateTime, ForeignKey, JSON
from database import Base
from datetime import datetime

class Cart(Base):
    __tablename__ = 'carts'
    id = Column(Integer, primary_key=True)
    customer_id = Column(String(50))
    status = Column(String(20), default='PENDING')  # PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    total_amount = Column(Float)
    shipping_address = Column(JSON)
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

class CartItem(Base):
    __tablename__ = 'cart_items'
    id = Column(Integer, primary_key=True)
    cart_id = Column(Integer, ForeignKey('carts.id'))
    product_id = Column(Integer)
    vendor_id = Column(Integer)
    quantity = Column(Integer)
    price = Column(Float)
    product_data = Column(JSON)  # Snapshot of product data at time of adding
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)
