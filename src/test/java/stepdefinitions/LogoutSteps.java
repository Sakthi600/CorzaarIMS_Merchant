package stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import utils.DriverFactory;

public class LogoutSteps {
	private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

	@When("user clicks Logout")
	public void userClicksLogout() {
		loginPage.clickLogout();
	}

	@Then("user should be redirected to the login page")
	public void userShouldBeRedirectedToLoginPage() {
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Logout failed: Login page was not displayed");
	}
}