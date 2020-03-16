package po.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Page {
	private static final String URL = "https://www.onliner.by/";
	private static final String INPUT_REQUEST_XPATH = "//input[@class='fast-search__input' and @name='query']";
	private static final String FIRST_RESULT_LOCATOR = "//a[@class='product__title-link'][1]";
	private static final String IFRAME_NAME = "iframe";

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public HomePage fillSearchInput(String query) {
		boolean queryIsNotCorrect = true;
		WebElement inputRequestElement = driver.findElement(By.xpath(INPUT_REQUEST_XPATH));
		while (queryIsNotCorrect) {
			inputRequestElement.sendKeys(query);
			if (query.equals(inputRequestElement.getAttribute("value"))) {
				queryIsNotCorrect = false;
			} else {
				inputRequestElement.clear();
			}
		}
		return this;
	}

	public ItemPage openFirstSearchResult() {
		driver.switchTo().defaultContent();
		List<WebElement> iframeList = driver.findElements(By.tagName(IFRAME_NAME));
		driver.switchTo().frame(iframeList.get(3));
		waitForElementPresent(FIRST_RESULT_LOCATOR);
		driver.findElement(By.xpath(FIRST_RESULT_LOCATOR)).click();
		driver.switchTo().defaultContent();
		return new ItemPage(driver);
	}

	public HomePage open() {
		driver.get(URL);
		return this;
	}
}
