package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.MerchantDashboardPage;
import pages.OtpPage;
import utils.ConfigReader;
import utils.DriverFactory;

public class LoginSteps {

	private LoginPage loginPage;
	private OtpPage otpPage;
	private MerchantDashboardPage dashboardPage;
	
	@Given("user opens the Corzaar IMS merchant login page")
	public void userOpensTheCorzaarIMSMerchantLoginPage() {
		loginPage = new LoginPage(DriverFactory.getDriver());
		DriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
	}
	@When("user enters the institute mobile number")
	public void userEntersTheInstituteMobileNumber() {
		loginPage.enterMobileNumber(ConfigReader.getProperty("instituteMobile"));
	}
	@When("user enters the institute contact person name")
	public void userEntersTheInstituteContactPersonName() {
		loginPage.enterContactPerson(ConfigReader.getProperty("instituteContact"));
	}
	@When("user clicks on Send Institute Verification Code")
	public void userClicksOnSendInstituteVerificationCode() {
		loginPage.clickSendVerificationCode();
	}
	@Then("OTP verification page should be displayed")
	public void otpVerificationPageShouldBeDisplayed() {
		otpPage = new OtpPage(DriverFactory.getDriver());

		Assert.assertTrue(otpPage.isOtpVerificationPageDisplayed(), "OTP verification page was not displayed");
	}
	@When("user enters the valid OTP")
	public void userEntersTheValidOTP() {
		otpPage.enterOtp(ConfigReader.getProperty("otp"));
	}
	@When("user clicks on Verify and Enter Merchant Studio")
	public void userClicksOnVerifyAndEnterMerchantStudio() {
		otpPage.clickVerify();
	}
	@Then("Merchant Studio dashboard should be displayed")
	public void merchantStudioDashboardShouldBeDisplayed() {
		dashboardPage = new MerchantDashboardPage(DriverFactory.getDriver());

		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Merchant Studio dashboard was not displayed");
	}
	@Then("Overview menu should be displayed")
	public void overviewMenuShouldBeDisplayed() {
		Assert.assertTrue(dashboardPage.isOverviewDisplayed(), "Overview menu was not displayed");
	}
	@Then("My Institute should be displayed")
	public void myInstituteShouldBeDisplayed() {
		Assert.assertTrue(dashboardPage.isMyInstituteDisplayed(), "My Institute was not displayed");
	}
	@Then("dashboard summary should be displayed")
	public void dashboardSummaryShouldBeDisplayed() {
		Assert.assertTrue(dashboardPage.isDashboardDataDisplayed(), "Dashboard summary was not displayed");
	}
	@When("user executes login negative case {string} using mobile key {string} and OTP key {string}")
	public void userExecutesLoginNegativeCaseUsingMobileKeyAndOTPKey(String testType, String mobileKey, String otpKey) {
		String mobile = mobileKey;
		loginPage = new LoginPage(DriverFactory.getDriver());

		switch (testType) {
		case "blank_mobile":
		case "alphabet":
		case "special_char":
		case "short_mobile":
		case "long_mobile":
			loginPage.enterMobileNumber(mobile == null ? "" : mobile);
			loginPage.clickSendVerificationCode();
			break;

		case "alphabet_otp":
		case "invalid_otp":
			loginPage.enterMobileNumber(mobile);
			loginPage.enterContactPerson(ConfigReader.getProperty("instituteContact"));
			loginPage.clickSendVerificationCode();

			otpPage = new OtpPage(DriverFactory.getDriver());
			otpPage.enterOtp(otpKey);
			otpPage.clickVerify();
			break;

		case "change_mobile":
			loginPage.enterMobileNumber(mobile);
			loginPage.enterContactPerson(ConfigReader.getProperty("instituteContact"));
			loginPage.clickSendVerificationCode();

			otpPage = new OtpPage(DriverFactory.getDriver());
			Assert.assertTrue(otpPage.isOtpVerificationPageDisplayed(), "OTP page was not displayed");
			loginPage.clickChangeButton();
			loginPage.enterMobileNumber(mobile);
			break;

		default:
			throw new IllegalArgumentException("Unknown login test type: " + testType);
		}
	}
	@Then("login negative case {string} should be validated")
	public void loginNegativeCaseShouldBeValidated(String testType) {
	   
		switch (testType) {
		case "blank_mobile":
			Assert.assertFalse(new OtpPage(DriverFactory.getDriver()).isOtpVerificationPageDisplayed(),
					"OTP page should not open for blank mobile");
			break;

		case "alphabet":
		case "special_char":
		case "short_mobile":
		case "long_mobile":
			Assert.assertFalse(new OtpPage(DriverFactory.getDriver()).isOtpVerificationPageDisplayed(),
					"OTP page should not open for invalid mobile");
			break;

		case "alphabet_otp":
		case "invalid_otp":
			Assert.assertTrue(new OtpPage(DriverFactory.getDriver()).isInvalidOtpMessageDisplayed(),
					"Expected invalid OTP error was not displayed");
			break;

		case "change_mobile":
			Assert.assertTrue(loginPage.isMobileValueAccepted(),
					"Changed mobile number was not displayed");
			break;

		default:
			throw new IllegalArgumentException("Unknown login test type: " + testType);
		}
	}

}
