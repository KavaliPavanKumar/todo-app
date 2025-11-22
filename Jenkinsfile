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

                // Working Windows method to kill java process running todo-app
                bat '''
                echo Searching for old todo-app process...

                for /f "tokens=2 delims==" %%a in ('
                    wmic process where "name='java.exe'" get ProcessId^,CommandLine /value ^| findstr /i "todo-app"
                ') do (
                    echo Killing Java Process: %%a
                    taskkill /F /PID %%a
                )

                echo If no PIDs above, no old process was running.
                '''
            }
        }

        stage('Start Application') {
            steps {
                echo "Starting new application..."
                bat '''
                echo Launching todo-app...
                start "" java -jar target\\todo-app-0.0.1-SNAPSHOT.jar
                echo Application started successfully!
                '''
            }
        }
    }
}
