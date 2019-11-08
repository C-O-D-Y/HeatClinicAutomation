package com.atmecs.ToolsninjaAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.constants.ValidatingData;
import com.atmecs.ToolsninjaAutomation.helpers.WebUtility;

/**
 * Class validates the different functionality of heat clinic website
 */
public class HeatClinicPage {

	WebDriver driver;
	Locators loc = new Locators();
	ValidatingData data;
	WebUtility WebUtility;

	public HeatClinicPage(WebDriver driver) {
		this.driver = driver;
		WebUtility = new WebUtility(driver);
		data = new ValidatingData();
	}

	/**
	 * Validating every anchor Redirection is correct or not
	 */
	public void isUserRedirectionCorrect(String actualtitle) {
		if (actualtitle == null) {
		} else {
			String title = WebUtility.getTitle();
			Assert.assertEquals(title, actualtitle);
			System.out.println("Redirection is on the correct page");
		}
	}

	/**
	 * Validating every view mens page redirection message
	 */
	public void validateViewMensDisplayed() {
		String gender = WebUtility.getText(Locators.getLocators("loc.text.viewMens"));
		Assert.assertEquals(gender, data.getValidatingData("ViewMensMessage"));
		System.out.println("message displayed");

	}

	/**
	 * Validating every details of the cart present in the values
	 */
	public void validateCartDetails(String productName, String size, String personalizeName, String color, String price,
			String totalPrice) {
		if (productName == null) {
		} else {
			WebUtility.clickElement(Locators.getLocators("loc.btn.cart"));
			String actualproductName = WebUtility.getText(Locators.getLocators("loc.text.cartProductName"));
			Assert.assertEquals(actualproductName, productName, "Product name doesnt match");

			String actualsize = WebUtility.getText(Locators.getLocators("loc.text.size"));

			Assert.assertEquals(actualsize, size, "size doesnt match");
			String name = WebUtility.getText(Locators.getLocators("loc.text.personalizename"));
			Assert.assertEquals(personalizeName, name, "Personalize name doesnt match");
			String actualcolor = WebUtility.getText(Locators.getLocators("loc.text.color"));
			Assert.assertEquals(actualcolor, color, "color doesnt match");
			String actualprice = WebUtility.getText(Locators.getLocators("loc.text.price"));
			Assert.assertEquals(actualprice, "$" + price, "individual price doesnt match");
			String actualTotalPrice = WebUtility.getText(Locators.getLocators("loc.text.totalPrice"));
			Assert.assertEquals(actualTotalPrice, "$" + totalPrice, "Total price  doesnt match");
		}
	}

	/**
	 * Validating cart details when quantity is updated
	 */
	public void updatedCartAssertion() {
		WebUtility.clearField(Locators.getLocators("loc.inputBox.quantity"));
		WebUtility.clickElement(Locators.getLocators("loc.inputBox.quantity"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtility.clickAndSendText(Locators.getLocators("loc.inputBox.quantity"), data.getValidatingData("quantity"));
		WebUtility.clickElement(Locators.getLocators("loc.btn.increaseQuantity"));
		WebUtility.clickElement(Locators.getLocators("loc.btn.updateQuantity"));
		String actualTotalPrice = WebUtility.getText(Locators.getLocators("loc.text.totalPrice"));
		Assert.assertEquals(actualTotalPrice, data.getValidatingData("updatedActualPrice"),
				"Total price  doesn't match");

	}

}
