package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(features = "src/test/resources/features", glue = { "stepdefinitions", "hooks" }, plugin = { "pretty",
		"html:target/cucumber-report.html", "json:target/cucumber.json",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }, tags = "@logout", dryRun = !true, snippets = SnippetType.CAMELCASE, monochrome = false, publish = false)
public class TestRunner extends AbstractTestNGCucumberTests {
}
