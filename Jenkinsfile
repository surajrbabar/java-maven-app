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

            // Parse the current version from POM
            sh "mvn build-helper:parse-version -DparseVersion.propertyPrefix=parsedVersion"

            // Read parsed values from Maven output (set as env vars in target folder)
            def props = readProperties file: 'target/parsedVersion.properties'

            def major = props['parsedVersion.majorVersion']
            def minor = props['parsedVersion.minorVersion']
            def increment = props['parsedVersion.incrementalVersion'].toInteger() + 1

            def newVersion = "${major}.${minor}.${increment}"

            echo "Setting new version: ${newVersion}"

            // Set new version in pom.xml
            sh "mvn versions:set -DnewVersion=${newVersion} versions:commit"

            // Save version for later stages
            env.IMAGE_NAME = "${newVersion}-${BUILD_NUMBER}"
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
