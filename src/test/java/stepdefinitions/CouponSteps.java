package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CouponsPage;
import utils.DriverFactory;

public class CouponSteps {
	private CouponsPage couponPage;

	@When("user opens Coupons and Promos")
	public void userOpensCouponsAndPromos() {
		couponPage = new CouponsPage(DriverFactory.getDriver());

		couponPage.openCoupons();

	}

	@When("user clicks Create Coupon")
	public void userClicksCreateCoupon() {
		couponPage.clickCreateCoupon();
	}

	@When("user enters coupon details {string} {string}")
	public void userEntersCouponDetails(String CouponCode, String Discount) {
		couponPage.enterCouponCode(CouponCode);
		couponPage.enterDiscountPercentage(Discount);
	}

	@When("user submits the coupon for approval")
	public void userSubmitsTheCouponForApproval() {
		couponPage.submitForApproval();
	}

	@When("created coupon should be displayed in the table with {string} status")
	public void createdCouponShouldBeDisplayedInTheTableWithStatus(String couponCode) {
		boolean pending = couponPage.isCouponPending(couponCode);

		Assert.assertTrue(pending, "Created coupon is not displayed with Pending status: " + couponCode);
	}

	@Then("coupon should not be submitted for approval")
	public void couponShouldNotBeSubmittedForApproval() {
		Assert.assertTrue(couponPage.isCouponFormStillDisplayed(), "Invalid coupon form should remain displayed");
	}

	@Then("coupon should not be submitted for approval for {string}")
	public void couponShouldNotBeSubmittedForApprovalFor(String TestCase) {
		Assert.assertTrue(couponPage.isCouponValidationDisplayed(),
				"Validation message not displayed for: " + TestCase);
	}

}
