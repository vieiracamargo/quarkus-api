pipeline {
    agent any
    tools {
            maven 'Maven'
    }
    environment {
            KUBECONFIG = credentials('kubernetes')
    }
    stages {
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
//                 sh 'helm install loki grafana/loki-stack --set grafana.enabled=true'
            }
        }
    }
}




