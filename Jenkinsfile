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
        stage("build") {
            steps {
                echo "building application"
                echo "building version ${NEW_VERSION}"

        }
    }

    stage("test") {
        when {
            expression {
                params.executeTests == true
            }
        }
        steps {
                echo "testing"
                echo "${params.VERSION}"
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
