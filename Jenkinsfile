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
                    // Captura a saída do comando minikube docker-env
                    def minikubeEnv = sh(
                        returnStdout: true,
                        script: 'minikube docker-env'
                    ).trim()

                    // Avalia a saída para obter os valores das variáveis de ambiente
                    sh script: minikubeEnv

                    // Configura as variáveis de ambiente Docker manualmente
                    withEnv([
                        "DOCKER_HOST=${env.DOCKER_HOST}",
                        "DOCKER_TLS_VERIFY=${env.DOCKER_TLS_VERIFY}",
                        "DOCKER_CERT_PATH=${env.DOCKER_CERT_PATH}"
                        // ... (outras variáveis de ambiente do Docker, se houver)
                    ])
        stage('Build') {
            steps {
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




