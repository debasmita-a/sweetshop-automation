package sweetshop.tests;

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
		return new Object[][] {
			{"Chocolate Cups"},
			{"Strawberry Bon Bons"},
			{"Jellies"},
			{"Dolly Mixture"}
		};
	}
	
	@Test(dataProvider="addToBasketData")
	public void addItemToBasketTest(String itemName) {		
		sweetsPage.addItemToBasket(itemName);
		Assert.assertTrue(sweetsPage.getBasketCount());
	}

}
