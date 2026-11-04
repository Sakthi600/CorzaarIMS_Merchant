package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import pages.BatchesAttendancePage;
import utils.DriverFactory;

public class BatchSteps {

	private BatchesAttendancePage batchPage;

	@When("user opens Batches and Attendance")
	public void userOpensBatchesAndAttendance() {
		batchPage = new BatchesAttendancePage(DriverFactory.getDriver());
		batchPage.openBatchesAttendance();
	}

	@When("user clicks Create Cohort Batch")
	public void userClicksCreateCohortBatch() {
		batchPage.clickCreateCohortBatch();
	}

	@When("user fills mandatory batch details {string} {string} {string} {string} {string}")
	public void userFillsMandatoryBatchDetails(String Coordinator, String WeeklySchedule, String StartDate,
			String EndDate, String SeatCapacity) {
		batchPage.enterCoordinator(Coordinator);
		batchPage.enterWeeklySchedule(WeeklySchedule);
		batchPage.enterStartDate(StartDate);
		batchPage.enterEndDate(EndDate);
		batchPage.enterSeatCapacity(SeatCapacity);
	}

	@When("user generates batch sessions")
	public void userGeneratesBatchSessions() {
		batchPage.generateSessions();
	}

	@Then("the created batch should be displayed in the batch table")
	public void createdBatchShouldBeDisplayedInTable() {

		String batchName = batchPage.getCreatedBatchName();

		System.out.println("Created Batch Name: " + batchName);

		boolean displayed = batchPage.isBatchDisplayed(batchName);

		Assert.assertTrue(displayed, "Created batch is not displayed: " + batchName);
	}

	@When("user clicks Roster Matrix for the created batch")
	public void userClicksRosterMatrixForCreatedBatch() {
		batchPage.clickRosterMatrixForCreatedBatch();
	}

	@Then("the Roster Matrix page should be displayed")
	public void theRosterMatrixPageShouldBeDisplayed() {
		Assert.assertTrue(batchPage.isRosterMatrixDisplayed(), "Roster Matrix page is not displayed");
		System.out.println("Header: " + batchPage.getRosterMatrixText());
	}

	@When("user closes the Roster Matrix")
	public void userClosesTheRosterMatrix() {
		batchPage.closeRosterMatrix();
	}

	@Then("the Roster Matrix should be closed")
	public void theRosterMatrixShouldBeClosed() {
		Assert.assertTrue(batchPage.isRosterMatrixDisplayed(), "Roster Matrix is still displayed");
	}

	@When("user tests batch validation for {string} using  {string} {string} {string} {string} {string}")
	public void userTestsBatchValidationForUsing(String TestCase, String Coordinator, String WeeklySchedule,
			String StartDate, String EndDate, String SeatCapacity) {
		batchPage.enterCoordinator(Coordinator);
		batchPage.enterWeeklySchedule(WeeklySchedule);
		batchPage.enterStartDate(StartDate);
		batchPage.enterEndDate(EndDate);
		batchPage.enterSeatCapacity(SeatCapacity);

		batchPage.generateSessions();
	}


	@Then("batch validation should be displayed for {string}")
	public void batchValidationShouldBeDisplayedFor(String TestCase) {
		Assert.assertTrue(batchPage.isBatchValidationDisplayed(TestCase),
				"Expected batch validation was not displayed for: " + TestCase);
	}

}
