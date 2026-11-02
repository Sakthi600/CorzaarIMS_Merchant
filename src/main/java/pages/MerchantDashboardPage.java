package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;

public class MerchantDashboardPage {

	private ElementUtils elementUtils;

	public MerchantDashboardPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		this.elementUtils = new ElementUtils(driver);

	}

	@FindBy(xpath = "//span[text()='Overview']")
	private WebElement overviewMenu;

	@FindBy(xpath = "//span[text()='Merchant Studio']")
	private WebElement merchantStudioText;

	@FindBy(xpath = "//h1[text()='My Institute']")
	private WebElement myInstituteText;

	@FindBy(xpath = "//span[text()='Total Revenue']")
	private WebElement totalRevenue;

	@FindBy(xpath = "//*[contains(.,'STUDENT ENROLLMENTS')]")
	private WebElement studentEnrollments;

	@FindBy(xpath = "//*[contains(.,'PUBLISHED COURSES')]")
	private WebElement publishedCourses;

	@FindBy(xpath = "//*[contains(.,'COHORT BATCHES')]")
	private WebElement cohortBatches;

	public boolean isDashboardDisplayed() {

		return elementUtils.isDisplayed(merchantStudioText);
	}

	public boolean isOverviewDisplayed() {

		return elementUtils.isDisplayed(overviewMenu);
	}

	public boolean isMyInstituteDisplayed() {

		return elementUtils.isDisplayed(myInstituteText);
	}

	public boolean isDashboardDataDisplayed() {

		return elementUtils.isDisplayed(totalRevenue);
	}

	public void clickOverview() {

		elementUtils.click(overviewMenu);
	}
}
