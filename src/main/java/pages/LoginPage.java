package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
		this.elementUtils = new ElementUtils(driver);

	}

	@FindBy(xpath = "(//input[@id='institute-manager-mobile-number'])[1]")
	private WebElement mobileNumber;

	@FindBy(xpath = "(//input[@id='institute-contact-person-/-faculty-name'])[1]")
	private WebElement contactPerson;

	@FindBy(xpath = "//button[.//span[normalize-space()='Send Institute Verification Code']]")
	private WebElement sendVerificationCode;

	@FindBy(xpath = "//button[normalize-space()='Change'] | //span[normalize-space()='Change']")
	private WebElement changeButton;

// Add these fields inside your existing LoginPage class

	@FindBy(xpath = "(//button[@title='Sign out'])[1]")
	private WebElement logoutButton;

	@FindBy(xpath = "(//input[@id='learner-mobile-number'])[1]")
	private WebElement learnermobileNumber;

// Add these methods inside your existing LoginPage class

	public void clickLogout() {
		elementUtils.click(logoutButton);
	}

	public boolean isLoginPageDisplayed() {
		return elementUtils.isDisplayed(learnermobileNumber);
	}

	public void enterMobileNumber(String mobile) {

		elementUtils.fillValue(mobileNumber, mobile);
	}

	public void enterContactPerson(String name) {

		elementUtils.fillValue(contactPerson, name);
	}

	public String getMobileNumberValue() {
		return mobileNumber.getAttribute("value");
	}

	public String getMobileValidationMessage() {
		return mobileNumber.getAttribute("validationMessage");
	}

	public boolean isMobileValueAccepted() {
		String currentNumber = getMobileNumberValue();
		return currentNumber != null && !currentNumber.trim().isEmpty();
	}

	public void clickSendVerificationCode() {

		elementUtils.waitForElement(sendVerificationCode);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",
				sendVerificationCode);

		elementUtils.waitForClickable(sendVerificationCode);

		try {
			sendVerificationCode.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sendVerificationCode);
		}
	}

	public void clickChangeButton() {
		elementUtils.click(changeButton);
	}

}