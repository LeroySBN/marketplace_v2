#!/bin/bash
# This script builds all Docker images

services=(
  "user-service"
  "auth-service"
  "notification-service"
  "inventory-service"
  "cart-service"
)

for service in "${services[@]}"; do
    echo "Building $service..."
    docker build -t $service:latest ./services/$service/
done