# K8s Flask Microservices

A modern microservices application built with Flask, Docker, Kubernetes, Kong API Gateway, and RabbitMQ.

## Features
- Customer and Order management via GraphQL API
- OpenID Connect authentication
- SMS notifications via Africa's Talking
- Message queue with RabbitMQ
- API Gateway with Kong
- PostgreSQL database
- Kubernetes deployment with Helm
- CI/CD pipeline
- Unit tests with coverage

## Prerequisites
- Docker & Docker Compose
- Kubernetes cluster
- Helm
- Python 3.9+
- PostgreSQL
- RabbitMQ
- Kong API Gateway

## Quick Start
1. Clone the repository
2. Create a `.env` file with required environment variables
3. Run `make deploy` to deploy to Kubernetes

## Project Structure
```
.
├── services/
│   ├── customer-service/     # Customer management service
│   ├── order-service/        # Order management service
│   └── notification-service/ # SMS notification service
├── k8s/                      # Kubernetes manifests
│   └── helm/                 # Helm charts
├── tests/                    # Unit tests
├── docker/                   # Dockerfile and compose files
└── docs/                     # Documentation
```

## Development
```bash
make dev     # Start development environment
make test    # Run tests
make deploy  # Deploy to Kubernetes
```

## License
MIT
