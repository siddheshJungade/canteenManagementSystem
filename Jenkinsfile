def gv

pipeline {
    agent any

    parameters {
        choice(name: 'VERSION',choices:['1','2','3'],description: "")
        booleanParam(name: 'executeTests',defaultValue: true, description: "")
    }
    stages {
        stage("init") {
            steps {
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
                gv.testApp()
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
