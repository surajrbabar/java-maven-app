def buildApp(){
    echo "building the jar file..."
    sh "mvn clean package"
}

def buildImage(){
    echo "building the docker image ..."
    withCredentials([usernamePassword(credentialsId : 'dockerhub-credentials', usernameVariable : 'USER', passwordVariable : 'PASS')]){
        sh "docker build -t surajrbabar/java-maven-app:${IMAGE_NAME} ."
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh "docker push surajrbabar/java-maven-app:${IMAGE_NAME}"
    }
}

def deployApp(){
    echo "deploying the application ..."
}

return this;
