package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	private static WebDriver driver;

	public static void initializeDriver() {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--start-maximized");

		driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {

		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}