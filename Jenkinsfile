
pipeline {
agent any

options {
timestamps()
disableConcurrentBuilds()
}

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

stage('HTML Report') {
steps {
publishHTML(target: [
reportDir: 'target',
reportFiles: 'cucumber-report.html',
reportName: 'Cucumber HTML Report',
keepAll: true,
alwaysLinkToLastBuild: true,
allowMissing: true
])
}
}

stage('Allure Report') {
steps {
allure([
includeProperties: false,
results: [[path: 'AllureReports/allure-results']]
])
}
}
}

post {
always {
archiveArtifacts(
artifacts: 'target/cucumber-report.html, AllureReports/allure-results/**, AllureReports/allure-report/**',
allowEmptyArchive: true
)
}
stage('Prepare Allure Email Attachment') {
steps {
bat '''
powershell -Command "Compress-Archive -Path 'AllureReports/allure-report/*' -DestinationPath 'AllureReports/AllureReport.zip' -Force"
'''
}
}
success {
emailext(
subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
body: """
Automation Execution Successful!

Job: ${JOB_NAME}
Build: ${BUILD_NUMBER}
Status: ${currentBuild.currentResult}

Jenkins Build URL:
${BUILD_URL}
Allure HTML Report:
${BUILD_URL}artifact/AllureReports/allure-report/index.html
""",
to: 'sakthivel.kiaq@gmail.com',
attachmentsPattern: 'AllureReports/AllureReport.zip'
)
}

failure {
emailext(
subject: "FAILURE: ${JOB_NAME} #${BUILD_NUMBER}",
body: """
Automation Execution Failed!

Job: ${JOB_NAME}
Build: ${BUILD_NUMBER}
Status: ${currentBuild.currentResult}

Please check Console Output:
${BUILD_URL}console
""",
to: 'sakthivel.kiaq@gmail.com'
)
}
}
}
