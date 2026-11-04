package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CourseStudioPage;
import utils.ConfigReader;

public class CourseSteps {

	private CourseStudioPage coursePage;

	@When("user opens Course Studio")
	public void userOpensCourseStudio() {
		coursePage = new CourseStudioPage();
		coursePage.openCourseStudio();
	}

	@When("user clicks Create New Course Program")
	public void userClicksCreateNewCourseProgram() {
		coursePage.clickCreateCourse();
	}

	@When("user fills mandatory course details {string} {string} {string} {string}{string}")
	public void userFillsMandatoryCourseDetails(String CourseTitle, String TuitionFee, String Duration, String Overview,
			String ModuleTitle) {
		coursePage.enterCourseTitle(CourseTitle);

		coursePage.enterTuitionFee(TuitionFee);

		coursePage.enterProgramDuration(Duration);

		coursePage.enterCourseOverview(Overview);

		coursePage.enterModuleTitle(ModuleTitle);
	}

	@When("user clicks Add Module")
	public void userClicksAddModule() {
		coursePage.clickAddModule();
	}

	@When("user fills mandatory module details {string}")
	public void userFillsMandatoryModuleDetails(String ModuleTitle1) {

		coursePage.fillModuleDetails(ModuleTitle1);
	}

	@Then("an additional module section should be displayed")
	public void anAdditionalModuleSectionShouldBeDisplayed() {
		Assert.assertTrue(coursePage.getModuleCount() >= 2, "Additional module section was not displayed");
	}

	@When("user submits the course")
	public void userSubmitsTheCourse() {
		coursePage.publishCourse();
	}

	@Then("the created course status should be displayed")
	public void theCreatedCourseStatusShouldBeDisplayed() {
		String createdCourseName = ConfigReader.getProperty("courseTitle");

		String actualStatus = coursePage.getCourseStatus(createdCourseName);

		System.out.println("Created Course: " + createdCourseName);
		System.out.println("Created Course Actual Status: " + actualStatus);

		Assert.assertEquals(actualStatus.toLowerCase(), "under_review", "Created course status is incorrect!");
	}

	@Then("the existing course status should be displayed")
	public void theExistingCourseStatusShouldBeDisplayed() {
		String existingCourseName = ConfigReader.getProperty("existingCourseName");

		String actualStatus = coursePage.getCourseStatus(existingCourseName);

		System.out.println("Existing Course: " + existingCourseName);
		System.out.println("Existing Course Actual Status: " + actualStatus);

		Assert.assertFalse(actualStatus.isBlank(), "Existing course status is empty!");
	}

	@When("user tests course validation for {string}")
	public void userTestsCourseValidationFor(String TestCase) {
		coursePage.testCourseValidation(TestCase);
	}

	@Then("validation should be displayed for {string}")
	public void validationShouldBeDisplayedFor(String TestCase) {
		Assert.assertTrue(coursePage.isValidationDisplayed(TestCase),
				"Expected validation was not displayed for: " + TestCase);
	}

	@When("user clicks the close button on the course program modal")
	public void userClicksTheCloseButtonOnTheCourseProgramModal() {
		coursePage.closeCourseProgramModal();
	}

	@Then("the course program modal should be closed")
	public void theCourseProgramModalShouldBeClosed() {
		Assert.assertTrue(coursePage.isCourseProgramModalClosed(), "Course Program modal is still displayed");
	}

	@When("user clicks Edit for the course {string}")
	public void userClicksEditForTheCourse(String CourseTitle) {
		coursePage.clickEditForCourse(CourseTitle);
	}

	@When("user updates course details {string} {string} {string} {string}")
	public void userUpdatesCourseDetails(String UpdatedTitle, String TuitionFee, String Duration, String Overview) {
		coursePage.enterCourseTitle(UpdatedTitle);

		coursePage.enterTuitionFee(TuitionFee);

		coursePage.enterProgramDuration(Duration);

		coursePage.enterCourseOverview(Overview);
	}

	@When("user saves the course changes")
	public void userSavesTheCourseChanges() {
		coursePage.saveCourseChanges();
	}

	@Then("the updated course {string} should be displayed in the course table")
	public void theUpdatedCourseShouldBeDisplayedInTheCourseTable(String UpdatedTitle) {
		Assert.assertTrue(coursePage.isCourseDisplayed(UpdatedTitle),
				"Updated course was not displayed in the course table");
	}

	@Then("the system should reject the invalid course details and prevent the update")
	public void theSystemShouldRejectTheInvalidCourseDetailsAndPreventTheUpdate() {
		boolean validationDisplayed = coursePage.isValidationMessageDisplayed();

		Assert.assertTrue(validationDisplayed, "Validation failed: Invalid course details were not rejected.");

		Assert.assertTrue(coursePage.isCourseUpdateFormStillOpen(),
				"Invalid course details were saved successfully. Update should have been prevented.");
	}

	@When("user deletes the configured course {string}")
	public void userDeletesTheConfiguredCourse(String CourseTitle) {
		coursePage.deleteCourse(CourseTitle);
	}

	@Then("the deleted course {string} should not be displayed in the course table")
	public void theDeletedCourseShouldNotBeDisplayedInTheCourseTable(String CourseTitle) {
		Assert.assertTrue(coursePage.isCourseDisplayed(CourseTitle),
				"Deleted course is still displayed in the course table");
	}

}
