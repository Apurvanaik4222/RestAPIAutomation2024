pipeline {
    agent any

    stages {
        stage('Clean Artifacts') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {  // Renamed to 'Package' for clarity
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build was successful!'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        failure {
            echo 'Build failed!'
        }
    }
}
