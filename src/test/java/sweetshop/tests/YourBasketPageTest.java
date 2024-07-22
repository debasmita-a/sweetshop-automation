package sweetshop.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sweetshop.basetest.BaseTest;
import sweetshop.constants.FrameworkConstants;

public class YourBasketPageTest extends BaseTest {

	@BeforeClass
	public void setup_basketPage() {
		sweetsPage = homePage.browserSweets();
	}

	@Test
	public void getPageHeaderTest() {
		basketPage = sweetsPage.basketPage();
		Assert.assertEquals(basketPage.getPageHeader(), FrameworkConstants.YOURBASKET_HEADER);
	}

	@Test
	public void getCheckOrderTextTest() {
		basketPage = sweetsPage.basketPage();
		Assert.assertEquals(basketPage.getCheckOrderText(), FrameworkConstants.ORDER_DETAILS_CHECK_TEXT);
	}

	@Test
	public void isBillingAdressHeaderAvailableTest() {
		basketPage = sweetsPage.basketPage();
		Assert.assertTrue(basketPage.isBillingAdressHeaderAvailable());
	}

	@Test
	public void isYourBasketHeaderAvailableTest() {
		basketPage = sweetsPage.basketPage();
		Assert.assertTrue(basketPage.isYourBasketHeaderAvailable());
	}

	@DataProvider
	public Object[] addToBasketData() {
		List<String> dataList = new ArrayList<>();
		dataList.add("Chocolate Cups");
		dataList.add("Strawberry Bon Bons");
		dataList.add("Jellies");
		dataList.add("Dolly Mixture");
		Object[][] data = new Object[1][1];
		data[0][0] = dataList;
		return data;
	}

	@Test(dataProvider = "addToBasketData")
	public void emptyBasketAlertTextTest(List<String> items) {
		System.out.println(items);
		basketPage = sweetsPage.basketPage();
		basketPage.goBackToBrowseSweetsPage();
		for (String s : items) {
			sweetsPage.addItemToBasket(s);
		}
		basketPage.goBackToBasketPage();
		Assert.assertEquals(basketPage.emptyBasketAlertText(), FrameworkConstants.EMPTY_BASKET_ALERT_MSG);
	}

	@Test(dataProvider = "addToBasketData")
	public void emptyBasketTest(List<String> items) {
		basketPage = sweetsPage.basketPage();
		basketPage.goBackToBrowseSweetsPage();
		for (String s : items) {
			sweetsPage.addItemToBasket(s);
		}
		basketPage.goBackToBasketPage();
		Assert.assertEquals(basketPage.emptyBasket(), 0);
	}

	@Test(dataProvider = "addToBasketData")
	public void dismissEmptyBasketAlertTest(List<String> items) {
		basketPage = sweetsPage.basketPage();
		basketPage.goBackToBrowseSweetsPage(); // Step -1
		for (String s : items) {
			sweetsPage.addItemToBasket(s); // Step -2
		}
		basketPage.goBackToBasketPage(); // Step -3
		Assert.assertTrue(basketPage.dismissEmptyBasketAlert()); // Step -4
	}

	@Test(dataProvider = "addToBasketData")
	public void addToBasketTest(List<String> items) {
		for (String s : items) {
			sweetsPage.addItemToBasket(s);
		}
		basketPage = sweetsPage.basketPage();
		List<Map<String, Object>> listMaps =  basketPage.getOrderDetails();
		System.out.println("int test method " + listMaps);
		for(int i=0; i<listMaps.size(); i++) {
			Set<Entry<String, Object>> entries = listMaps.get(i).entrySet();
			Iterator<Entry<String, Object>> it = entries.iterator();
			while(it.hasNext()) {
				Entry<String, Object> entry = it.next();
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		}
	}

	@Test(dataProvider = "addToBasketData")
	public void getBasketCountTest(List<String> items) {
		basketPage = sweetsPage.basketPage();
		basketPage.goBackToBrowseSweetsPage();
		for (String s : items) {
			sweetsPage.addItemToBasket(s);
		}
		int countOnBrowsePage = sweetsPage.getBasketCount();
		basketPage.goBackToBasketPage();
		int countOnOrderPage = basketPage.getBasketCount();
		Assert.assertEquals(countOnBrowsePage, countOnOrderPage);
	}

	@DataProvider
	public Object[][] mapTestData() {
		return new Object[][] { { "Chocolate Cups", 1.0, 1 }, { "Strawberry Bon Bons", 1.0, 1 } };
	}

	@Test(dataProvider = "mapTestData")
	public void getItemsMapData(String item) {
		sweetsPage.addItemToBasket(item);
		basketPage = sweetsPage.basketPage();
	}

}
