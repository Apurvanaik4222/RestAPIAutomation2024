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

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build was successful!'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true

            // Send email on success
            emailext (
                subject: 'Jenkins Test',
                body: '''Hi,

This is a Jenkins Test Message

Thanks & Regards,
APN Team''',
                to: 'apurvanaik42@gmail.com'
            )
        }

        failure {
            echo 'Build failed!'
            // Send email on failure
            emailext (
                subject: 'Jenkins Build Failed',
                body: '''Hi,

The Jenkins build has failed.

Please check the details.

Thanks & Regards,
APN Team''',
                to: 'apurvanaik42@gmail.com'
            )
        }
    }
}
