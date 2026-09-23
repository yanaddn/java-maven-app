#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'maven3.6'
    }
    stages {
        stage("test") {
            steps {
                script {
                     echo "Testing the app..."
                }
            }
        }
        stage("build") {
            steps {
                script {
                    echo "Building the app..."
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    def dockerCmd = 'docker run -p 3080:3080 -d yanadidun/demo-app:1.0'
                    sshagent(['ec2-server-key']) {
                      sh "ssh -o StrictHostKeyChecking=no ec2-user@13.48.42.243 ${dockerCmd}"
                    }
                }
            }
        }
    }   
}
