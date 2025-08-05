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
            when {
                expression {
                    BRANCH_NAME == "master"
                }
            }
            steps{
                echo "deploying the application ...."
            }
        }
    }
}
