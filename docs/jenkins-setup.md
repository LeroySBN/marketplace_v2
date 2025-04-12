# Jenkins Setup Guide

## Prerequisites
- Kubernetes cluster with Helm installed
- Docker registry credentials
- Kubernetes config
- SonarQube instance
- Slack webhook (optional)

## Initial Setup

1. **Deploy Jenkins using Helm**
```bash
helm upgrade --install ecommerce ./k8s/helm/microservices \
    --namespace ecommerce \
    --create-namespace \
    --set jenkins.enabled=true
```

2. **Get the Jenkins admin password**
```bash
kubectl get secret -n ecommerce jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode
```

## Required Jenkins Plugins
- Kubernetes
- Pipeline
- Git
- Docker Pipeline
- Kubernetes CLI
- Credentials Binding
- SonarQube Scanner
- Slack Notification
- Cobertura
- JUnit

## Required Credentials

1. **Docker Registry**
   - Kind: Username with password
   - ID: docker-creds
   - Description: Docker registry credentials

2. **Kubernetes Config**
   - Kind: Secret file
   - ID: kube-config
   - Description: Kubernetes config file

3. **SonarQube**
   - Kind: Secret text
   - ID: sonar-token
   - Description: SonarQube authentication token

4. **Slack**
   - Kind: Secret text
   - ID: slack-webhook
   - Description: Slack webhook URL

## Pipeline Configuration

1. **Create New Pipeline**
   - Click "New Item"
   - Select "Pipeline"
   - Name it "ecommerce-pipeline"

2. **Configure Pipeline**
   - Select "Pipeline script from SCM"
   - Set SCM to Git
   - Enter repository URL
   - Set branch specifier to */main
   - Set Script Path to "Jenkinsfile"

## Environment Variables

Configure the following environment variables in Jenkins:
- DOCKER_REGISTRY: Your Docker registry URL
- SONAR_HOST_URL: Your SonarQube instance URL
- SLACK_CHANNEL: Your Slack channel for notifications

## Branch Strategy

The Jenkinsfile supports three environments:
- develop: Automatic deployment to development
- staging: Automatic deployment to staging
- main: Manual approval required for production deployment

## Monitoring Jenkins

1. **Resource Usage**
   - Monitor via Prometheus/Grafana
   - Watch Jenkins agent resource consumption

2. **Backup Configuration**
   - Daily backups configured via Kubernetes CronJob
   - Backup location: PVC in the cluster

## Security Considerations

1. **Authentication**
   - Enable Jenkins security
   - Use LDAP or OAuth for authentication
   - Configure role-based access control

2. **Agents**
   - Use Pod Security Policies
   - Limit agent permissions
   - Regular security updates

## Troubleshooting

1. **Pipeline Issues**
   - Check Jenkins logs
   - Verify credentials are correctly configured
   - Ensure Kubernetes permissions are correct

2. **Agent Issues**
   - Check agent pod logs
   - Verify resource limits
   - Check network connectivity

3. **Common Solutions**
   - Restart Jenkins pod if UI becomes unresponsive
   - Clear workspace if build fails due to disk space
   - Update plugins if experiencing compatibility issues
