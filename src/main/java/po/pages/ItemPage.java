package po.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemPage extends Page {
	private static final String ITEM_PRICE_LOCATOR = "(//span[@class='helpers_hide_tablet'])[1]";

	public ItemPage(WebDriver driver) {
		super(driver);
	}

	public int readItemPrice() {
		waitForElementPresent(ITEM_PRICE_LOCATOR);
		String priceTextValue = driver.findElement(By.xpath(ITEM_PRICE_LOCATOR)).getText();
		return getMinPrice(priceTextValue);
	}

	public static int getMinPrice(String priceText) {
		String[] arrOfStr = priceText.split(" ");
		String resultStr = arrOfStr[0];
		resultStr = resultStr.replace(",", ".");
		return (int) Double.parseDouble(resultStr);
	}

}
