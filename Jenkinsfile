pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'                 // Make sure you configured this in Jenkins tools
        maven 'Maven3'         // Configure Maven under Global Tools
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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy Application') {
            steps {
                echo "Stopping old application (if running)..."
                sh '''
                    PID=$(pgrep -f "todo-app") || true
                    if [ -n "$PID" ]; then
                        kill -9 $PID
                        echo "Stopped old process: $PID"
                    else
                        echo "No old process running."
                    fi
                '''
            }
        }

        stage('Start Application') {
            steps {
                echo "Starting new application..."
                sh '''
                    nohup java -jar target/*.jar > app.log 2>&1 &
                    echo "Application started successfully!"
                '''
            }
        }
    }
}
