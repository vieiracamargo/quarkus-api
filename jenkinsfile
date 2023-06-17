pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Realiza o checkout do código fonte da sua aplicação
                // Aqui você pode usar o Git ou qualquer outro sistema de controle de versão que preferir
                // Exemplo:
                git 'https://github.com/vieiracamargo/quarkus-api.git'
            }
        }

        stage('Build') {
            steps {
                // Aqui você pode realizar a compilação ou qualquer outro processo de build da sua aplicação
                // Exemplo:
                sh './mvnw package'
                sh 'docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-api-jvm .'
            }
        }

        stage('Deploy') {
            environment {
                // Defina as variáveis de ambiente necessárias para o deploy
                // Exemplo:
                KUBECONFIG = "~/.kube/config"
            }

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




