pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JAVA_HOME'
    }

    stages {

        stage('Wipe Workspace') {
            steps {
                echo "Cleaning Jenkins workspace..."
                deleteDir()
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    credentialsId: 'JenkinsPavanAdmin',
                    url: 'https://github.com/KavaliPavanKumar/todo-app.git'
            }
        }

        stage('Kill Java') {
            steps {
                powershell '''
                    Get-Process java -ErrorAction SilentlyContinue |
                    Stop-Process -Force -ErrorAction SilentlyContinue
                '''
            }
        }

        stage('Build') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Start Application') {
            steps {
                powershell '''
                    Start-Process "java" "-jar target/todo-app-1.0.0.jar" -WindowStyle Hidden
                '''
            }
        }
    }
}
