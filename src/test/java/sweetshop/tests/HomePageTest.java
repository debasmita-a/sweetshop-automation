package sweetshop.tests;

import org.testng.Assert;
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
	public void getRetroSweetDataMapTest() {
		homePage.getRetroSweetDataMap();
	}
	
}
