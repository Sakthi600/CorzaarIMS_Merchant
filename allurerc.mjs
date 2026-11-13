import { defineConfig } from "allure";
 
export default defineConfig({
name: "CorzaarIMS-Merchant Automation Report",
output: "./AllureReports/allure-report",
 
plugins: {
awesome: {
options: {
singleFile: true,
reportLanguage: "en"
}
}
}
});