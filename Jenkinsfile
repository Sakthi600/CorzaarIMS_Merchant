pipeline {
 agent any

 options {
 timestamps()
 disableConcurrentBuilds()
 skipDefaultCheckout(true)
 }

 stages {

 // 1. GitHub Checkout
 stage('Checkout') {
 steps {
 echo 'Checking out GitHub repository'
 checkout scm
 }
 }

 // 2. Environment Check
 stage('Environment Check') {
 steps {
 bat 'java -version'
 bat 'mvn -version'
 bat 'node --version'
 bat 'npm --version'
 }
 }

 // 3. Selenium Automation
 stage('Run Automation') {
 steps {
 catchError(
 buildResult: 'FAILURE',
 stageResult: 'FAILURE'
 ) {
 bat 'mvn clean test'
 }
 }
 }

 // 4. Generate Complete Allure HTML
 stage('Generate Allure HTML Report') {
 steps {
 echo 'Generating Allure HTML Report'

 bat '''
 if not exist "AllureReports\\allure-results" (
 echo Allure results folder not found
 exit /b 1
 )

 if not exist "AllureReports" (
 mkdir "AllureReports"
 )

 call npx allure generate "AllureReports\\allure-results" --clean --output "AllureReports\\allure-report-%BUILD_NUMBER%"

 if not exist "AllureReports\\allure-report-%BUILD_NUMBER%\\index.html" (
 echo Allure index.html was not generated
 exit /b 1
 )
 '''
 }
 }

 // 5. Publish Allure HTML
 stage('Publish Allure HTML Report') {
 steps {
 publishHTML(target: [
 reportDir: "AllureReports/allure-report-${env.BUILD_NUMBER}",
 reportFiles: 'index.html',
 reportName: 'Allure HTML Report',
 keepAll: true,
 alwaysLinkToLastBuild: true,
 allowMissing: false
 ])
 }
 }

 // 6. Jenkins Allure Dashboard
 stage('Allure Dashboard') {
 steps {
 allure([
 commandline: 'Allure-3',
 includeProperties: false,
 results: [[
 path: 'AllureReports/allure-results'
 ]]
 ])
 }
 }

 // 7. Create Full HTML ZIP
 stage('Prepare Allure Email Attachment') {
 steps {
 echo 'Creating complete Allure HTML ZIP'

 bat '''
 powershell -NoProfile -ExecutionPolicy Bypass -Command "
 $src = 'AllureReports\\allure-report-' + $env:BUILD_NUMBER
 $zip = 'AllureReports\\Allure-HTML-Report-' + $env:BUILD_NUMBER + '.zip'

 if (!(Test-Path (Join-Path $src 'index.html'))) {
 throw 'Allure index.html not found'
 }

 Compress-Archive -Path (Join-Path $src '*') -DestinationPath $zip -Force

 Write-Host 'Allure HTML ZIP created successfully'
 "
 '''
 }
 }
 }

 // 8. Post Build Actions
 post {

 always {
 echo 'Archiving automation reports'

 archiveArtifacts(
 artifacts: "AllureReports/Allure-HTML-Report-${env.BUILD_NUMBER}.zip,target/cucumber-report.html",
 allowEmptyArchive: true
 )
 }

 success {
 echo 'Automation execution SUCCESS'

 emailext(
 subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
 mimeType: 'text/html',
 body: """
 <h2>Automation Execution Successful</h2>

 <p><b>Job:</b> ${JOB_NAME}</p>
 <p><b>Build:</b> ${BUILD_NUMBER}</p>
 <p><b>Status:</b> SUCCESS</p>

 <p>Allure HTML Report is attached as a ZIP file.</p>

 <p>
 <a href="${BUILD_URL}">
 Open Jenkins Build
 </a>
 </p>

 <p>Regards,<br>Automation Team</p>
 """,
 attachmentsPattern: "AllureReports/Allure-HTML-Report-${env.BUILD_NUMBER}.zip",
 to: 'sakthivel.kiaq@gmail.com'
 )
 }

 failure {
 echo 'Automation execution FAILED'

 emailext(
 subject: "FAILURE: ${JOB_NAME} #${BUILD_NUMBER}",
 mimeType: 'text/html',
 body: """
 <h2>Automation Execution Failed</h2>

 <p><b>Job:</b> ${JOB_NAME}</p>
 <p><b>Build:</b> ${BUILD_NUMBER}</p>
 <p><b>Status:</b> FAILURE</p>

 <p>Please check Jenkins Console Output.</p>

 <p>
 <a href="${BUILD_URL}console">
 View Console Output
 </a>
 </p>

 <p>Regards,<br>Automation Team</p>
 """,
 attachmentsPattern: "AllureReports/Allure-HTML-Report-${env.BUILD_NUMBER}.zip",
 to: 'sakthivel.kiaq@gmail.com'
 )
 }
 }
}
