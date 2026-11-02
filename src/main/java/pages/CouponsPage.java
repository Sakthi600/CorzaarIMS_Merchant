package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtils;

public class CouponsPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	public CouponsPage(WebDriver driver) {

		this.elementUtils = new ElementUtils(driver);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='Coupons & Promos']")
	private WebElement couponsMenu;

	@FindBy(xpath = "//span[normalize-space()='Create Coupon']")
	private WebElement createCouponButton;

	@FindBy(xpath = "//input[@id='coupon-code']")
	private WebElement couponCode;

	@FindBy(xpath = "//input[@id='discount-percentage-(%)']")
	private WebElement discountPercentage;

	@FindBy(xpath = "//input[@id='usage-limit']")
	private WebElement usageLimit;

	@FindBy(xpath = "//span[normalize-space()='Submit for Approval']")
	private WebElement submitApprovalButton;

	@FindBy(xpath = "(//span[normalize-space()='Cancel'])[1]")
	private WebElement cancelButton;

	@FindBy(xpath = "(//th[normalize-space()='Status'])[1]")
	private WebElement pendingStatus;

	@FindBy(xpath = "//*[@role='alert']")
	private WebElement validationMessage;

	@FindBy(xpath = "//table/child::tbody/child::tr")
	private List<WebElement> couponRows;

	public void openCoupons() {

		elementUtils.click(couponsMenu);
	}

	public void clickCreateCoupon() {

		elementUtils.click(createCouponButton);
	}

	public void enterCouponCode(String code) {

		elementUtils.clear(couponCode);
		elementUtils.fillValue(couponCode, code);
	}

	public void enterDiscountPercentage(String discount) {

		elementUtils.clear(discountPercentage);
		elementUtils.fillValue(discountPercentage, discount);
	}

	public void enterusageLimit(String usagelimit) {

		elementUtils.clear(usageLimit);
		elementUtils.fillValue(usageLimit, usagelimit);
	}

	public void submitForApproval() {

		elementUtils.click(submitApprovalButton);
	}

	public void clickCancel() {
		elementUtils.click(cancelButton);
	}

	public boolean isCouponPending(String couponCode) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		return wait.until(d -> {

			for (WebElement row : couponRows) {

				try {
					String rowText = row.getText().replaceAll("\\s+", " ").trim().toLowerCase();

					System.out.println("Coupon Table Row: " + rowText);

					if (rowText.contains(couponCode.trim().toLowerCase()) && rowText.contains("pending")) {

						System.out.println("Coupon found with Pending status: " + couponCode);

						return true;
					}

				} catch (StaleElementReferenceException e) {
					// Retry when table refreshes
				}
			}

			return false;
		});
	}

	public boolean isCouponFormStillDisplayed() {
		try {
			return elementUtils.isDisplayed(couponCode);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isCouponValidationDisplayed() {
		try {
			return elementUtils.isDisplayed(validationMessage);
		} catch (Exception e) {
		}
		return false;
	}

}