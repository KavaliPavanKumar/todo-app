pipeline {
    agent any
    
    tools {
        maven 'Maven3'
        jdk 'JAVA_HOME'
    }

    stages {

        // 1. Kill any previously running application processes that might be holding files
        stage('Kill Java') {
            steps {
                powershell '''
                    # Stop the Java process associated with the application
                    Get-Process java -ErrorAction SilentlyContinue |
                    Stop-Process -Force -ErrorAction SilentlyContinue
                '''
            }
        }

        // 2. Explicitly wipe the entire workspace after killing the process
        stage('Wipe Workspace') {
            steps {
                echo "Cleaning Jenkins workspace..."
                deleteDir()
            }
        }

        // 3. Checkout the code into the now-clean workspace
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    credentialsId: 'JenkinsPavanAdmin',
                    url: 'https://github.com/KavaliPavanKumar/todo-app.git'
            }
        }
        
        // ... continue with Build and Start stages ...

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
