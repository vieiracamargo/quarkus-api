pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
//                 Aqui você pode realizar a compilação ou qualquer outro processo de build da sua aplicação
//                 Exemplo:
//                 sh './mvnw package'
                sh 'docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-api-jvm .'
            }
        }

        stage('Deploy') {
            steps {
                // Configura o ambiente Kubernetes
                // Certifique-se de que o cluster esteja configurado corretamente no ambiente onde o Jenkins é executado
                // Exemplo:

                // Realiza o deploy usando o Helm
                // Certifique-se de ter o Helm instalado no ambiente onde o Jenkins é executado
                // Exemplo:
                sh 'helm upgrade --install ./k8s'
                sh 'helm install loki grafana/loki-stack --set grafana.enabled=true'
            }
        }
    }
}




