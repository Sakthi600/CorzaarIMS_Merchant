package pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class OverviewPage {

	private ElementUtils elementUtils;

	public OverviewPage(WebDriver driver) {

		this.elementUtils = new ElementUtils(driver);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//span[normalize-space()='Overview'])[1]")
	private WebElement overviewHeading;

	@FindBy(xpath = "//span[text()='Overview']")
	private WebElement overview;

	@FindBy(xpath = "//*[normalize-space()='Published Courses']/following::span[1]")
	private WebElement publishedCoursesLabel;

	@FindBy(xpath = "(//*[contains(normalize-space(.),' in review')])[10]")
	private WebElement coursesInReviewLabel;

	@FindBy(xpath = "//*[normalize-space()='Cohort Batches']/following::span[1]")
	private WebElement cohortBatchesLabel;

	@FindBy(xpath = "//*[normalize-space()='Student Enrollments']/following::span[1]")
	private WebElement studentEnrollmentsValue;

	@FindBy(xpath = "(//span[normalize-space()='Total Revenue'])[1]")
	private WebElement totalRevenueCard;

	@FindBy(xpath = "(//p[@class='text-xs sm:text-sm text-ink-muted dark:text-dark-ink-muted mt-1'])[1]")
	private WebElement accreditationPending;

	@FindBy(xpath = "(//h1[normalize-space()='My Institute'])[1]")
	private WebElement instituteName;

	@FindBy(xpath = "(//h1[normalize-space()='Course Studio'])[1]")
	private WebElement courseStudioHeading;

	@FindBy(xpath = "(//h1[normalize-space()='Batches & Attendance'])[1]")
	private WebElement batchesAttendanceHeading;

	@FindBy(xpath = "(//h1[normalize-space()='My Institute'])[1]")
	private WebElement myInstituteHeading;

	@FindBy(xpath = "(//span[normalize-space()='Create Course'])[1]")
	private WebElement createCourseButton;

	@FindBy(xpath = "(//span[normalize-space()='Add Batch'])[1]")
	private WebElement addBatchButton;

	@FindBy(xpath = "//*[text()='StudentEnrollments']/following::tr")
	private List<WebElement> studentEnrollRows;

	@FindBy(xpath = "(//h3[normalize-space()='Course Studio'])[1]")
	private WebElement courseStudioCard;

	@FindBy(xpath = "(//h3[normalize-space()='Batches & Attendance Matrix'])[1]")
	private WebElement batchesAttendanceCard;

	@FindBy(xpath = "(//h3[normalize-space()='Learner Drop-off & Funnel'])[1]")
	private WebElement learnerDropOffCard;

	private String getVisibleText(WebElement element) {
		return elementUtils.getText(element).trim();
	}

	private int extractFirstNumber(String text) {
		Matcher matcher = Pattern.compile("\\b\\d+\\b").matcher(text);

		if (matcher.find()) {
			return Integer.parseInt(matcher.group());
		}

		throw new AssertionError("Number not found in UI text: " + text);
	}

	public boolean isTotalRevenueDisplayed() {
		elementUtils.waitForElement(totalRevenueCard);
		return totalRevenueCard.isDisplayed();
	}

	public String getTotalRevenueText() {
		elementUtils.waitForElement(totalRevenueCard);
		return totalRevenueCard.getText();
	}

	public boolean isMyInstituteDisplayed() {
		elementUtils.waitForElement(myInstituteHeading);
		return myInstituteHeading.isDisplayed();
	}

	public boolean isAccreditationPending() {
		elementUtils.waitForElement(accreditationPending);
		return accreditationPending.isDisplayed();
	}

	public boolean isExpectedInstituteDisplayed() {
		elementUtils.waitForElement(instituteName);
		return instituteName.isDisplayed();

	}

	public boolean isCourseStudioPageDisplayed() {
		elementUtils.waitForElement(courseStudioHeading);
		return courseStudioHeading.isDisplayed();

	}

	public boolean isBatchesAttendancePageDisplayed() {
		elementUtils.waitForElement(batchesAttendanceHeading);
		return batchesAttendanceHeading.isDisplayed();
	}

	public boolean isOverviewDisplayed() {
		elementUtils.waitForElement(overviewHeading);
		return overviewHeading.isDisplayed();
	}

	public int getStudentEnrollmentsCount() {
		elementUtils.waitForElement(studentEnrollmentsValue);
		String text = studentEnrollmentsValue.getText().trim();

		System.out.println("Student Enrollments value: " + text);

		return extractFirstNumber(text);
	}

	public int getPublishedCoursesCount() {
		return extractFirstNumber(getVisibleText(publishedCoursesLabel));
	}

	public int getCoursesInReviewCount() {
		return extractFirstNumber(getVisibleText(coursesInReviewLabel));
	}

	public int getAttendanceTrackingCount() {
		return extractFirstNumber(getVisibleText(cohortBatchesLabel));
	}

	public boolean isCreateCourseDisplayed() {
		elementUtils.waitForElement(createCourseButton);
		return createCourseButton.isDisplayed();
	}

	public boolean isAddBatchDisplayed() {
		elementUtils.waitForElement(addBatchButton);
		return addBatchButton.isDisplayed();
	}

	public int getStudentEnrollmentsTableCount() {

		elementUtils.waitForAllElement(studentEnrollRows);

		return studentEnrollRows.size();

	}

	public void clickCourseStudioCard() {
		elementUtils.click(courseStudioCard);
	}

	public void clickBatchesAttendanceCard() {
		elementUtils.click(batchesAttendanceCard);
	}

	public void clickLearnerDropOffCard() {
		elementUtils.click(learnerDropOffCard);
	}

	public void clickCreateCourse() {
		elementUtils.click(createCourseButton);
	}

	public void openOverview() {
		elementUtils.click(overview);
	}

	public void clickAddBatch() {
		elementUtils.click(addBatchButton);
	}

	public void clickOverview() {
		elementUtils.click(overviewHeading);
	}

}
