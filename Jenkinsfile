pipeline {
    agent any
    stages {
        stage('Clean Artificats') {
            steps {
                bat mvn clean
            }
        }
		stage('Test'){
		steps{
		bat mvn test
		}
    }
	stage('Install'){
		steps{
		bat mvn package
		}
    }
}}