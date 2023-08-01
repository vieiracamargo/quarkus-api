# README - Rodando o Projeto

Este documento descreve os passos necessários para executar o projeto localmente utilizando Kubernetes (K8s) e Jenkins. Certifique-se de ter os seguintes requisitos instalados antes de prosseguir:

## Requisitos Necessários

1. [Docker](https://docs.docker.com/get-docker/)
2. [Minikube](https://minikube.sigs.k8s.io/docs/start/)
3. [Jenkins](https://www.jenkins.io/doc/book/installing/)
4. [kubectl](https://kubernetes.io/docs/tasks/tools/)
5. [Helm](https://helm.sh/docs/intro/install/)

## Passos para Execução

### 1. Iniciando o Minikube

No terminal, utilize o seguinte comando para iniciar o cluster local do Minikube:

```sh
minikube start
```

### 2. Ativando o NGINX Ingress Controller

Para ativar o NGINX Ingress Controller no Minikube, execute o seguinte comando:

```sh
minikube addons enable ingress
```

### 3. Verificando o Status do Jenkins

Verifique se o serviço do Jenkins está ativo usando o seguinte comando:

```sh
sudo systemctl status jenkins
```

### 4. Acesso ao Jenkins

Acesse o Jenkins através do seu navegador, utilizando o endereço "http://localhost:8080".

Durante a primeira execução, o Jenkins irá solicitar uma senha de desbloqueio. Você pode obtê-la no servidor usando o seguinte comando:

```sh
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

### 5. Configurando Credencial para o Kubernetes

Crie uma credencial para o Kubernetes em:
`Manage Jenkins` > `Credentials` > `System` > `Global credentials` > `Add Credentials`

Preencha os seguintes campos:
- Kind: Secret file
- Scope: Global
- ID: kubernetes
- Description: kubernetes

### 6. Criando o Pipeline no Jenkins

Vá para a Dashboard do Jenkins e siga os passos abaixo:

1. Clique em `New Item`
2. Selecione `Pipeline`
3. Em `Definition`, selecione `Pipeline script from SCM`
4. Em `SCM`, escolha `GIT`
5. Informe a URL do repositório que contém o conteúdo do projeto. Exemplo:
  - Repository URL: https://github.com/vieiracamargo/quarkus-api.git
6. Branches to build: */main
7. Script Path: Jenkinsfile

Após configurar o pipeline, volte para a Dashboard e execute-o.

Com esses passos, o projeto será implantado no cluster Kubernetes gerenciado pelo Minikube, e o Jenkins automatizará o processo de construção e implantação.