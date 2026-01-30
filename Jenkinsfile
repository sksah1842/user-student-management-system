pipeline {
    agent any

    environment {
        KUBECONFIG = "C:/Users/faizt/.kube/config"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/faiz-devops2026/user-student-management-system.git'
            }
        }

        stage('Build Auth Service (Maven)') {
            steps {
                dir('auth-module') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Student Service (Maven)') {
            steps {
                dir('student-module') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build API Gateway (Maven)') {
            steps {
                dir('api-gateway') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Images (Local)') {
            steps {
                bat """
                docker build -t auth-service:latest auth-module
                docker build -t student-service:latest student-module
                docker build -t api-gateway:latest api-gateway
                """
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat """
                kubectl apply -f k8s/

                kubectl rollout restart deployment/auth-deployment
                kubectl rollout restart deployment/student-deployment
                kubectl rollout restart deployment/gateway-deployment

                kubectl rollout status deployment/student-deployment
                """
            }
        }
    }

    post {
        success {
            echo '✅ CI/CD pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check Jenkins logs.'
        }
    }
}
