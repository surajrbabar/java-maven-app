def buildApp(){
    echo "building the jar file..."
    sh "mvn package"
}

def buildImage(){
    echo "building the docker image ..."
    withCredentials([usernamePassword(credentialsId : "dockerhub-credentials", usernameVariable : "USER", passwordVariable : "PASS")]){
        sh 'docker build -t surajrbabar/java-maven-app:jma-1.2'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push surajrbabar/java-maven-app:jma-1.2'
    }
}

def deployApp(){
    echo "deploying the application ..."
}

return this;
