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
                    def dockerCmd = 'docker run -d -p 8080:80 surajrbabar/java-maven-app:web-1.0'
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeychecking=no ec2-user@13.201.132.82 ${dockerCmd}"
                    }
                }
            }
        }
    }
}
