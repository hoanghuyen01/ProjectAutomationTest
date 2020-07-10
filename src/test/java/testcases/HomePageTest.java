package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pages.HomePage;

public class HomePageTest extends BaseTest{
	HomePage homePage;
	@BeforeTest
	public void data() {
		homePage = new HomePage(driver);
	}
	@Test(dataProvider = "valid_data_search")
	@Description("Input with with valid words on list product to check result searching")
	public void checkSearchProductWithValidData(String data) {
		homePage.open();
		homePage.searchProduct(data);
		Assert.assertEquals(homePage.getFirstResultSearchProductName(),data);
	}
	@Test(dataProvider = "invalid_data_search")
	@Description("Input with doesnt exit words on list product to check result of searching")
	public void checkSearchWithInvalidData(String data) {
		homePage.open();
		homePage.searchProduct(data);
		Assert.assertEquals(homePage.haveNoProductOfSearchResults(),true);
	}
	@Test
	@Description("Use Titles TEES as data sample for testing select category on menu bar")
	public void checkCategoryMenu() {
		homePage.open();
		homePage.selectCategoryOnMenu();
		Assert.assertEquals(homePage.getTitleOfCategory(), "Tees");	
	}
	@Test(dataProvider = "product_name_homepage")
	@Description("Go to product details throught imagent on homepage")
	public void goToDetailProduct(String nameProduct) {
		homePage.open();
		homePage.clickOnImageProduct(nameProduct);
		Assert.assertEquals(homePage.getTitlePage(),nameProduct);
	}
	@Test
	@Description("Verify Link Sign on Top HomePage")
	public void checkLinkSignIn() {
		homePage.open().clickOnSignIn();
		Assert.assertEquals(homePage.getTitlePage(),"Customer Login");
	}
	@Test
	@Description("Verify Link Create Account on HomePage")
	public void checkLinkCreateAccount() {
		homePage.open().clickOnSignUp();
		Assert.assertEquals(homePage.getTitlePage(),"Create New Customer Account");
	}
	@DataProvider(name = "invalid_data_search")
	public static Object[][] invalidDataTest(){
		return new Object[][] {{"safsdfsdfsd"}};
	}
	@DataProvider(name = "valid_data_search")
	public static Object[][] validDataTest(){
		return new Object[][] {{"Radiant Tee"}};
	}
	@DataProvider(name = "product_name_homepage")
	public static Object[][] dataValidAccountTest(){
		return new Object[][] {{"Radiant Tee"},{"Breathe-Easy Tank"},{"Hero Hoodie"}};
	}
}
