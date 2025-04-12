from sqlalchemy import Column, Integer, String, Float, DateTime, ForeignKey
from database import Base

class Order(Base):
    __tablename__ = 'orders'
    id = Column(Integer, primary_key=True)
    customer_code = Column(String(50))
    item = Column(String(100))
    amount = Column(Float)
    created_at = Column(DateTime)
