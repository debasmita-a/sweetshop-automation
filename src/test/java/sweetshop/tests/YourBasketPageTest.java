package sweetshop.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sweetshop.basetest.BaseTest;

public class YourBasketPageTest extends BaseTest{

	@BeforeClass
	public void setup_basketPage() {
		sweetsPage = homePage.browserSweets();	
	}
	
	@DataProvider
	public Object[] addToBasketData(){
		return new Object[][] {
			{"Chocolate Cups"},
			{"Strawberry Bon Bons"},
			{"Jellies"},
			{"Dolly Mixture"}
		};
	}
	
	@Test(dataProvider="addToBasketData")
	public void addToBasket(String item) {
		List<Object> itemList = Arrays.asList(addToBasketData());
		List<String> list = itemList.stream().map(object -> Objects.toString(object, null)).toList();
		for(String s : list) {
			sweetsPage.addItemToBasket(s);
		}
		//sweetsPage.addItemToBasket(item);
		
		basketPage = sweetsPage.basketPage();
		basketPage.getOrderDetails();
	}
	
	@Test
	public void getOrderDetailsTest() {
		basketPage = sweetsPage.basketPage();
		basketPage.getOrderDetails();
	}
	
}
