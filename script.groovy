def buildApp() {
        echo "building the application"
        sh 'mvn package'
}

def deployToDockerHub(){
                    echo "building the docker images"
                    withCredentials([usernamePassword(credentialsId:'docker_hub_id',usernameVariable: 'USER',passwordVariable: "PWD")]){
                        sh 'docker build -t siddheshjungade/demojava-app .'
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh 'docker push siddheshjungade/demojava-app'
                    }
}


def deployApp() {
    echo "deploying the application"
}

return this