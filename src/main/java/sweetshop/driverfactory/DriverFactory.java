package sweetshop.driverfactory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

	Properties prop;
	//WebDriver driver;
	static ThreadLocal<WebDriver> tl_driver = new ThreadLocal<WebDriver>();
	
	public WebDriver init_webdriver(Properties prop) {
		this.prop = prop;
		
		String browser = prop.getProperty("browser");
		
		switch (browser) {
		case "chrome":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				
			}else {
				tl_driver.set(new ChromeDriver());
			}
			break;
		case "edge":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				
			}else {
				tl_driver.set(new EdgeDriver());
			}			
			break;

		default:
			break;
		}
		get_tlDriver().get(prop.getProperty("url"));
		get_tlDriver().manage().window().maximize();
		get_tlDriver().manage().deleteAllCookies();
		
		return get_tlDriver();
		
	}
	
	public WebDriver get_tlDriver() {
		return tl_driver.get();
	}
	
}
