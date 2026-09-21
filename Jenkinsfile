def gv

pipeline {
    agent any
    tools {
        maven 'maven3.6'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                    echo "building the app..."
                    sh 'mvn package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building the image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-creds', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t yanadidun/demo-app:jma-2.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push yanadidun/demo-app:jma-2.0'
                    }
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the app..."
                }
            }
        }
    }
}
