pipeline {
    agent any
    
    tools {
        maven 'M3'
        jdk 'JAVA_HOME'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    credentialsId: 'JenkinsPavanAdmin',
                    url: 'https://github.com/KavaliPavanKumar/todo-app.git'
        }
    }

        stage('Force Stop Java Locks') {
            steps {
                powershell '''
                    Write-Host "Killing all Java processes..."
                    Get-Process java -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue
                '''
            }
        }

        stage('Build Application') {
            steps {
                echo "Building the Spring Boot application..."
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Stop Old Application') {
            steps {
                powershell '''
                    Write-Host "Stopping previous instance..."
                    Get-Process java -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue
                '''
            }
        }

        stage('Start Application') {
            steps {
                powershell '''
                    Write-Host "Starting new version..."
                    Start-Process "java" "-jar target/todo-app-1.0.0.jar" -WindowStyle Hidden
                '''
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed. Please check logs."
        }
    }
}
