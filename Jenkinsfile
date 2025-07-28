#!/user/bin/env groovy

@Library('jenkins-shared-library')

def gv

pipeline {
    agent any
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
                    buildApp()
                }
            }
        }
        stage("buildImage"){
            // when {
            //     expression {
            //         BRANCH_NAME == "master"
            //     }
            // }
            steps{
                script{
                    buildImage()
                }
            }
        }
        stage("deploy"){
            // when {
            //     expression {
            //         BRANCH_NAME == "master"
            //     }
            // }
            steps{
                script{
                    gv.deployApp()
                }
            }
        }
    }
}
