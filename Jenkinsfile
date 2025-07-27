// def gv

// pipeline {
//     agent any
//     stages {
//         stage("init") {
//             steps {
//                 script {
//                     gv = load "script.groovy"
//                 }
//             }
//         }
//         stage("build jar") {
//             steps {
//                 script {
//                     echo "building jar"
//                     //gv.buildJar()
//                 }
//             }
//         }
//         stage("build image") {
//             steps {
//                 script {
//                     echo "building image"
//                     //gv.buildImage()
//                 }
//             }
//         }
//         stage("deploy") {
//             steps {
//                 script {
//                     echo "deploying"
//                     //gv.deployApp()
//                 }
//             }
//         }
//     }   
// }
pipeline {
    agent any
    parameters{
        choice(name :'VERSION', choices : ['1.1.0','1.2.0','1.3.0'], description :'')
        booleanParam(name :'executeTest', defaultValue :true, description :'')
    }
    stages{
        stage("build"){
            steps{
                echo 'building the application ....'
            }
        }
        stage("test"){
            when{
                expression{
                    params.executeTest
                }
            }
            steps{
                echo 'testing the application ....'
            }
        }
        stage("deploy"){
            steps{
                echo 'deploying the application ....'
                echo "Deploying the version ${params.VERSION}"
            }
        }
    }
}
