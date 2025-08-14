pipeline {
    agent any
    stages{
        stage("test"){
            steps{
                echo "testing the application ....."
            }
        }
        stage("build"){
            when {
                expression {
                    BRANCH_NAME == "master"
                }
            }
            steps{
                echo "building the applicaiton ...."
            }
        }
        stage("deploy"){
           
            steps{
                script{
                    echo "deploying the application ...."
                    def shellCMD = "bash ./shellScript.sh"
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
