pipeline{

    agent any

    tools{
        maven 'Maven 3.9.4'
        jdk 'jdk17'
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
        always{
            echo 'Build Success'
        }
    }
}