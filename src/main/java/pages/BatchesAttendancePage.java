package pages;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtils;

public class BatchesAttendancePage {

	private WebDriver driver;

	private ElementUtils elementUtils;

	public BatchesAttendancePage(WebDriver driver) {

		this.driver = driver;

		this.elementUtils = new ElementUtils(driver);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Batches & Attendance']")
	private WebElement batchesAttendanceMenu;

	@FindBy(xpath = "//span[normalize-space()='Create Cohort Batch']")
	private WebElement createCohortBatchButton;

	@FindBy(xpath = "(//h1[normalize-space()='Batches & Attendance'])[1]")
	private WebElement batchesAttendanceHeading;

	@FindBy(xpath = "//select[@id='select-course']")
	private WebElement selectCourseDropdown;

	@FindBy(xpath = "(//input[@id='course-coordinator-/-instructor'])[1]")
	private WebElement coordinator;

	@FindBy(xpath = "//input[@id='weekly-schedule-(days)']")
	private WebElement weeklySchedule;

	@FindBy(xpath = "//input[@id='start-date']")
	private WebElement startDate;

	@FindBy(xpath = "//input[@id='end-date']")
	private WebElement endDate;

	@FindBy(xpath = "//input[@id='cohort-seat-capacity']")
	private WebElement seatCapacity;

	@FindBy(xpath = "//span[text()='Generate Sessions & Schedule']")
	private WebElement generateSessionsButton;

	@FindBy(xpath = "(//span[normalize-space()='Cancel'])[1]")
	private WebElement cancelButton;

	@FindBy(xpath = "(//h1[normalize-space()='Batches & Attendance'])[1]")
	private WebElement batchesPage;

	@FindBy(xpath = "//*[normalize-space()='Selenium Automation Testing']")
	private WebElement createdBatchName;

	@FindBy(xpath = "(//span[contains(text(),'Roster Matrix')])[1]")
	private WebElement rosterMatrixButton;

	@FindBy(xpath = "//*[normalize-space()='Roster Matrix']")
	private WebElement rosterMatrixHeading;

	@FindBy(xpath = "(//button[@aria-label='Close modal'])[1]")
	private WebElement rosterMatrixCloseButton;

	@FindBy(xpath = "//*[text()='Batch & Program']/following::tr")
	private List<WebElement> batchRows;

	@FindBy(xpath = "//*[text()='Batch & Program']/following::tr/td[1]")
	private List<WebElement> batchNameElements;

	public void openBatchesAttendance() {

		elementUtils.click(batchesAttendanceMenu);
	}

	public void clickCreateCohortBatch() {

		elementUtils.click(createCohortBatchButton);
	}

	public void refreshBatchPage() {
		elementUtils.refreshPage();
	}

	public void selectCourse(int index) {

		elementUtils.waitForClickable(selectCourseDropdown);

		Select select = new Select(selectCourseDropdown);
		select.selectByIndex(index);

	}

	public void enterCoordinator(String name) {

		elementUtils.clear(coordinator);
		elementUtils.fillValue(coordinator, name);
	}

	public void enterWeeklySchedule(String days) {

		elementUtils.clear(weeklySchedule);
		elementUtils.fillValue(weeklySchedule, days);
	}

	public void enterStartDate(String date) {

		elementUtils.clear(startDate);
		elementUtils.fillValue(startDate, date);
	}

	public void enterEndDate(String date) {

		elementUtils.clear(endDate);
		elementUtils.fillValue(endDate, date);
	}

	public void enterSeatCapacity(String capacity) {

		elementUtils.clear(seatCapacity);
		elementUtils.fillValue(seatCapacity, capacity);
	}

	public void generateSessions() {

		elementUtils.click(generateSessionsButton);
	}

	public void clickCancel() {
		elementUtils.click(cancelButton);
	}

	public boolean isCreatedBatchDisplayed() {
		return createdBatchName.isDisplayed();
	}

	public void clickRosterMatrixForCreatedBatch() {

		elementUtils.waitForClickable(rosterMatrixButton);

		elementUtils.click(rosterMatrixButton);
	}

	public String getRosterMatrixText() {
		elementUtils.waitForElement(rosterMatrixHeading);
		return rosterMatrixHeading.getText();
	}

	public boolean isBatchesDisplayed() {
		return rosterMatrixHeading.isDisplayed();
	}

	public boolean isRosterMatrixDisplayed() {
		return rosterMatrixHeading.isDisplayed();
	}

	public void closeRosterMatrix() {
		elementUtils.click(rosterMatrixCloseButton);
	}

	public void createCohortBatch() {
		elementUtils.click(createCohortBatchButton);
	}

	public String getValidationMessage(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		return (String) js.executeScript("return arguments[0].validationMessage;", element);
	}

	public boolean isNativeValidationDisplayed(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Boolean valid = (Boolean) js.executeScript("return arguments[0].checkValidity();", element);

		String message = (String) js.executeScript("return arguments[0].validationMessage;", element);

		System.out.println("Valid: " + valid);
		System.out.println("Validation Message: " + message);

		return Boolean.FALSE.equals(valid) && message != null && !message.trim().isEmpty();
	}

	public boolean isBatchValidationDisplayed(String testCase) {

		switch (testCase) {

		case "MissingCoordinator":
			return isNativeValidationDisplayed(coordinator);

		case "MissingWeeklySchedule":
			return isNativeValidationDisplayed(weeklySchedule);

		case "PastStartDate":
		case "OldYearStartDate":
			return isNativeValidationDisplayed(startDate);

		case "PastEndDate":
		case "InvalidEndDate":
			return isNativeValidationDisplayed(endDate);

		case "ZeroSeatCapacity":
		case "BelowMinimumCapacity":
		case "NegativeSeatCapacity":
			return isNativeValidationDisplayed(seatCapacity);

		default:
			throw new IllegalArgumentException("Unknown test case: " + testCase);
		}
	}

	public boolean isBatchDisplayed(String batchName) {

		if (batchName == null || batchName.trim().isEmpty()) {
			throw new IllegalArgumentException("Batch name is empty");
		}

		elementUtils.refreshPage();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {
			return wait.until(d -> {
				for (WebElement row : batchRows) {
					try {
						String rowText = row.getText().trim();

						System.out.println("Actual batch row: " + rowText);

						if (rowText.toLowerCase().contains(batchName.trim().toLowerCase())) {
							return true;
						}
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}
				return false;
			});
		} catch (TimeoutException e) {
			System.out.println("Batch not found: " + batchName);
			return false;
		}
	}

	public String getCreatedBatchName() {

		elementUtils.waitForAllElement(batchRows);

		for (int i = 0; i < batchNameElements.size(); i++) {

			String batchName = batchNameElements.get(i).getText().trim();

			if (!batchName.isEmpty()) {
				System.out.println("Batch Name: " + batchName);
				return batchName;
			}
		}

		throw new RuntimeException("No batch name found in the table");
	}

	public int getBatchTableCount() {

		elementUtils.waitForAllElement(batchRows);

		return batchRows.size();

	}

	public boolean isBatchesAttendancePageDisplayed() {
		elementUtils.waitForElement(batchesAttendanceHeading);
		return batchesAttendanceHeading.isDisplayed();
	}

}