pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "Pulling source code from Git..."
                git branch: 'main', url: 'https://github.com/KavaliPavanKumar/todo-app.git'
            }
        }

        stage('Build Application') {
            steps {
                echo "Building Java Application..."
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Stop Old Application') {
            steps {
                echo "Stopping old application if running..."

                // Kill previous Java process
                bat '''
                for /f "tokens=2 delims=," %%a in ('wmic process where "CommandLine like '%%todo-app%%'" get ProcessId /format:csv ^| findstr /r "[0-9]"') do (
                    echo Killing process %%a
                    taskkill /PID %%a /F
                )
                echo No existing app process found or already killed.
                '''
            }
        }

        stage('Start Application') {
            steps {
                echo "Starting new application..."

                // Run your jar file
                bat '''
                start java -jar target\\todo-app-0.0.1-SNAPSHOT.jar
                echo Application started successfully!
                '''
            }
        }
    }
}
