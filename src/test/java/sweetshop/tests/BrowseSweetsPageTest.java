package sweetshop.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sweetshop.basetest.BaseTest;
import sweetshop.constants.FrameworkConstants;

public class BrowseSweetsPageTest extends BaseTest{

	@BeforeClass
	public void setupBrowseSweetsPage() {
		sweetsPage = homePage.browserSweets();
	}
	
	@Test
	public void getPageHeaderTest() {
		Assert.assertEquals(sweetsPage.getPageHeaderText(), FrameworkConstants.BROWSESWEETS_HEADER);
	}
	
	@DataProvider
	public Object[][] addToBasketData(){
		List<String> dataList = new ArrayList<>();
		dataList.add("Chocolate Cups");
		dataList.add("Strawberry Bon Bons");
		dataList.add("Jellies");
		dataList.add("Dolly Mixture");
		Object[][] data = new Object[1][1];
		data[0][0] = dataList;
		return data;
	}
	
	@Test(dataProvider="addToBasketData")
	public void addItemToBasketTest(List<String> items) {	
		for(String item : items) {
			sweetsPage.addItemToBasket(item);
		}
		Assert.assertEquals(sweetsPage.getBasketCount(), items.size());
	}

}
