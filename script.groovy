def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage(IMAGE) {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ${IMAGE} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push ${IMAGE}"
    }
} 

// def deployApp() {
//     echo 'deploying the application...'
// } 

return this
