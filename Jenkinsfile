#!/usr/bin/env groovy

// Define Functions
def doMaven(args) {
    configFileProvider([configFile(fileId: 'leisure-settings', variable: 'MAVEN_SETTINGS')]) {
        sh(returnStdout: true, script: "mvn -s $MAVEN_SETTINGS $args")
    }
}

pipeline {
    environment {
        ARTIFACTORY_REPO_USER = credentials('ARTIFACTORY_LEISURE_ACCOUNT')
        ARTIFACTORY_REPO_PASS = credentials('ARTIFACTORY_LEISURE_PASSWORD')
        SERVICE = 'ics-microservice-demo'
    }

    agent { node { label 'leisure-slave' } }

    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }

        stage('Build WAR & Run Unit Tests') {
            steps {
                doMaven("-U clean install -T 4 -DskipTests")
            }
            post {
                always {
                    // junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: '**/microservice-demo*.jar', fingerprint: true
                }
            }
        }


    }
}
