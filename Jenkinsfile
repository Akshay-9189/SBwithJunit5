pipeline{

    agent any

    tools{
        maven 'maven'
    }

    stages{
        stage("Build"){
            steps{
                bat 'mvn clean install -DskipTests'
            }
        }

        stage("Test"){
            steps{
                bat 'mvn test'
            }
        }
    }

    post{
        success{
            bat 'echo "----- Build Success -----"'
        }

        failure{
            bat 'echo "----- Build Failed -----"'
        }
    }
}