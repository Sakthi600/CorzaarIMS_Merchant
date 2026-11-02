package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class FunnelInsightsPage {

	private ElementUtils elementUtils;

	public FunnelInsightsPage(WebDriver driver) {

		this.elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='Funnel Insights']")
	private WebElement funnelInsightsMenu;

	@FindBy(xpath = "//h1[normalize-space()='Learner Retention & Funnel Insights']")
	private WebElement pageTitle;

	@FindBy(xpath = "//h2[normalize-space()='Top Performing Programs']")
	private WebElement topPerformingPrograms;

	@FindBy(xpath = "//span[normalize-space()='Rank #1']")
	private WebElement rankOne;

	@FindBy(xpath = "//span[normalize-space()='Rank #2']")
	private WebElement rankTwo;

	@FindBy(xpath = "//*[normalize-space()='Curriculum Completion & Drop-Off Matrix']")
	private WebElement curriculumCompletion;

	@FindBy(xpath = "(//div[@class='p-8 text-center bg-white dark:bg-dark-surface rounded-2xl border border-border text-ink-muted'])[1]")
	private WebElement noDropOffData;

	public void openFunnelInsights() {
		elementUtils.click(funnelInsightsMenu);
	}

	public boolean isTopPerformingProgramsDisplayed() {
		return elementUtils.isDisplayed(topPerformingPrograms);
	}

	public boolean isRankOneDisplayed() {
		return elementUtils.isDisplayed(rankOne);
	}

	public boolean isRankTwoDisplayed() {
		return elementUtils.isDisplayed(rankTwo);
	}

	public boolean isCurriculumCompletionDisplayed() {
		return elementUtils.isDisplayed(curriculumCompletion);
	}

	public boolean isNoDropOffDataDisplayed() {
		return elementUtils.isDisplayed(noDropOffData);
	}

	public boolean isLearnerDropOffPageDisplayed() {
		elementUtils.waitForElement(pageTitle);
		return pageTitle.isDisplayed();
	}
}