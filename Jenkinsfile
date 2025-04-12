pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'your-registry.com'
        DOCKER_CREDENTIALS = credentials('docker-creds')
        KUBE_CONFIG = credentials('kube-config')
        HELM_RELEASE_NAME = 'ecommerce'
        NAMESPACE = 'ecommerce'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                    python -m pip install --upgrade pip
                    pip install -r requirements.txt
                    pytest tests/ --cov=services --cov-report=xml -v
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        sonar-scanner \
                            -Dsonar.projectKey=ecommerce \
                            -Dsonar.sources=. \
                            -Dsonar.python.coverage.reportPaths=coverage.xml \
                            -Dsonar.host.url=$SONAR_HOST_URL
                    '''
                }
            }
        }

        stage('Build and Push Docker Images') {
            steps {
                script {
                    def services = ['customer-service', 'order-service', 'notification-service', 
                                  'inventory-service', 'vendor-service', 'cart-service']
                    
                    services.each { service ->
                        def imageTag = "${DOCKER_REGISTRY}/${service}:${BUILD_NUMBER}"
                        
                        // Build Docker image
                        sh """
                            docker build -t ${imageTag} \
                                --build-arg SERVICE=${service} \
                                -f docker/Dockerfile .
                        """
                        
                        // Push to registry
                        withCredentials([usernamePassword(credentialsId: 'docker-creds', 
                                                        usernameVariable: 'DOCKER_USER', 
                                                        passwordVariable: 'DOCKER_PASS')]) {
                            sh """
                                echo \$DOCKER_PASS | docker login ${DOCKER_REGISTRY} -u \$DOCKER_USER --password-stdin
                                docker push ${imageTag}
                            """
                        }
                        
                        // Update Helm values with new image tag
                        sh """
                            yq eval '.${service}.image.tag = "${BUILD_NUMBER}"' -i k8s/helm/microservices/values.yaml
                        """
                    }
                }
            }
        }

        stage('Deploy to Development') {
            when { branch 'develop' }
            steps {
                withKubeConfig([credentialsId: 'kube-config']) {
                    sh """
                        helm upgrade --install ${HELM_RELEASE_NAME} \
                            ./k8s/helm/microservices \
                            --namespace ${NAMESPACE} \
                            --create-namespace \
                            --set environment=development \
                            --wait
                    """
                }
            }
        }

        stage('Deploy to Staging') {
            when { branch 'staging' }
            steps {
                withKubeConfig([credentialsId: 'kube-config']) {
                    sh """
                        helm upgrade --install ${HELM_RELEASE_NAME} \
                            ./k8s/helm/microservices \
                            --namespace ${NAMESPACE}-staging \
                            --create-namespace \
                            --set environment=staging \
                            --wait
                    """
                }
            }
        }

        stage('Deploy to Production') {
            when { branch 'main' }
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    input message: 'Approve production deployment?'
                }
                withKubeConfig([credentialsId: 'kube-config']) {
                    sh """
                        helm upgrade --install ${HELM_RELEASE_NAME} \
                            ./k8s/helm/microservices \
                            --namespace ${NAMESPACE}-prod \
                            --create-namespace \
                            --set environment=production \
                            --wait
                    """
                }
            }
        }
    }

    post {
        always {
            junit '**/test-results/*.xml'
            cobertura coberturaReportFile: 'coverage.xml'
            cleanWs()
        }
        success {
            slackSend(
                color: 'good',
                message: "Build #${BUILD_NUMBER} succeeded in ${currentBuild.durationString}"
            )
        }
        failure {
            slackSend(
                color: 'danger',
                message: "Build #${BUILD_NUMBER} failed in ${currentBuild.durationString}"
            )
        }
    }
}
