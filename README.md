# A marketplace Microservices

A modern microservices application built with a K8s cluster.

## Features
- Customer and Order management via GraphQL API
- OpenID Connect authentication
- SMS notifications via Africa's Talking
- Email notifications via SendGrid
- Message queue with RabbitMQ
- API Gateway with Kong
- PostgreSQL database
- Redis for caching
- Kubernetes deployment with Helm
- CI/CD with Jenkins
- Monitoring with Prometheus/Grafana
- Logging with ELK Stack

## Architecture
- Event-driven microservices
- Scalable and resilient design
- Automated CI/CD pipeline
- Comprehensive monitoring and logging

## Services
- Customer Service (Port 5000)
- Order Service (Port 5001)
- Notification Service (Port 5002)
- Inventory Service (Port 5003)
- Vendor Service (Port 5004)
- Cart Service (Port 5005)

## Prerequisites
- Docker & Docker Compose
- Kubernetes cluster
- Helm
- Jenkins
- Python 3.9+
- PostgreSQL
- RabbitMQ
- Kong API Gateway

## Quick Start
1. Clone the repository
2. Create a `.env` file with required environment variables
3. Deploy using Jenkins pipeline or manually:
   ```bash
   # Manual deployment
   helm upgrade --install ecommerce ./k8s/helm/microservices
   ```

## Development
```bash
make dev     # Start development environment
make test    # Run tests
make deploy  # Deploy to Kubernetes
```

## CI/CD Pipeline
The project uses Jenkins for continuous integration and deployment:
- Automated testing
- Code quality checks with SonarQube
- Docker image building and pushing
- Automated deployment to dev/staging
- Manual approval for production
- Slack notifications

See [Jenkins Setup Guide](docs/jenkins-setup.md) for detailed configuration.

## Documentation
- [System Flow](docs/system-flow.md)
- [Jenkins Setup](docs/jenkins-setup.md)
- [API Documentation](docs/api.md)

## Monitoring
- Prometheus for metrics
- Grafana for visualization
- ELK Stack for log aggregation
- Jenkins build monitoring

## License
MIT
