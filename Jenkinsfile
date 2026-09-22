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
        stage("increment app version") {
            steps {
                script {
                    echo 'incrementing app version...'
                    sh 'mvn build-helper:parse-version versions:set \
                        - DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion}. /
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
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
                    buildImage'yanadidun/demo-app:$IMAGE_NAME'
                    dockerLogin()
                    dockerPush 'yanadidun/demo-app:$IMAGE_NAME'
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
