package hooks;

import io.cucumber.java.After;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.LoginPage;
import pages.OtpPage;
//import pages.LoginPage;
//import pages.OtpPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class Hooks {

	@Before(order = 0)
	public void setUp(Scenario scenario) {

		System.out.println("========== TEST STARTED ==========");

		DriverFactory.initializeDriver();

		/*
		 * Login scenarios perform login inside LoginSteps.java.
		 *
		 * Therefore automatic login must be skipped.
		 */

		if (scenario.getSourceTagNames().contains("@login")) {

			System.out.println("LOGIN SCENARIO - AUTO LOGIN SKIPPED");

			return;
		}

		performValidLogin();
	}

	// =========================================================
	// COMMON VALID LOGIN
	// =========================================================

	private void performValidLogin() {

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

		DriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));

		loginPage.enterMobileNumber(ConfigReader.getProperty("instituteMobile"));

		loginPage.enterContactPerson(ConfigReader.getProperty("instituteContact"));

		loginPage.clickSendVerificationCode();

		OtpPage otpPage = new OtpPage(DriverFactory.getDriver());

		otpPage.enterOtp(ConfigReader.getProperty("otp"));

		otpPage.clickVerify();

		System.out.println("========== LOGIN & OTP COMPLETED ==========");
	}

	// =========================================================
	// TEARDOWN
	// =========================================================

	@After
	public void tearDown() {

		System.out.println("========== TEST COMPLETED ==========");

		DriverFactory.quitDriver();
	}
}