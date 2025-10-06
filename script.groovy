def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage(IMAGE) {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ${IMAGE} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push ${IMAGE}"
    }
} 

def commit(){
    withCredentials([string(credentialsId : 'github-token', variable : "TOKEN")]) {
        // def safePass = URLEncoder.encode(PASS, "UTF-8")

        sh 'git config --global user.email "jenkins@ex.com"'
        sh 'git config --global user.name "jenkins"'
        
        sh 'git status'
        sh 'git branch'
        sh 'git config --list'
        
        // Use safePass here
        sh "git remote set-url origin https://surajrbabar:${TOKEN}@github.com/surajrbabar/java-maven-app.git"
        
        sh 'git add .'
        sh 'git commit -m "ci: version bump" || echo "No changes to commit"'
        sh 'git push --force origin HEAD:jenkins-jobs'
    }
}
// def deployApp() {
//     echo 'deploying the application...'
// } 

return this
