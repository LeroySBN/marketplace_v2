FROM python:3.9-slim

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY services/ ./services/

ENV PYTHONPATH=/app

CMD ["python", "services/customer-service/app.py"]
