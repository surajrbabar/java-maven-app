def gv

pipeline{
    agent any
    tools{
        maven 'maven-3.9.11'
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar"){
            steps{
                gv.buildApp()
            }
        }
        stage("build image"){
            steps{
                
            }
        }
        stage("deploy"){
            gv.deployApp()
        }
    }
}
