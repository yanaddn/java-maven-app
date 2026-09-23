#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@lesson-100', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/yanaddn/jenkins-shared-library.git',
    credentialsId: 'github-creds'
    ]
)

pipeline {
    agent any
    tools {
        maven 'maven3.6'
    }
    environment {
        IMAGE_NAME = 'yanadidun/demo-app:java-maven-1.0'
    } 
    stages {
        stage('build app') {
            steps {
                script {
                    echo 'building app jar...'
                    buildJar()
                }
            }
        }
        stage('build and push image') {
            steps {
                script {
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    def dockerCmd = 'docker run -p 8080:8080 -d yanadidun/demo-app:1.0'
                    sshagent(['ec2-server-key']) {
                      sh "ssh -o StrictHostKeyChecking=no ec2-user@13.48.42.243 ${dockerCmd}"
                    }
                }
            }
        }
    }   
}
