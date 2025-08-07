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

def commit(){
    withCredentials([string(credentialsId : 'github-token', variable : "TOKEN")]) {
        // def safePass = URLEncoder.encode(PASS, "UTF-8")
        
        sh 'git status'
        sh 'git branch'
        sh 'git config --list'
        
        // Use safePass here
        sh "git remote set-url origin https://surajrbabar:${TOKEN}@github.com/surajrbabar/java-maven-app.git"
        
        sh 'git add .'
        sh 'git commit -m "ci: version bump" || echo "No changes to commit"'
        sh 'git push origin HEAD:jenkins-jobs'
    }
}


return this;
