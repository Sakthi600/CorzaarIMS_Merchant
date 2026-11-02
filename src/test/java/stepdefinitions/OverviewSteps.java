package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BatchesAttendancePage;
import pages.CourseStudioPage;
import pages.FunnelInsightsPage;
import pages.OverviewPage;
import utils.DriverFactory;

public class OverviewSteps {

	private final CourseStudioPage coursePage = new CourseStudioPage();
	private final BatchesAttendancePage batchPage = new BatchesAttendancePage(DriverFactory.getDriver());
	private final OverviewPage overviewPage = new OverviewPage(DriverFactory.getDriver());
	private final FunnelInsightsPage funnelPage = new FunnelInsightsPage(DriverFactory.getDriver());

	@Given("user is logged into Merchant Studio Overview")
	public void userIsLoggedIntoMerchantStudioOverview() {
		Assert.assertTrue(overviewPage.isOverviewDisplayed(), "Merchant Overview page is not displayed after login");
	}

	@Given("My Institute status should be displayed")
	public void myInstituteStatusShouldBeDisplayed() {
		Assert.assertTrue(overviewPage.isMyInstituteDisplayed(), "My Institute section is not displayed");

		System.out.println("My Institute section is displayed");
	}

	@Given("Accreditation status should be displayed")
	public void accreditationStatusShouldBeDisplayed() {
		Assert.assertTrue(overviewPage.isAccreditationPending(), "Accreditation status is not displayed");
	}

	@Given("logged-in institute should match expected institute")
	public void loggedInInstituteShouldMatchExpectedInstitute() {
		Assert.assertTrue(overviewPage.isExpectedInstituteDisplayed(), "Expected institute is not displayed");
	}

	@Given("Create Course button should be displayed")
	public void createCourseButtonShouldBeDisplayed() {
		Assert.assertTrue(overviewPage.isCreateCourseDisplayed(), "Create Course button is not displayed");
	}

	@Given("Add Batch button should be displayed")
	public void addBatchButtonShouldBeDisplayed() {
		Assert.assertTrue(overviewPage.isAddBatchDisplayed(), "Add Batch button is not displayed");
	}

	@Given("Create Course button should navigate to Course Studio page")
	public void createCourseButtonShouldNavigateToCourseStudioPage() {
		overviewPage.clickCreateCourse();

		Assert.assertTrue(overviewPage.isCourseStudioPageDisplayed(), "Course Studio page was not opened");
	}

	@When("user returns to Overview page")
	public void userReturnsToOverviewPage() {
		overviewPage.clickOverview();

		Assert.assertTrue(overviewPage.isOverviewDisplayed(), "Unable to return to Overview page");
	}

	@When("Add Batch button should navigate to Batches Attendance page")
	public void addBatchButtonShouldNavigateToBatchesAttendancePage() {
		overviewPage.clickAddBatch();

		Assert.assertTrue(overviewPage.isBatchesAttendancePageDisplayed(), "Batches & Attendance page was not opened");
	}

	@When("user clicks Course Studio card")
	public void userClicksCourseStudioCard() {
		overviewPage.clickCourseStudioCard();
	}

	@Then("Course Studio page should be displayed")
	public void courseStudioPageShouldBeDisplayed() {
		Assert.assertTrue(coursePage.isCourseStudioPageDisplayed(), "Course Studio navigation failed");
	}

	@When("user clicks Batches Attendance card")
	public void userClicksBatchesAttendanceCard() {
		overviewPage.clickBatchesAttendanceCard();
	}

	@Then("Batches Attendance page should be displayed")
	public void batchesAttendancePageShouldBeDisplayed() {
		Assert.assertTrue(batchPage.isBatchesAttendancePageDisplayed(), "Batches & Attendance navigation failed");
	}

	@When("user clicks Learner Drop-off card")
	public void userClicksLearnerDropOffCard() {
		overviewPage.clickLearnerDropOffCard();
	}

	@Then("Learner Drop-off page should be displayed")
	public void learnerDropOffPageShouldBeDisplayed() {
		Assert.assertTrue(funnelPage.isLearnerDropOffPageDisplayed(), "Learner Drop-off navigation failed");
	}

	@Then("Overview dashboard should be displayed")
	public void overviewDashboardShouldBeDisplayed() {
		Assert.assertTrue(overviewPage.isOverviewDisplayed(), "Overview dashboard is not displayed");
	}

	@Then("Total Revenue value should be displayed")
	public void totalRevenueValueShouldBeDisplayed() {
		Assert.assertTrue(overviewPage.isTotalRevenueDisplayed(), "Total Revenue value is not displayed");

		System.out.println("Total Revenue: " + overviewPage.getTotalRevenueText());
	}

	@Then("Confirmed Paid Learners count should be displayed")
	public void confirmedPaidLearnersCountShouldBeDisplayed() {
		int count = overviewPage.getStudentEnrollmentsCount();

		Assert.assertTrue(count >= 0, "Invalid Confirmed Paid Learners count: " + count);

		System.out.println("Confirmed Paid Learners: " + count);

//		overviewPage.openOverview();
//
//		int tableStudentEnrollCount = overviewPage.getStudentEnrollmentsTableCount();
//
//		overviewPage.openOverview();
//		int overviewStudentErollCount = overviewPage.getStudentEnrollmentsCount();
//
//		System.out.println("===== BATCH COUNT COMPARISON =====");
//		System.out.println("StudentEnroll Table Count: " + tableStudentEnrollCount);
//		System.out.println("Overview StudentEnroll Count: " + overviewStudentErollCount);
//
//		Assert.assertEquals(overviewStudentErollCount, tableStudentEnrollCount,
//				"StudentEnroll count mismatch between StudentEnroll Table and Overview");
	}

	@Then("course and batch table counts should match overview")
	public void courseAndBatchTableCountsShouldMatchOverview() {
		coursePage.openCourseStudio();

		int tableReviewCount = coursePage.getCourseCountByStatus("Under Review");

		int tablePublishedCount = coursePage.getCourseCountByStatus("Published");

		overviewPage.openOverview();

		int overviewReviewCount = overviewPage.getCoursesInReviewCount();

		int overviewPublishedCount = overviewPage.getPublishedCoursesCount();

		System.out.println("Under Review Table Count: " + tableReviewCount);
		System.out.println("Under Review Overview Count: " + overviewReviewCount);

		System.out.println("Published Table Count: " + tablePublishedCount);
		System.out.println("Published Overview Count: " + overviewPublishedCount);

		Assert.assertEquals(tableReviewCount, overviewReviewCount, "Under Review count mismatch");

		Assert.assertEquals(tablePublishedCount, overviewPublishedCount, "Published count mismatch");

		batchPage.openBatchesAttendance();

		int tableBatchCount = batchPage.getBatchTableCount();

		overviewPage.openOverview();
		int overviewBatchCount = overviewPage.getAttendanceTrackingCount();

		System.out.println("===== BATCH COUNT COMPARISON =====");
		System.out.println("Batch Table Count: " + tableBatchCount);
		System.out.println("Overview Batch Count: " + overviewBatchCount);

		Assert.assertEquals(overviewBatchCount, tableBatchCount,
				"Batch count mismatch between Batch Table and Overview");
		System.out.println("Counts matched successfully.");
	}

}
