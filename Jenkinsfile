pipeline {
    agent any
    tools {
            maven 'Maven'
    }
    environment {
            KUBECONFIG = credentials('kubernetes')
    }
    stages {
        stage('Setup Minikube') {
                steps {
                    def minikubeEnv = sh(
                                        returnStdout: true,
                                        script: 'minikube docker-env'
                                    ).trim()

                    sh script: minikubeEnv

                    withEnv([
                                        "DOCKER_HOST=${env.DOCKER_HOST}",
                                        "DOCKER_TLS_VERIFY=${env.DOCKER_TLS_VERIFY}",
                                        "DOCKER_CERT_PATH=${env.DOCKER_CERT_PATH}"
                    ])
                }
            }

        stage('Build') {
            steps {
                sh 'eval $(minikube docker-env)'
                sh 'mvn -v'
                sh 'mvn package'
                sh 'docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-api-jvm .'
            }
        }

        stage('Deploy') {
            steps {
                sh 'helm upgrade --install k8s ./k8s'
                sh 'helm repo add grafana https://grafana.github.io/helm-charts'
                sh 'helm install loki grafana/loki-stack --set grafana.enabled=true'
            }
        }
    }
}




