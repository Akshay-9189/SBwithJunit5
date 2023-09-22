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

        stage("Deploy"){
            steps{
                deploy adapters: [tomcat9(credentialsId: '49f7166e-bd60-4e0f-9c3c-438dda0bc195', path: '', url: 'http://localhost:8080/')], contextPath: null, onFailure: false, war: 'target/*.war'
            }
        }
    }

    post{
        success{
            bat 'echo ----- Build Success -----'
        }

        failure{
            bat 'echo ----- Build Failed -----'
        }
    }
}