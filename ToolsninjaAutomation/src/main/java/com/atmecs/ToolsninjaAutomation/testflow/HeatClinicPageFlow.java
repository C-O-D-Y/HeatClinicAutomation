package com.atmecs.ToolsninjaAutomation.testflow;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.helpers.WebUtility;

public class HeatClinicPageFlow {
	WebDriver driver;
	Locators locaters = new Locators();
	WebUtility WebUtility;

	public HeatClinicPageFlow(WebDriver driver) {
		this.driver = driver;
		WebUtility = new WebUtility(driver);

	}

	public void clickHeader(String header) {
		if (header == null) {
		} else {
			WebUtility.clickElement(Locators.getLocators("loc.btn.headers").replace("[dummytext]", header));
		}
	}

	public void selectClothingGender(String shirt) {
		WebUtility.action(Locators.getLocators("loc.btn.headers").replace("[dummytext]", shirt));
		WebUtility.clickElement(Locators.getLocators("loc.btn.mensClothing"));
	}

	public void selectShirt(String name) throws InterruptedException {
		
		WebUtility.clickElement(Locators.getLocators("loc.link.shirt"));
		WebUtility.explicitWait(Locators.getLocators("loc.btn.color"));
		Thread.sleep(5000);

		WebUtility.clickElement(Locators.getLocators("loc.btn.color"));
		Thread.sleep(5000);

		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();

		WebUtility.clickElement(Locators.getLocators("loc.btn.size"));
		Thread.sleep(5000);

		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();

		WebUtility.clickAndSendText(Locators.getLocators("loc.inputText.personalize"), name);
		WebUtility.clickElement(Locators.getLocators("loc.btn.buyNow"));

	}

}
