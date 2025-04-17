pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout your repository from Git
                git 'https://github.com/Mohamad-Nasser-engr/Playwright_Jenkins_Test'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Compile the Java code
                    bat 'javac D:\\eclipse-workspace\\Playwright_test\\src\\test.java'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run the Java test
                    bat 'java -cp D:\\eclipse-workspace\\Playwright_test\\src test'
                }
            }
        }
    }
}
