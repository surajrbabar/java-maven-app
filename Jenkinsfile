def gv
pipeline {
    agent any
    tools{
        maven "maven-3.9.11"
    }
    environment{
        IMAGE = "surajrbabar/java-maven-app:java-maven-2.0"
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage("build"){
            steps{
                script{
                    echo "building the applicaiton ...."
                    gv.build(env.IMAGE)
                }
                
            }
        }
        stage("deploy"){
           
            steps{
                script{
                    echo "deploying the application ...."
                    def shellCMD = "bash ./shellScript.sh ${env.IMAGE}"
                    sshagent(['ec2-server-key']) {
                        sh "scp shellScript.sh ec2-user@13.201.132.82:/home/ec2-user"
                        sh "scp docker-compose.yaml ec2-user@13.201.132.82:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.201.132.82 ${shellCMD}"
                    }
                }
                
            }
        }
    }
}
