package sweetshop.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sweetshop.utils.ElementUtil;

public class YourBasketPage {

	WebDriver driver;
	ElementUtil util;

	public YourBasketPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	private By yourBasketPageHeader = By.xpath("//h1");
	private By checkOrderDetailsText = By.className("lead");
	private By billingAddHeader = By.xpath("//h4");
	private By yourBasketDetailsHeader = By.className("text-muted");
	
	
	private By fName = By.xpath("//label[@for='firstName']/following-sibling::input");
	private By lName = By.xpath("//label[@for='lastName']/following-sibling::input");
	private By emailAddr = By.id("email");
	private By address = By.id("address");
	private By countryDropdown = By.id("country");
	private By cityDropdown = By.id("city");
	private By zip = By.id("zip");
	
	private By ccName = By.id("cc-name");
	private By ccNameTextBelowField = By.xpath("//input[@id='cc-name']/following-sibling::small");
	private By ccNum = By.id("cc-number");
	private By ccExp = By.id("cc-expiration");
	private By cvvNum = By.id("cc-cvv");
	
	private By checkoutBtn = By.xpath("//button[text()='Continue to checkout']");
	
	private By emptyBasketLink = By.linkText("Empty Basket");
	private By basketCount = By.id("basketCount");
	
	private By itemsInBasket = By.xpath("//ul[@id='basketItems']//h6");
	private By itemsPrices = By.xpath("//ul[@id='basketItems']//li/span[@class='text-muted']");
	private By countPerItem = By.xpath("//ul[@id='basketItems']//li/div/small");
	private By totalGBP = By.xpath("(//ul[@id='basketItems']//li)[3]/span");
	private By totalVal = By.xpath("(//ul[@id='basketItems']//li)[3]/strong");
	
	private By sweetsLink = By.linkText("Sweets");
	
	public String getPageHeader() {
		return util.getElementText(yourBasketPageHeader);
	}
	
	public String getCheckOrderText() {
		return util.getElementText(checkOrderDetailsText);
	}
	
	public boolean isBillingAdressHeaderAvailable() {
		return util.isElementDisplayed(billingAddHeader);
	}
	
	public boolean isYourBasketHeaderAvailable() {
		return util.isElementDisplayed(yourBasketDetailsHeader);
	}
	
	private void fillBillingAddress(String fn, String ln, String email, String add, String country, String city, String zCode) {
		util.doSendKeys(fName, fn);
		util.doSendKeys(lName, ln);
		util.doSendKeys(emailAddr, email);
		util.doSendKeys(address, add);
		util.elementSelectByValue(countryDropdown, country);
		util.elementSelectByValue(cityDropdown, city);
		util.doSendKeys(zip, zCode);
	}
	
	private void fillCCDetails(String name, String number, String cvv, String expDate) {
		util.doSendKeys(ccName, name);
		util.doSendKeys(ccNum, number);
		util.doSendKeys(cvvNum, cvv);
		util.doSendKeys(ccExp, expDate);
	}
	
	public void fillBillingDetails(String fn, String ln, String email, String add, String country, String city, String zCode,
			String name, String number, String cvv, String expDate) {
		
		fillBillingAddress(fn,ln,email,add,country,city,zCode);
		fillCCDetails(name,number,cvv,expDate);		
		util.doClick(checkoutBtn);
	}
	
	public List<String> getBasketItemNames() {
		List<WebElement> items = util.getElements(itemsInBasket);
		List<String> itemNames = new ArrayList<>();
		
		for(WebElement e : items) {
			itemNames.add(e.getText());
		}
		return itemNames;
	}
	
	public List<Double> getBasketItemPrices() {
		List<WebElement> prices = util.getElements(itemsPrices);
		List<Double> itemPrices = new ArrayList<>();
		
		for(WebElement e : prices) {
			itemPrices.add(Double.parseDouble(e.getText().replace("£", "").strip()));
		}
		return itemPrices;
	}
	
	public List<Integer> getCountPerItem() {
		List<WebElement> count = util.getElements(countPerItem);
		List<Integer> itemCount = new ArrayList<>();
		
		for(WebElement e : count) {
			itemCount.add(Integer.parseInt(e.getText().replace("x", "").strip()));
		}
		return itemCount;
	}
	
	public void getOrderDetails() {
		
		List<String> itemNames = getBasketItemNames();
		List<Double> itemPrices = getBasketItemPrices();
		List<Integer> itemCount = getCountPerItem();
		
		Map<String, Object> basketMap = new HashMap<>();
		
		for(int i=0; i<itemNames.size(); i++) {
			basketMap.put("Item name", itemNames.get(i));
			basketMap.put("Item price", itemPrices.get(i));
			basketMap.put("Item count", itemCount.get(i));
		}
		
		System.out.println(basketMap);		
		
	}
	
	public int getBasketCount() {
		return Integer.parseInt(util.getElementText(basketCount));
	}
	
	
		
}
