package pages;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverFactory;
import utils.ElementUtils;

public class CourseStudioPage {

	private WebDriver driver;

	private WebDriverWait wait;
	private ElementUtils elementUtils;

	public CourseStudioPage() {

		this.driver = DriverFactory.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Course Studio']")
	private WebElement courseStudioMenu;

	@FindBy(xpath = "//span[text()='New Course Program']")
	private WebElement createCourseButton;

	@FindBy(xpath = "(//h1[normalize-space()='Course Studio'])[1]")
	private WebElement courseStudioHeading;

	@FindBy(xpath = "//input[@id='course-title']")
	private WebElement courseTitle;

	@FindBy(xpath = "//input[@id='tuition-fee-(₹)']")
	private WebElement tuitionFee;

	@FindBy(xpath = "//input[@id='program-duration']")
	private WebElement programDuration;

	@FindBy(xpath = "//textarea[@id='course-overview-&-learning-outcomes']")
	private WebElement courseOverview;

	@FindBy(xpath = "(//input[@id='module-1-title'])[1]")
	private WebElement moduleTitle;

	@FindBy(xpath = "(//span[normalize-space()='Cancel'])[1]")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[contains(.,'Add Module')]")
	private WebElement addModuleButton;

	@FindBy(xpath = "//input[starts-with(@id,'module-') and contains(@id,'-title')]")
	private List<WebElement> allModuleTitles;

	@FindBy(css = "button.absolute.top-3.right-3")
	private WebElement courseModalCloseButton;

	@FindBy(xpath = "//*[normalize-space()='Create New Course Program']")
	private WebElement courseModalHeading;

	@FindBy(id = "module-1-title")
	private WebElement moduleTitleField;

	@FindBy(id = "module-1-description")
	private WebElement moduleDescriptionField;

	@FindBy(id = "module-1-duration")
	private WebElement moduleDurationField;

	@FindBy(xpath = "//span[text()='Publish Course']")
	private WebElement publishCourseButton;

	@FindBy(xpath = "//button[@title='Edit Course']")
	private List<WebElement> editCourseButtons;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;

	@FindBy(id = "course-program-modal")
	private WebElement courseProgramModal;

	@FindBy(xpath = "//span[normalize-space()='Yes, Delete Course']")
	private WebElement confirmDeleteButton;

	@FindBy(xpath = "//button[@title='Delete Course']")
	private List<WebElement> deleteCourseButtons;

	@FindBy(xpath = "//td[1]/ancestor::table//tbody/tr")
	private List<WebElement> courseRows;

	@FindBy(xpath = "//table/descendant::tbody/tr/child::td[1]")
	private List<WebElement> courseNameElements;

	@FindBy(xpath = "//table/descendant::tbody/tr/child::td[4]")
	private List<WebElement> courseStatusElements;

	@FindBy(xpath = ".//button[@title='Delete Course']")
	private List<WebElement> buttons;

	public void openCourseStudio() {

		elementUtils.click(courseStudioMenu);
	}

	public void clickCreateCourse() {

		elementUtils.click(createCourseButton);
	}

	public void enterCourseTitle(String title) {

		elementUtils.fillValue(courseTitle, title);
	}

	public void enterTuitionFee(String fee) {

		elementUtils.fillValue(tuitionFee, fee);
	}

	public void enterProgramDuration(String duration) {

		elementUtils.fillValue(programDuration, duration);
	}

	public void enterCourseOverview(String overview) {

		elementUtils.fillValue(courseOverview, overview);
	}

	public void enterModuleTitle(String title) {

		elementUtils.fillValue(moduleTitle, title);
	}

	public void publishCourse() {

		elementUtils.click(publishCourseButton);
	}

	public void clickCancel() {
		elementUtils.click(cancelButton);
	}

	public void clickAddModule() {
//		int previousCount = allModuleTitles.size();

		elementUtils.waitForElement(addModuleButton);
		elementUtils.click(addModuleButton);

//		wait.until(driver -> allModuleTitles.size() > previousCount);
	}

	public int getModuleCount() {
		return allModuleTitles.size();
	}

	public void testCourseValidation(String testCase) {

		WebElement field;

		switch (testCase) {

		case "MissingTitle":
			field = courseTitle;
			break;

		case "MissingTuitionFee":
			field = tuitionFee;
			break;

		case "MissingDuration":
			field = programDuration;
			break;

		case "MissingOverview":
			field = courseOverview;
			break;

		case "MissingModuleTitle":
			field = moduleTitleField;
			break;

		default:
			throw new IllegalArgumentException("Unknown test case: " + testCase);
		}

		field.clear();
	}

	public void closeCourseProgramModal() {

		// Wait until the close button is clickable
		elementUtils.waitForClickable(courseModalCloseButton);
		elementUtils.click(courseModalCloseButton);

		// Wait until the actual modal is closed
		elementUtils.waitForInvisibility(courseProgramModal);
	}

	public boolean isCourseProgramModalClosed() {
		elementUtils.waitForInvisibility(courseProgramModal);
		return true;
	}

	public boolean isValidationDisplayed(String testCase) {

		WebElement field;

		switch (testCase) {

		case "MissingTitle":
			field = courseTitle;
			break;

		case "MissingTuitionFee":
			field = tuitionFee;
			break;

		case "MissingDuration":
			field = programDuration;
			break;

		case "MissingOverview":
			field = courseOverview;
			break;

		case "MissingModuleTitle":
			field = moduleTitleField;
			break;

		default:
			throw new IllegalArgumentException("Unknown test case: " + testCase);
		}

		return Boolean.TRUE
				.equals(((JavascriptExecutor) driver).executeScript("return !arguments[0].checkValidity();", field));
	}

	public void fillModuleDetails(String title) {

		elementUtils.fillValue(moduleTitleField, title);
		// elementUtils.fillValue(moduleDescriptionField, description);
		// elementUtils.fillValue(moduleDurationField, duration);
	}

	public void clickEditForCourse(String courseName) {

		elementUtils.waitForAllElement(courseRows);

		for (int i = 0; i < courseRows.size(); i++) {

			try {
				if (courseRows.get(i).getText().contains(courseName)) {

					wait.until(ExpectedConditions.elementToBeClickable(editCourseButtons.get(i)));

					elementUtils.click(editCourseButtons.get(i));
					return;
				}
			} catch (StaleElementReferenceException e) {
				throw new RuntimeException("Course table refreshed. Please locate the course again: " + courseName, e);
			}
		}

		throw new RuntimeException("Course not found: " + courseName);
	}

	public void saveCourseChanges() {
		elementUtils.waitForClickable(saveButton);
		elementUtils.click(saveButton);
	}

	public boolean isCourseDisplayed(String courseName) {
		elementUtils.waitForAllElement(courseRows);

		for (WebElement row : courseRows) {
			if (row.getText().contains(courseName)) {
				return true;
			}
		}

		return false;
	}

	public void deleteCourse(String courseName) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		boolean deleted = false;

		for (int attempt = 0; attempt < 3 && !deleted; attempt++) {

			try {
				wait.until(ExpectedConditions.visibilityOfAllElements(courseRows));

				for (int i = 0; i < courseRows.size(); i++) {

					if (courseRows.get(i).getText().contains(courseName)) {

						wait.until(ExpectedConditions.elementToBeClickable(deleteCourseButtons.get(i)));

						elementUtils.click(deleteCourseButtons.get(i));

						wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));

						elementUtils.click(confirmDeleteButton);

						wait.until(ExpectedConditions.stalenessOf(courseRows.get(i)));

						deleted = true;
						break;
					}
				}

				if (!deleted) {
					throw new RuntimeException("Course not found: " + courseName);
				}

			} catch (StaleElementReferenceException e) {
				if (attempt == 2) {
					throw new RuntimeException("Course table kept refreshing during deletion: " + courseName, e);
				}
			}
		}

		System.out.println("Course deleted successfully: " + courseName);
	}

	public String getCourseStatus(String courseName) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOfAllElements(courseRows));
		wait.until(ExpectedConditions.visibilityOfAllElements(courseNameElements));
		wait.until(ExpectedConditions.visibilityOfAllElements(courseStatusElements));

		int rowCount = courseRows.size();
		int nameCount = courseNameElements.size();
		int statusCount = courseStatusElements.size();

		System.out.println("Rows: " + rowCount);
		System.out.println("Course names: " + nameCount);
		System.out.println("Status elements: " + statusCount);

		if (rowCount != nameCount || rowCount != statusCount) {
			throw new RuntimeException("Course table elements do not match. Check @FindBy locators.");
		}

		String searchName = courseName.trim().replaceAll("\\s+", " ");

		for (int i = 0; i < rowCount; i++) {

			String actualName = courseNameElements.get(i).getText().trim().replaceAll("\\s+", " ");

			String actualStatus = courseStatusElements.get(i).getText().trim();

			System.out.println("Table Row " + (i + 1) + " | Course Name: [" + actualName + "]" + " | Actual Status: ["
					+ actualStatus + "]");

			if (actualName.toLowerCase().contains(searchName.toLowerCase())) {

				WebElement row = courseRows.get(i);

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", row);

				System.out.println("Course Number: " + (i + 1));
				System.out.println("Course Name: " + actualName);
				System.out.println("Actual Course Status: " + actualStatus);

				if (actualStatus.isEmpty()) {
					throw new RuntimeException("Status is empty for course: " + actualName);
				}

				return actualStatus;
			}
		}

		throw new RuntimeException("Course not found: [" + searchName + "]. Check the course names printed above.");
	}

	public int getCourseCountByStatus(String expectedStatus) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(d -> !courseRows.isEmpty());

		int count = 0;

		for (int i = 0; i < courseRows.size(); i++) {

			String actualStatus = courseStatusElements.get(i).getText().trim().replace("_", " ").replaceAll("\\s+",
					" ");

			if (actualStatus.equalsIgnoreCase(expectedStatus.replace("_", " ").trim())) {
				count++;
			}
		}

		return count;
	}

	public boolean isCourseStudioPageDisplayed() {
		elementUtils.waitForElement(courseStudioHeading);
		return courseStudioHeading.isDisplayed();
	}

}
