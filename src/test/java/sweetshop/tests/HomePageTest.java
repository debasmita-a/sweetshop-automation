package sweetshop.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sweetshop.basetest.BaseTest;
import sweetshop.constants.FrameworkConstants;

public class HomePageTest extends BaseTest{

	@Test
	public void getPageTitleTest() {
		Assert.assertEquals(homePage.getPageTitle(), FrameworkConstants.HOME_PAGE_TITLE);
	}
	
	@Test
	public void getPageHeaderTest() {
		Assert.assertEquals(homePage.getPageHeader(), FrameworkConstants.HOME_PAGE_HEADER);
	}
	
	@Test
	public void getPageHeaderP2Test() {
		Assert.assertEquals(homePage.getPageHeaderP2(), FrameworkConstants.HOME_PAGE_HEADER2);
	}
	
	@Test
	public void logoAvailableTest() {
		Assert.assertTrue(homePage.logoAvailable());
	}
	
	@Test
	public void getRetroSweetsHeaderTest() {
		Assert.assertEquals(homePage.getRetroSweetsHeader(), FrameworkConstants.RETRO_SWEETS_HEADER);
	}
	
	@Test
	public void isH2HeaderVisibleTest() {
		Assert.assertTrue(homePage.isH2HeaderVisible());
	}
	
	@Test
	public void isSalesImageAvailableTest() {
		Assert.assertTrue(homePage.isSalesImageAvailable());
	}
	
	@Test
	public void getBadgeCountTest() {
		Assert.assertTrue(homePage.getBadgeCount());
	}
	
	@DataProvider
	public Object[][] retrosweetdata(){
		return new Object[][] {
			{"Bon Bons", "Pink Strawberry Bonbons - sugar dusted, strawberry flavoured chewy sweets.", "£1.00"},
			{"Chocolate Cups", "Candy Chocolate Cups.", "£1.00"},
			{"Sherbert Discs", "UFO's Sherbert Filled Flying Saucers.", "£0.95"}
		};
	}
	
	@Test(dataProvider="retrosweetdata")
	public void getRetroSweetDataMapTest(String name, String desc, String price) {
		homePage.getDataMap();
	}
	
	
	
}
