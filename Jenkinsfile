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
            steps{
                bat 'echo "----- Build Success -----"'
            }
        }

        failure{
            steps{
                bat 'echo "----- Build Failed -----"'
            }
        }
    }
}