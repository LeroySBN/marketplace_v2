from sqlalchemy import Column, Integer, String, Float, DateTime, ForeignKey, Boolean
from sqlalchemy.orm import relationship
from database import Base
from datetime import datetime

class Product(Base):
    __tablename__ = 'products'
    id = Column(Integer, primary_key=True)
    vendor_id = Column(Integer, ForeignKey('vendors.id'))
    name = Column(String(100))
    description = Column(String(500))
    sku = Column(String(50), unique=True)
    price = Column(Float)
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)
    
    inventory = relationship("Inventory", back_populates="product", uselist=False)

class Inventory(Base):
    __tablename__ = 'inventory'
    id = Column(Integer, primary_key=True)
    product_id = Column(Integer, ForeignKey('products.id'))
    quantity = Column(Integer, default=0)
    reserved_quantity = Column(Integer, default=0)
    low_stock_threshold = Column(Integer, default=10)
    is_active = Column(Boolean, default=True)
    last_restock_date = Column(DateTime)
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)
    
    product = relationship("Product", back_populates="inventory")

class InventoryTransaction(Base):
    __tablename__ = 'inventory_transactions'
    id = Column(Integer, primary_key=True)
    inventory_id = Column(Integer, ForeignKey('inventory.id'))
    transaction_type = Column(String(20))  # RESTOCK, RESERVE, RELEASE, CHECKOUT
    quantity = Column(Integer)
    reference_id = Column(String(100))  # Order ID or Restock reference
    created_at = Column(DateTime, default=datetime.utcnow)
