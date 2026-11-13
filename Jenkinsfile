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
 bat 'npx allure --version'
 }
 }

 // 3. Selenium Automation
 stage('Run Automation') {
 steps {
 bat 'mvn clean test'
 }
 }

 // 4. Verify Allure Results
 stage('Check Allure Results') {
 steps {
 bat '''
 echo ===== CHECK ALLURE RESULTS =====

 if not exist "AllureReports\\allure-results" (
 echo ERROR: Allure results folder not found
 exit /b 1
 )

 echo Allure results folder found

 dir "AllureReports\\allure-results"

 echo ===== CHECK RESULT JSON =====

 dir /b "AllureReports\\allure-results\\*-result.json" 2>nul

 if errorlevel 1 (
 echo ERROR: No Allure result JSON files found
 exit /b 1
 )

 echo Allure results verified successfully
 '''
 }
 }

 // 5. Generate Allure HTML
 stage('Generate Allure HTML Report') {
 steps {
 bat '''
 @echo off

 echo ===== GENERATING ALLURE HTML =====

 if not exist "AllureReports" mkdir "AllureReports"

 set "REPORT_DIR=AllureReports\\allure-report-%BUILD_NUMBER%"

 call npx allure generate "AllureReports\\allure-results" --clean --output "%REPORT_DIR%"

 if errorlevel 1 (
 echo ERROR: Allure generation failed
 exit /b 1
 )

 if not exist "%REPORT_DIR%\\index.html" (
 echo ERROR: index.html not generated
 exit /b 1
 )

 echo SUCCESS: Allure index.html generated

 dir "%REPORT_DIR%"
 '''
 }
 }

 // 6. Publish Allure HTML
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

 // 7. Prepare Full Report ZIP
 stage('Prepare Allure Email Attachment') {
steps {
bat '''
if not exist "AllureReports\\allure-report-%BUILD_NUMBER%\\index.html" (
echo Allure index.html not found
exit /b 1
)

powershell -NoProfile -ExecutionPolicy Bypass -Command "Compress-Archive -Path 'AllureReports\\allure-report-%BUILD_NUMBER%\\*' -DestinationPath 'AllureReports\\Allure-HTML-Report-%BUILD_NUMBER%.zip' -Force"

if not exist "AllureReports\\Allure-HTML-Report-%BUILD_NUMBER%.zip" (
echo ZIP file creation failed
exit /b 1
)

echo Allure HTML ZIP created successfully
'''
}
}
}

 // 8. Post Build Actions
 post {

 always {
 echo 'Archiving automation reports'

 archiveArtifacts(
 artifacts: "AllureReports/allure-report-${env.BUILD_NUMBER}/**/*,AllureReports/Allure-HTML-Report-${env.BUILD_NUMBER}.zip",
 allowEmptyArchive: true
 )
 }

 success {
 echo 'Automation execution SUCCESS'

 emailext(
 subject: "SUCCESS: ${JOB_NAME} #${BUILD_NUMBER}",
 mimeType: 'text/html',

 body: """
 <html>
 <body>
 <h2>Automation Execution Successful</h2>

 <p><b>Job:</b> ${JOB_NAME}</p>
 <p><b>Build:</b> ${BUILD_NUMBER}</p>
 <p><b>Status:</b> SUCCESS</p>

 <p>Complete Allure HTML Report is attached as ZIP.</p>

 <p>
 <a href="${BUILD_URL}">
 Open Jenkins Build
 </a>
 </p>

 <p>Regards,<br>Automation Team</p>
 </body>
 </html>
 """,

 attachmentsPattern:
 "AllureReports/Allure-HTML-Report-${env.BUILD_NUMBER}.zip",

 to: 'sathishsezhian@gmail.com'
 )
 }

 failure {
 echo 'Automation execution FAILED'

 emailext(
 subject: "FAILURE: ${JOB_NAME} #${BUILD_NUMBER}",
 mimeType: 'text/html',

 body: """
 <html>
 <body>
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
 </body>
 </html>
 """,

 attachmentsPattern:
 "AllureReports/Allure-HTML-Report-${env.BUILD_NUMBER}.zip",

 to: 'sakthivel.kiaq@gmail.com'
 )
 }
 }
}