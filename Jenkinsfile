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
            post{
                echo '==== Archiving the artifacts ===='
                archiveArtifacts artifacts: '**/target/*.jar'
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
            bat 'echo "----- Build Success -----"'
        }

        failure{
            bat 'echo "----- Build Failed -----"'
        }
    }
}