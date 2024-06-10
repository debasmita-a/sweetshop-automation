package sweetshop.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import sweetshop.driverfactory.DriverFactory;
import sweetshop.driverfactory.PropertiesFileReader;
import sweetshop.pages.HomePage;

public class BaseTest {
	
	public WebDriver driver;
	public Properties prop;
	public DriverFactory df;
	public PropertiesFileReader configReader;
	public HomePage homePage;

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
