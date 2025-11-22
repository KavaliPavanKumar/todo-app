pipeline {
    agent any

    tools {
        maven 'Maven3'
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

        stage('Kill All Java') {
            steps {
                powershell '''
                    Write-Host "Killing all Java processes..."
                    Get-Process java -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue
                '''
            }
        }

        stage('Unlock & Delete Target') {
            steps {
                powershell '''
                    Write-Host "Deleting target directory with retries..."
                    $dir = "target"
                    $max = 10

                    for ($i = 1; $i -le $max; $i++) {
                        if (!(Test-Path $dir)) { break }

                        try {
                            Remove-Item -Recurse -Force -ErrorAction Stop $dir
                            Write-Host "Target deleted."
                            break
                        }
                        catch {
                            Write-Host "Attempt $i failed. Retrying..."
                            Start-Sleep -Seconds 2
                        }
                    }
                '''
            }
        }

        stage('Build Application') {
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

    post {
        failure {
            echo "Pipeline failed!"
        }
    }
}
