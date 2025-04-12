# E-commerce System Flow

## Event-Driven Architecture Flow

1. **Vendor Flow**:
   ```
   Vendor Service -> Inventory Service
   [vendor.product.created] -> [inventory.create]
   [vendor.product.updated] -> [inventory.update]
   ```

2. **Shopping Flow**:
   ```
   Cart Service -> Inventory Service
   [cart.item.added] -> [inventory.check]
   [cart.item.removed] -> [inventory.release]
   ```

3. **Checkout Flow**:
   ```
   Cart Service -> Order Service -> Inventory Service -> Notification Service
   [cart.checkout] -> [order.create] -> [inventory.update] -> [notification.order]
   ```

4. **Inventory Alerts**:
   ```
   Inventory Service -> Notification Service
   [inventory.low_stock] -> [notification.vendor]
   ```

## Service Responsibilities

### Customer Service (Port 5000)
- Customer management
- Authentication/Authorization
- Customer profile

### Order Service (Port 5001)
- Order processing
- Order status management
- Order history

### Notification Service (Port 5002)
- Email notifications
- SMS notifications
- Vendor alerts

### Inventory Service (Port 5003)
- Stock management
- Inventory tracking
- Low stock alerts

### Vendor Service (Port 5004)
- Vendor management
- Product management
- Pricing

### Cart Service (Port 5005)
- Shopping cart management
- Price calculation
- Checkout process

## Event Types

1. **Customer Events**
   - customer.created
   - customer.updated

2. **Vendor Events**
   - vendor.created
   - vendor.product.created
   - vendor.product.updated

3. **Cart Events**
   - cart.created
   - cart.item.added
   - cart.item.removed
   - cart.checkout

4. **Order Events**
   - order.created
   - order.updated
   - order.shipped

5. **Inventory Events**
   - inventory.created
   - inventory.updated
   - inventory.low_stock

## Data Flow Examples

1. **Adding Product to Cart**:
   ```
   1. Customer adds product -> Cart Service
   2. Cart Service checks inventory -> Inventory Service
   3. If available:
      - Cart Service reserves inventory
      - Inventory Service updates reserved quantity
   4. If unavailable:
      - Return error to customer
   ```

2. **Checkout Process**:
   ```
   1. Customer initiates checkout -> Cart Service
   2. Cart Service validates inventory -> Inventory Service
   3. Cart Service creates order -> Order Service
   4. Order Service processes payment
   5. Order Service confirms inventory update -> Inventory Service
   6. Order Service triggers notifications -> Notification Service
   7. Notification Service sends email and SMS
   ```

3. **Low Stock Alert**:
   ```
   1. Inventory update drops below threshold -> Inventory Service
   2. Inventory Service sends alert -> Notification Service
   3. Notification Service emails vendor
   ```

## Scalability Features

1. **Service Replication**
   - All services are replicated for high availability
   - Load balancing via Kubernetes

2. **Data Storage**
   - PostgreSQL for persistent data
   - Redis for caching and session management
   - RabbitMQ for message queuing

3. **Monitoring**
   - Prometheus for metrics
   - Grafana for visualization
   - ELK stack for log aggregation

4. **API Gateway**
   - Kong for routing and rate limiting
   - Authentication/Authorization
   - Request throttling
