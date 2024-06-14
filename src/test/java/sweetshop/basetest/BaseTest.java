package sweetshop.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import sweetshop.driverfactory.DriverFactory;
import sweetshop.driverfactory.PropertiesFileReader;
import sweetshop.pages.BrowseSweetsPage;
import sweetshop.pages.HomePage;
import sweetshop.pages.YourBasketPage;

public class BaseTest {
	
	public WebDriver driver;
	public Properties prop;
	public DriverFactory df;
	public PropertiesFileReader configReader;
	public HomePage homePage;
	public BrowseSweetsPage sweetsPage;
	public YourBasketPage basketPage;

	@BeforeTest
	public void init() {
		configReader = new PropertiesFileReader();
		prop = configReader.init_properties();
		df = new DriverFactory();
		driver = df.init_webdriver(prop);
		
		homePage = new HomePage(driver);
		
	}
	
	@AfterTest
	public void teardown() {
		//driver.quit();
	}
}
