pipeline {
    agent any

    tools {
        jdk ("jdk22")
    }

    environment {
        // 환경 변수 설정
        DOCKER_TAG = "latest"
        REPO_NAME = "sungwoo166/spring-app"
        CONTAINER_NAME = "springboot-web-service"
        IMAGE_NAME = "${REPO_NAME}:${DOCKER_TAG}"
        COMPOSE_FILE = '/var/jenkins_home/docker-compose.yml'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'develop', url: "https://github.com/Woom-s-Land/wooms-backend"
            }
        }

      	stage('application.properties 설정'){
          	steps{
                withCredentials([file(credentialsId: 'application-auth.properties', variable: 'properties')]) {
                    script {
                        sh 'cp $properties src/main/resources/application-auth.properties'
                    }
                }
            }
      	}

        stage('Prepare Environment') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Build Project') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'DOCKER_PWD', usernameVariable: 'DOCKER_USR')]) {
                    sh 'echo $DOCKER_PWD | docker login -u $DOCKER_USR --password-stdin'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${REPO_NAME}:${DOCKER_TAG} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                sh "docker push ${REPO_NAME}:${DOCKER_TAG}"
            }
        }

        stage('Stop Existing Containers') {
            steps {
                sh 'docker-compose -f ${COMPOSE_FILE} down'
            }
        }

        stage('Start New Containers') {
            steps {
                sh 'docker-compose -f ${COMPOSE_FILE} up -d' 
            }
        }
    }
}
