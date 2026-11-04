import { defineConfig } from "allure";
 
export default defineConfig({
name: "SauceDemo Automation Report",
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