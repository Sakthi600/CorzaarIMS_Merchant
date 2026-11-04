package pages;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class OtpPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	public OtpPage(WebDriver driver) {

		this.driver = driver;

		this.elementUtils = new ElementUtils(driver);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@id='enter-6-digit-passcode'])[1]")
	private WebElement otpField;

	@FindBy(xpath = "//span[text()='Verify & Enter Merchant Studio']")
	private WebElement verifyButtonElement;

	@FindBy(xpath = "(//button[@type='submit'])[1]")
	private WebElement otpVerificationPage;

	@FindBy(xpath = "//*[text()='Invalid OTP code']")
	private WebElement invalidOtpMessage;

	public void enterOtp(String otp) {

		elementUtils.fillValue(otpField, otp);
	}

	public void clickVerify() {

		elementUtils.waitForClickable(verifyButtonElement);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",
				verifyButtonElement);

		try {
			verifyButtonElement.click();
		} catch (ElementNotInteractableException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", verifyButtonElement);
		}
	}

	public boolean isOtpVerificationPageDisplayed() {
		try {
			return otpField.isDisplayed() && otpField.isEnabled();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public String getOtpValue() {
		return otpField.getAttribute("value");
	}

	public String getOtpValidationMessage() {
		return otpField.getAttribute("validationMessage");
	}

	public boolean isInvalidOtpMessageDisplayed() {
		return elementUtils.isDisplayed(invalidOtpMessage);
	}

}