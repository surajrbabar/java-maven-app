// def gv

// pipeline{
//     agent any
//     tools{
//         maven 'maven-3.9.11'
//     }
//     stages{
//         stage("init"){
//             steps{
//                 script{
//                     gv = load "script.groovy"
//                 }
//             }
//         }
//         stage("build jar"){
//             steps{
//                 script{
//                     gv.buildApp()
//                 }
                
//             }
//         }
//         stage("build image"){
//             steps{
//                 script{
//                     gv.buildImage()
//                 }
                
//             }
//         }
//         stage("deploy"){
//             steps{
//                 script{
//                     gv.deployApp()
//                 }
//             }
            
//         }
//     }
// }


pipeline {
    agent any
    stages{
        stage("test"){
            steps{
                echo "testing the application ...."
            }
        }
        stage("build"){
            // when {
            //     expression {
            //         BRANCH_NAME == "master"
            //     }
            // }
            steps{
                echo "building the applicaiton ...."
            }
        }
        stage("deploy"){
            // when {
            //     expression {
            //         BRANCH_NAME == "master"
            //     }
            // }
            steps{
                echo "deploying the application ...."
            }
        }
    }
}
