from sqlalchemy import Column, Integer, String, Float, DateTime, Boolean
from database import Base
from datetime import datetime

class Vendor(Base):
    __tablename__ = 'vendors'
    id = Column(Integer, primary_key=True)
    name = Column(String(100))
    code = Column(String(50), unique=True)
    email = Column(String(100))
    phone = Column(String(20))
    status = Column(String(20), default='ACTIVE')  # ACTIVE, SUSPENDED, INACTIVE
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

class VendorProduct(Base):
    __tablename__ = 'vendor_products'
    id = Column(Integer, primary_key=True)
    vendor_id = Column(Integer)
    product_id = Column(Integer)
    price = Column(Float)
    is_active = Column(Boolean, default=True)
    created_at = Column(DateTime, default=datetime.utcnow)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)
