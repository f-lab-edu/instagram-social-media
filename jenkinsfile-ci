pipeline {
    agent any

    environment {
        PATH = "/opt/gradle/gradle-6.6.1/bin:$PATH"
    }

    stages {

        stage('Git Checkout') {
            steps {
                checkout scm
                echo 'Git Checkout Success!'
            }
        }

        stage('Build') {
            steps {
                sh 'gradle clean build --exclude-task test'
                echo 'Build Success!'
            }
        }

        stage('Test') {
            steps {
                sh 'gradle test'
                echo 'Test Success!'
            }
        }

    }

}