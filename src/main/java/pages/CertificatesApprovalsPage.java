package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class CertificatesApprovalsPage {

	private WebDriver driver;

	private ElementUtils elementUtils;

	public CertificatesApprovalsPage(WebDriver driver) {

		this.driver = driver;

		this.elementUtils = new ElementUtils(driver);

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Certificates & Approvals']")
	private WebElement certificatesApprovalsMenu;

	@FindBy(xpath = "//*[normalize-space()='Certificates & Approvals']")
	private WebElement pageTitle;

	@FindBy(xpath = "//*[normalize-space()='Pending Approvals']")
	private WebElement pendingApprovals;

	@FindBy(xpath = "//*[normalize-space()='Templates Studio']")
	private WebElement templatesStudio;

	@FindBy(xpath = "//*[normalize-space()='Learner & Program']")
	private WebElement learnerProgram;

	@FindBy(xpath = "//th[normalize-space()='Certificate ID']")
	private WebElement certificateId;

	@FindBy(xpath = "//th[normalize-space()='Completed On']")
	private WebElement completedOn;

	@FindBy(xpath = "//th[normalize-space()='Review & Decision']")
	private WebElement reviewDecision;

	@FindBy(xpath = "//td[text()='No student credentials awaiting manual sign-off.']")
	private WebElement noPendingMessage;

	@FindBy(xpath = "//span[normalize-space()='Templates Studio']")
	private WebElement templateStudio;

	@FindBy(xpath = "//span[normalize-space()='New Template']")
	private WebElement newTemplateButton;

	@FindBy(xpath = "//input[@id='template-name']")
	private WebElement templateNameField;

	@FindBy(xpath = "//input[@id='branding-accent-color']")
	private WebElement brandingAccentColorField;

	@FindBy(xpath = "(//input[@id='signatory-title-&-name'])[1]")
	private WebElement signatoryField;

	@FindBy(xpath = "//span[normalize-space()='Save Template']")
	private WebElement saveTemplateButton;

	@FindBy(xpath = "(//span[normalize-space()='Cancel'])[1]")
	private WebElement cancelButton;

	@FindBy(xpath = "(//tbody/tr[1]//*[name()='svg'])[1]")
	private WebElement previewButton;

	@FindBy(xpath = "(//span[contains(text(),'Reject')])[1]")
	private WebElement rejectButton;

	@FindBy(xpath = "(//span[contains(text(),'Sign & Approve')])[1]")
	private WebElement signAndApproveButton;

	@FindBy(xpath = "(//h4[normalize-space()='Template Created'])[1]")
	private WebElement templateSuccessMessage;

	public void openCertificatesApprovals() {

		elementUtils.click(certificatesApprovalsMenu);
	}

	public boolean isCertificatesApprovalsPageDisplayed() {

		return elementUtils.isDisplayed(pageTitle);
	}

	public boolean isPendingApprovalsDisplayed() {

		return elementUtils.isDisplayed(pendingApprovals);
	}

	public boolean isTemplatesStudioDisplayed() {

		return elementUtils.isDisplayed(templatesStudio);
	}

	public boolean isLearnerProgramDisplayed() {

		return elementUtils.isDisplayed(learnerProgram);
	}

	public boolean isCertificateIdDisplayed() {

		return elementUtils.isDisplayed(certificateId);
	}

	public boolean isCompletedOnDisplayed() {

		return elementUtils.isDisplayed(completedOn);
	}

	public boolean isReviewDecisionDisplayed() {

		return elementUtils.isDisplayed(reviewDecision);
	}

	public boolean isNoPendingMessageDisplayed() {

		return elementUtils.isDisplayed(noPendingMessage);
	}

	public void clickTemplateStudio() {

		elementUtils.click(templateStudio);
	}

	public void clickNewTemplate() {

		elementUtils.click(newTemplateButton);
	}

	public void enterTemplateName(String templateName) {

		elementUtils.fillValue(templateNameField, templateName);
	}

	public void enterBrandingAccentColor(String color) {

		elementUtils.fillValue(brandingAccentColorField, color);
	}

	public void enterSignatory(String signatory) {

		elementUtils.fillValue(signatoryField, signatory);
	}

	public void clickSaveTemplate() {

		elementUtils.click(saveTemplateButton);
	}

	public void clickCancel() {
		elementUtils.click(cancelButton);
	}

	public boolean isTemplateCreatedSuccessfully() {
		try {
			elementUtils.waitForElement(templateSuccessMessage);

			return templateSuccessMessage.isDisplayed();

		} catch (TimeoutException e) {
			return false;
		}
	}

	public void clickPreview() {
		elementUtils.click(previewButton);
	}

	public void clickReject() {
		elementUtils.click(rejectButton);
	}

	public void clickSignAndApprove() {
		elementUtils.waitForClickable(signAndApproveButton);
		elementUtils.click(signAndApproveButton);
	}

	public void closeReviewTab() {
		String currentWindow = driver.getWindowHandle();

		for (String window : driver.getWindowHandles()) {
			if (!window.equals(currentWindow)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}

		driver.switchTo().window(currentWindow);
	}

}