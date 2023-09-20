pipeline{

    agent any

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
        always{
            echo 'Build Success'
        }
    }
}