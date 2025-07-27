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
def gv
pipeline {
    agent any
    parameters{
        // choice(name :'VERSION', choices : ['1.1.0','1.2.0','1.3.0'], description :'')
        string(name :"VERSION", defaultValue :'', description :'Enter the version')
        booleanParam(name :'executeTest', defaultValue :true, description :'')
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script2.groovy"
                }
            }
            
        }
        stage("build"){
            steps{
                script{
                    gv.buildApp()
                }
            }
        }
        stage("test"){
            when{
                expression{
                    params.executeTest
                }
            }
            steps{
                script{
                    gv.testApp()
                }
            }
        }
        stage("deploy"){
            input {
                message "Select the environment to deploy" ok "Done"
                parameters {
                    choice(name :"ENV", choices : ['dev', 'pro', 'test'], description : " ")
                }
            }
            steps{
                script{
                    gv.deployApp()
                    echo "Deploying environment {ENV}"
                }
            }
        }
    }
}
