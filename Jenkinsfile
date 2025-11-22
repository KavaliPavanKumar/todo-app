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

        stage('Stop Old Application') {
    steps {
        echo "Stopping old todo-app if running..."
        bat '''
        echo Searching for todo-app JAR process...

        for /f "tokens=2 delims=," %%i in ('
            tasklist /FI "IMAGENAME eq java.exe" /FO CSV /V ^| findstr /I "todo-app-1.0.0.jar"
        ') do (
            echo Killing Java PID: %%i
            taskkill /F /PID %%i
        )

        echo Done checking old processes.
        '''
    }
}

        stage('Build Application') {
            steps {
                echo "Building Java Application..."
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Start Application') {
            steps {
                echo "Starting new todo-app..."
                bat 'start "" java -jar target\\todo-app-1.0.0.jar'
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed. Please check the logs."
        }
        success {
            echo "Pipeline completed successfully!"
        }
    }
}
