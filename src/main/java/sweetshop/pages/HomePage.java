package sweetshop.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sweetshop.utils.ElementUtil;

public class HomePage {

	WebDriver driver;
	ElementUtil util;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	private By logoLink = By.className("navbar-brand");
	private By pageHeader = By.xpath("//h1");
	private By pHeader = By.xpath("(//p[@class='lead'])[1]");
	private By h2Header = By.xpath("//h2");
	private By browserSweetsLink = By.linkText("Browse Sweets");
	private By retroSweetsHeader = By.xpath("(//p[@class='lead'])[2]");
	private By cardheaders = By.xpath("//div[@class='row text-center']//div[@class='card-body']/h4");
	private By carttexts = By.xpath("//div[@class='row text-center']//div[@class='card-body']/p[1]");
	private By prices = By.xpath("//div[@class='row text-center']//div[@class='card-body']/p[2]");
	private By addToBasketLink = By.xpath("//div[@class='row text-center']//div[@class='card-footer']/a");
	private By footerText = By.xpath("(//div[@class='container']/p)[2]");
	private By saleImg = By.xpath("//img[@alt='Sale now on']");

	private By sweetsLink = By.linkText("Sweets");
	private By aboutLink = By.linkText("About");
	private By loginLink = By.linkText("Login");
	private By basketLink = By.linkText("Basket");
	private By badgeCount = By.xpath("//span[contains(@class,'badge')]");

	public String getPageTitle() {
		return util.getCurrentPageTitle();
	}

	public String getPageHeader() {
		return util.getElementText(pageHeader);
	}

	public String getPageHeaderP2() {
		return util.getElementText(pHeader);
	}

	public boolean logoAvailable() {
		return util.isElementDisplayed(logoLink);
	}

	public String getRetroSweetsHeader() {
		return util.getElementText(retroSweetsHeader);
	}

	public boolean isH2HeaderVisible() {
		return util.isElementEnabled(h2Header);
	}

	private List<String> getAllCardHeaders() {
		List<WebElement> sweetNameList = util.getElements(cardheaders);
		List<String> cardHeaders = new ArrayList<String>();
		for (WebElement e : sweetNameList) {
			cardHeaders.add(e.getText());
		}
		return cardHeaders;
	}

	private List<String> getAllCardDescriptions() {
		List<WebElement> sweetDescList = util.getElements(carttexts);
		List<String> cardText = new ArrayList<String>();
		for (WebElement e : sweetDescList) {
			cardText.add(e.getText());
		}
		return cardText;
	}

	private List<String> getRetroSweetPrices() {
		List<WebElement> sweetPriceList = util.getElements(prices);
		List<String> cardPrice = new ArrayList<String>();
		for (WebElement e : sweetPriceList) {
			cardPrice.add(e.getText());
		}
		return cardPrice;
	}

	public List<HashMap<String, HashMap<String, String>>> getRetroSweetDataMap() {
		List<String> cardHeader = getAllCardHeaders();
		List<String> cardText = getAllCardDescriptions();
		List<String> cardPrice = getRetroSweetPrices();

		List<HashMap<String, HashMap<String, String>>> listOfMaps = new ArrayList<HashMap<String, HashMap<String, String>>>();

		for (int i = 0; i < 4; i++) {

			HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>();
			HashMap<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("desc", cardText.get(i));
			dataMap.put("price", cardPrice.get(i));
			data.put(cardHeader.get(i), dataMap);

			listOfMaps.add(data);
		}
		System.out.println(listOfMaps);

		return listOfMaps;
	}

	public void getDataMap() {
		List<HashMap<String, HashMap<String, String>>> listOfMaps = getRetroSweetDataMap();
		Map<String, String> maps = new HashMap<>();

		for (int i = 0; i < listOfMaps.size(); i++) {

		}
	}
}
