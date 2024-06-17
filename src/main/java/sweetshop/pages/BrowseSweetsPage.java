package sweetshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
		basketCount++;
	}
	
	public boolean getBasketCount() {
		int count = Integer.parseInt(util.getElementText(badgeCount));
		if(basketCount==count) {
			return true;
		}
		return false;
	}
	
	public YourBasketPage basketPage() {
		util.doClick(basketLink);
		return new YourBasketPage(driver);
	}
	
}
