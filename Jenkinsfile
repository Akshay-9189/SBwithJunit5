pipeline{

    agent any

    stages{
        stage("Build"){
            steps{
                bat 'mvnw clean install -DskipTests'
            }
        }

        stage("Test"){
            steps{
                bat 'mvnw test'
            }
        }

        stage("Deploy"){
            steps{
                bat 'mvnw spring-boot:run'
            }
        }
    }
}