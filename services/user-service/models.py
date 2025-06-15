from sqlalchemy import Column, Integer, String, DateTime
from database import Base

class User(Base):
    __tablename__ = 'users'
    id = Column(Integer, primary_key=True)
    code = Column(String(50), unique=True)
    name = Column(String(100))
    phone = Column(String(20))
    created_at = Column(DateTime)

