pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JAVA_HOME'
        MAVEN_HOME = tool 'Maven3'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"
        APP_JAR = "todo-app-1.0.0.jar"
        WORK_DIR = "${WORKSPACE}/target"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/KavaliPavanKumar/todo-app.git', branch: 'main', credentialsId: 'JenkinsPavanAdmin'
            }
        }

        stage('Stop Old Application') {
            steps {
                echo "Stopping any running instance of ${APP_JAR}..."

                bat '''
                echo Checking for running JAR via WMIC...

                for /f "tokens=2 delims==;" %%a in ('wmic process where "name=\'java.exe\'" get CommandLine,ProcessId /value ^| findstr /I "%APP_JAR%"') do (
                    echo Killing old process PID %%a
                    taskkill /F /PID %%a
                )

                echo Completed old process cleanup.
                '''
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
        failure {
            echo "Pipeline failed. Please check logs."
        }
        success {
            echo "Deployment Successful!"
        }
    }
}
