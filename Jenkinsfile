def gv

pipeline {
    agent any

    environment {
        NEW_VERSION = '1.3.0'
    } 
    parameters {
        choice(name: 'VERSION',choices:['1','2','3'],description: "")
        booleanParam(name: 'executeTests',defaultValue: true, description: "")
    }
    stages {
        stage("init") {
            steps{
                script {
                    gv = load "script,groovy"
                }
            }
        }
        stage("build") {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }

    stage("test") {
        when {
            expression {
                params.executeTests == true
            }
        }
        steps {
              script{
                gv.buildApp()
              }
        }
    }
    stage("deploy") {
            steps {
                echo "deployment"
            }
        }
    }

    post {
        always {
            echo "sending email"
        }
        success {
            echo "build success"
        }
        failure {
            echo "build failure"
        }
    }
}
