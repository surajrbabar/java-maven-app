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
        
        stage("increment version") {
            steps {
                script {
                    echo "Incrementing app version"
        
                    // Read current version from pom.xml
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    if (!matcher) {
                        error "Could not find version in pom.xml"
                    }
        
                    def version = matcher[0][1]
                    echo "Current version in pom.xml: ${version}"
        
                    // Split version and increment last digit
                    def parts = version.tokenize('.')
                    if (parts.size() < 3) {
                        error "Version format is incorrect: ${version}"
                    }
        
                    def major = parts[0]
                    def minor = parts[1]
                    def patch = parts[2].replaceAll('-SNAPSHOT', '').toInteger() + 1
        
                    def newVersion = "${major}.${minor}.${patch}"
                    echo "New version: ${newVersion}"
        
                    // Set new version in pom.xml
                    sh "mvn versions:set -DnewVersion=${newVersion} versions:commit"
        
                    env.IMAGE_NAME = "${newVersion}-${BUILD_NUMBER}"
                    echo "Set IMAGE_NAME to ${env.IMAGE_NAME}"
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
