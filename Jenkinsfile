pipeline{

    agent any

    stages{
        stage("Build"){
            bat 'mvnw clean install -DskipTests'
        }

        stage("Test"){
            bat 'mvnw test'
        }

        stage("Deploy"){
            bat 'mvnw spring-boot:run'
        }
    }
}