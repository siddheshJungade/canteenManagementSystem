#!/usr/bin/env groovy
@Library('shared-groovy-lib')
def gv

pipeline {
    agent any

    tools {
        maven "meven-3.6"
    }

    stages {
        stage('init'){
            steps {
                gv = load "script.groovy"
            }
        }
        stage("build jar") {
            steps {
                script{
                    buildjar()
                }

            }
        }

        stage("building docker image") {
            steps {
                script {
                    pushBuildDocker()
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
} 