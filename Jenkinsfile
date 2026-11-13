
pipeline {
agent any

stages {

stage('Checkout') {
steps {
echo 'GitHub repository connected'
checkout scm
}
}

stage('Environment Check') {
steps {
bat 'java -version'
bat 'mvn -version'
}
}

stage('Run Automation') {
steps {
bat 'mvn clean test'
}
}
}
}
