# Markets

An event-driven microservices backend application using Go to serve a GraphQL API, and RabbitMQ for inter-service communication, with a Kotlin Multiplatform mobile frontend.

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
- Inventory Service (Port 5000)
- User Service (Port 5001)
- Auth Service (Port 5002)
- Notification Service (Port 5003)
- Cart Service (Port 5004)

## Prerequisites
- Ubuntu server 22.04
- [Kubernetes](https://kubernetes.io/docs/home/) cluster (kubectl, kubeadm, kubelet)
- containerd for CRI
- [Calico](https://docs.tigera.io/calico/latest/about/) CNI for networking
- [Helm](https://helm.sh/)
- [Jenkins](https://www.jenkins.io/)
- Golang v1.21

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

## Getting started
1. Clone the repository
2. 

## License
MIT
