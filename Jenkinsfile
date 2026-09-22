#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@lesson-100', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/yanaddn/jenkins-shared-library.git',
    credentialsId: 'github-creds'
    ]
)

def gv

pipeline {
    agent any
    tools {
        maven 'maven3.6'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage("build and push image") {
            steps {
                script {
                    buildImage'yanadidun/demo-app:jma-6.0'
                    dockerLogin()
                    dockerPush 'yanadidun/demo-app:jma-6.0'
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }   
}
