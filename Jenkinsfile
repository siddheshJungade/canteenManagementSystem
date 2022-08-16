pipeline {
    agent any

    tools {
        maven "meven-3.6"
    }

    stages {
        stage("build jar") {
            steps {
                echo "building the application"
                sh 'mvn package'
            }
        }

        stage("building docker image") {
            steps {
                script {
                    echo "building the docker images"
                    withCredentials([usernamePassword(credentialsId:'docker_hub_id',usernameVariable: 'USER',passwordVariable : "PWD")]){
                        sh 'docker build -t siddheshjungade/demojava-app .'
                        sh 'echo $PWD | docker login -u $USER --password-stdin'
                        sh 'docker push siddheshjungade/demojava-app'
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo "deploying the application"
                }
            }
        }
    }
}