#!/user/bin/env groovy

@Library('jenkins-shared-library')

def gv

pipeline {
    agent any
    tools{
        maven 'maven-3.9.11'
    }
    environment{
        IMAGE_NAME = 'surajrbabar/java-maven-app:java-maven-1.0'
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load 'script.groovy'
                }
            }
        }
        stage("buildJar"){
            steps{
                script{
                    echo "Building the application ...."
                    buildApp()
                }
            }
        }
        stage("buildImage"){
            steps{
                script{
                    echo "Building the docker image ...."
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    pushImage(env.IMAGE_NAME)
                }
            }
        }
        stage("deploy"){
            steps{
                script{
                    echo "Deploying the application on the EC2 server ...."
                    def dockerCmd = 'docker run -d -p 8080:8080 surajrbabar/java-maven-app:web-1.0'
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeychecking=no ec2-user@13.201.132.82 ${dockerCmd}"
                    }
                }
            }
        }

    }
}
