package po.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;
	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isElementPresent(String xpathExpression) {
		return !driver.findElements(By.xpath(xpathExpression)).isEmpty();
	}

	protected void waitForElementPresent(String xpathExpression) {
		(new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS)))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathExpression)));
	}

	protected void waitForElementVisible(String xpathExpression) {
		new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathExpression)));
	}

	protected void waitForElementEnabled(String xpathExpression) {
		new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
	}
}
