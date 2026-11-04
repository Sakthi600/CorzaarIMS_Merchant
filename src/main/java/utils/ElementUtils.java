package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	private WebDriver driver;
	private WebDriverWait wait;

	public ElementUtils(WebDriver driver) {

		this.driver = driver;

		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

// Enter value into text field
	public void fillValue(WebElement element, String value) {

		WebElement ele = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));

		ele.clear();
		ele.sendKeys(value);
	}

// Click element

	public void click(WebElement element) {

		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));

		ele.click();
	}

// Select dropdown by visible text
	public void selectByVisibleText(WebElement element, String text) {

		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));

		Select select = new Select(ele);

		select.selectByVisibleText(text);
	}

// Select dropdown by value
	public void selectByValue(WebElement element, String value) {

		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));

		Select select = new Select(ele);

		select.selectByValue(value);
	}

// Select dropdown by index
	public void selectByIndex(WebElement element, int index) {

		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));

		Select select = new Select(ele);

		select.selectByIndex(index);
	}

// Check element is displayed
	public boolean isDisplayed(WebElement element) {

		try {

			return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

// Get text
	public String getText(WebElement element) {

		return wait.until(ExpectedConditions.visibilityOf(element)).getText();
	}

// Clear field
	public void clear(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element)).clear();
	}

// Wait until element is visible
	public void waitForElement(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
	}

// Wait until element is clickable
	public void waitForClickable(WebElement element) {

		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}

// refreshpage
	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void waitForAllElement(List<WebElement> batchRows) {

		wait.until(ExpectedConditions.visibilityOfAllElements(batchRows));
	}

	public void waitForInvisibility(WebElement element) {

		wait.until(ExpectedConditions.invisibilityOf(element));
	}

// 

}
