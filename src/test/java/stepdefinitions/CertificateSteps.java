package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CertificatesApprovalsPage;
import utils.DriverFactory;

public class CertificateSteps {
	private CertificatesApprovalsPage certificatePage;

	@When("user opens Certificates and Approvals")
	public void userOpensCertificatesAndApprovals() {
		certificatePage = new CertificatesApprovalsPage(DriverFactory.getDriver());

		certificatePage.openCertificatesApprovals();
	}

	@Then("Certificates and Approvals page should be displayed")
	public void certificatesAndApprovalsPageShouldBeDisplayed() {
		Assert.assertTrue(certificatePage.isCertificatesApprovalsPageDisplayed(),
				"Certificates & Approvals page is not displayed");
	}

	@Then("Pending Approvals should be displayed")
	public void pendingApprovalsShouldBeDisplayed() {
		Assert.assertTrue(certificatePage.isPendingApprovalsDisplayed(), "Pending Approvals is not displayed");
	}

	@Then("Templates Studio should be displayed")
	public void templatesStudioShouldBeDisplayed() {
		Assert.assertTrue(certificatePage.isTemplatesStudioDisplayed(), "Templates Studio is not displayed");
	}

	@When("user clicks Preview for a certificate")
	public void userClicksPreviewForACertificate() {
		certificatePage.clickPreview();
	}

	@When("user clicks Reject for a certificate")
	public void userClicksRejectForACertificate() {
		certificatePage.clickReject();
	}

	@When("user clicks Sign and Approve for a certificate")
	public void userClicksSignAndApproveForACertificate() {
		certificatePage.clickSignAndApprove();
	}

	@When("user closes the Review tab")
	public void userClosesTheReviewTab() {
		certificatePage.closeReviewTab();
	}

	@When("user opens Templates Studio")
	public void userOpensTemplatesStudio() {
		certificatePage.clickTemplateStudio();
	}

	@When("user clicks New Template")
	public void userClicksNewTemplate() {
		certificatePage.clickNewTemplate();
	}

	@When("user enters certificate details {string} {string}")
	public void userEntersCertificateDetails(String TemplateName, String Signatory) {
		certificatePage.enterTemplateName(TemplateName);

		certificatePage.enterSignatory(Signatory);
	}

	@When("user saves the certificate template")
	public void userSavesTheCertificateTemplate() {
		certificatePage.clickSaveTemplate();
	}

	@Then("certificate template {string} should be created successfully")
	public void certificateTemplateShouldBeCreatedSuccessfully(String TemplateName) {
		Assert.assertTrue(certificatePage.isTemplateCreatedSuccessfully(),
				"Certificate template was not created successfully " + TemplateName);
	}

	@Then("certificate template should not be created for {string}")
	public void certificateTemplateShouldNotBeCreatedFor(String TestCase) {
		Assert.assertFalse(certificatePage.isTemplateCreatedSuccessfully(),
				"Certificate template was created with invalid data " + TestCase);
	}

}
