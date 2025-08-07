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
        stage("increment version"){
          steps{
            script{
              echo "Incrementing app version"
              sh ''' 
                mvn build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementVersion} versions:commit 
                '''
              def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
              def version = matcher[0][1]
              env.IMAGE_NAME = "${version}-${BUILD_NUMBER}"
            }
          }
        }
        stage("build jar"){
            steps{
                script{
                    gv.buildApp()
                }
                
            }
        }
        stage("build image"){
            steps{
                script{
                    gv.buildImage()
                }
                
            }
        }
        stage("deploy"){
            steps{
                script{
                    gv.deployApp()
                }
            }
            
        }
    }
}


// pipeline {
//     agent any
//     stages{
//         stage("test"){
//             steps{
//                 echo "testing the application ....."
//             }
//         }
//         stage("build"){
//             when {
//                 expression {
//                     BRANCH_NAME == "master"
//                 }
//             }
//             steps{
//                 echo "building the applicaiton ...."
//             }
//         }
//         stage("deploy"){
//             when {
//                 expression {
//                     BRANCH_NAME == "master"
//                 }
//             }
//             steps{
//                 echo "deploying the application ...."
//             }
//         }
//     }
// }
