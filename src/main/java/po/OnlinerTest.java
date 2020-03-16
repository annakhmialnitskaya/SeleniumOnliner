package po;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import po.pages.HomePage;
import po.pages.ItemPage;

public class OnlinerTest {
	private WebDriver driver;
	private static final String SEARCH_QUERY = "macbook pro";
	private static final String MESAGE = "macbook pro cost is %d. This is more than 2000 BYN";
	private static final int EXPECTED_PRICE = 2000;

	@BeforeClass(description = "Start browser")
	private void initBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:/programs/selenium/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(description = "Onliner search test")
	public void onlinerSearchTest() {
		ItemPage macbookProPage = new HomePage(driver).open().fillSearchInput(SEARCH_QUERY).openFirstSearchResult();
		int actualPrice = macbookProPage.readItemPrice();
		Assert.assertTrue(actualPrice < EXPECTED_PRICE, String.format(MESAGE, actualPrice));
	}

	@Test
	public void getMinPriceTest() {
		int minPrice = ItemPage.getMinPrice("6246,00 – 8102,47");
		Assert.assertEquals(minPrice, 6246, "Incorrect price");
	}

	@AfterClass(description = "close browser")
	public void kill() {
		driver.close();
	}
}
