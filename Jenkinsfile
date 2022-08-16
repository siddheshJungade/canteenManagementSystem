pipeline {
    agent any

    environment {
        NEW_VERSION = '1.3.0'
    } 
    parameters {
        choice(name: 'VERSION',choice:['1','2','3'],descpration: "")
        booleanParm(name: 'executeTests',defaultValue: true, descpration: "")
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
