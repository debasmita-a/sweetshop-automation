package sweetshop.pages;

import org.openqa.selenium.WebDriver;

import sweetshop.utils.ElementUtil;

public class AboutPage {

	WebDriver driver;
	ElementUtil util;

	public AboutPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
}
