pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Mohamad-Nasser-engr/Playwright_Jenkins_Test'
            }
        }
        stage('Build') {
            steps {
                script {
                    // Running Maven to install dependencies and compile the project
                    sh 'mvn clean install'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    // Running tests using Maven (ensure your tests are configured in the pom.xml)
                    sh 'mvn test'
                }
            }
        }
    }
    post {
        always {
            // Cleanup actions
            echo 'Cleaning up'
        }
        success {
            echo 'Build and tests passed!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}
