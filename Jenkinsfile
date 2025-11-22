pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JAVA_HOME'
        MAVEN_HOME = tool 'Maven3'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"
        APP_JAR = "todo-app-1.0.0.jar"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/KavaliPavanKumar/todo-app.git',
                    branch: 'main',
                    credentialsId: 'JenkinsPavanAdmin'
            }
        }

        stage('Stop Old Application') {
            steps {
                echo "Stopping any running instance of ${APP_JAR}..."

                powershell """
                \$proc = Get-WmiObject Win32_Process | Where-Object {
                    \$_.CommandLine -like '*${APP_JAR}*'
                }

                if (\$proc) {
                    Write-Host "Killing PID: " \$proc.ProcessId
                    Stop-Process -Id \$proc.ProcessId -Force
                } else {
                    Write-Host "No running todo-app JAR found."
                }
                """
            }
        }

        stage('Build Application') {
            steps {
                echo "Building the Spring Boot application..."
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Start Application') {
            steps {
                echo "Starting new application..."
                bat """
                cd target
                start "" java -jar ${APP_JAR}
                """
            }
        }

    }

    post {
        success {
            echo "Deployment Successful!"
        }
        failure {
            echo "Pipeline failed. Please check logs."
        }
    }
}
