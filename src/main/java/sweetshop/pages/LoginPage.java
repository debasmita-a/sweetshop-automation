package sweetshop.pages;

import org.openqa.selenium.WebDriver;

import sweetshop.utils.ElementUtil;

public class LoginPage {

	WebDriver driver;
	ElementUtil util;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
}
