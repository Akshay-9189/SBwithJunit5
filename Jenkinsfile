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
                deploy adapters: [tomcat9(path: '', url: 'http://locahost:8080')], contextPath: null, war: '**/*.jar'
            }
        }
    }

    post{
        success{
            bat 'echo ----- Build Success -----'
            archiveArtifacts artifacts: '**/target/*.jar'
        }

        failure{
            bat 'echo ----- Build Failed -----'
        }
    }
}