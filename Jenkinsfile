def gv
pipeline {
    agent any
    tools{
        maven "maven-3.9.11"
    }
    environment{
        IMAGE = "surajrbabar/java-maven-app:java-maven"
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
         stage("increment version") {
            steps {
                script {
                    echo "Incrementing app version...."
        
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$IMAGE-$version"

                }
            }
        }
        stage("build"){
            steps{
                script{
                    echo "building the applicaiton ...."
                    gv.buildJar()
                }
                
            }
        }
        stage("build image"){
            steps{
                script{
                    gv.buildImage env.IMAGE_NAME
                }
            }
        }
        stage("deploy"){
            steps{
                script{
                    echo "deploying the application ...."
                    def shellCMD = 'bash ./shellScript.sh ${IMAGE_NAME}'
                    sshagent(['ec2-server-key']) {
                        sh "scp shellScript.sh ec2-user@13.201.132.82:/home/ec2-user"
                        sh "scp docker-compose.yaml ec2-user@13.201.132.82:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.201.132.82 ${shellCMD}"
                    }
                }
                
            }
        }
        stage("commit version update"){
            steps{
                script{
                    gv.commit()
                }
            }
        }
    }
}
