package sweetshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import sweetshop.utils.ElementUtil;

public class BrowseSweetsPage {

	WebDriver driver;
	ElementUtil util;

	public BrowseSweetsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	private By pageHeader = By.xpath("//h1");
	private By badgeCount = By.xpath("//span[contains(@class,'badge')]");
	private By basketLink = By.xpath("//a[@href='/basket']");
	
	public int basketCount = 0;
	
	public String getPageHeaderText() {
		return util.getElementText(pageHeader);
	}
	
	public void addItemToBasket(String itemName) {
		String addToBasketXpath = "//a[contains(@data-name,'" + itemName + "')]";
		driver.findElement(By.xpath(addToBasketXpath)).click();
	}
	
	public int getBasketCount() {
		return Integer.parseInt(util.getElementText(badgeCount));	
	}
	
	public YourBasketPage basketPage() {
		util.doClick(basketLink);
		return new YourBasketPage(driver);
	}
	
}
