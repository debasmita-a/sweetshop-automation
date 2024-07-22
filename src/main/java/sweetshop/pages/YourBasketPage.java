package sweetshop.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	private By basketLink = By.xpath("//a[@href='/basket']");
	
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
	
	private List<String> getBasketItemNames() {
		List<WebElement> items = util.getElements(itemsInBasket);
		List<String> itemNames = new ArrayList<>();
		
		for(WebElement e : items) {
			itemNames.add(e.getText());
		}
		return itemNames;
	}
	
	private List<Double> getBasketItemPrices() {
		List<WebElement> prices = util.getElements(itemsPrices);
		List<Double> itemPrices = new ArrayList<>();
		
		for(WebElement e : prices) {
			itemPrices.add(Double.parseDouble(e.getText().replace("£", "").strip()));
		}
		return itemPrices;
	}
	
	private List<Integer> getCountPerItem() {
		List<WebElement> count = util.getElements(countPerItem);
		List<Integer> itemCount = new ArrayList<>();
		
		for(WebElement e : count) {
			itemCount.add(Integer.parseInt(e.getText().replace("x", "").strip()));
		}
		return itemCount;
	}
	
	public List<Map<String, Object>> getOrderDetails() {
		
		List<String> itemNames = getBasketItemNames();
		List<Double> itemPrices = getBasketItemPrices();
		List<Integer> itemCount = getCountPerItem();
		
		Map<String, Object> basketMap;
		List<Map<String, Object>> listOfMaps = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i<itemNames.size(); i++) {
			basketMap = new HashMap<>();
			basketMap.put("Item name", itemNames.get(i));
			basketMap.put("Item price", itemPrices.get(i));
			basketMap.put("Item count", itemCount.get(i));
			listOfMaps.add(basketMap);
		}
		return listOfMaps;
	}
	
	public int getBasketCount() {
		return Integer.parseInt(util.getElementText(basketCount));
	}
	
	public String emptyBasketAlertText() {
		util.doClick(emptyBasketLink);
		return util.getAlertText();
	}
	
	public int emptyBasket() {
		util.doClick(emptyBasketLink);
		util.acceptAlert();
		return getBasketCount();
	}
	
	public boolean dismissEmptyBasketAlert() {
		util.doClick(emptyBasketLink);
		util.dismissAlert();
		if(util.getElements(itemsInBasket).isEmpty()) {
			return false;
		}		
		return true;
	}
	
	public void goBackToBrowseSweetsPage() {
		util.doClick(sweetsLink);
	}
	
	public void goBackToBasketPage() {
		util.doClick(basketLink);
	}	
		
}
