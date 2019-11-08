package com.atmecs.ToolsninjaAutomation.testScripts;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.ToolsninjaAutomation.constants.Locators;
import com.atmecs.ToolsninjaAutomation.constants.ValidatingData;
import com.atmecs.ToolsninjaAutomation.dataProvider.TestDataProvider;
import com.atmecs.ToolsninjaAutomation.logReports.LogReport;
import com.atmecs.ToolsninjaAutomation.pages.HeatClinicPage;
import com.atmecs.ToolsninjaAutomation.testBase.TestBase;
import com.atmecs.ToolsninjaAutomation.testflow.HeatClinicPageFlow;

public class HeatClinicAutomation extends TestBase {
	LogReport log = new LogReport();
	int i = 1;
	Locators locators = new Locators();
	HeatClinicPage home;
	ValidatingData data;
	HeatClinicPageFlow heatClinicFlow;

	@BeforeClass
	public void getUrl() {
		String url2 = baseClass.getProperty("URL2");
		driver.get(url2);

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

//redirection validation
	@Test(priority = 1, dataProvider = "headers", dataProviderClass = TestDataProvider.class)
	public void validatefooterForServices(String header, String title) {
		// log = Logger.getLogger(HomePagetestscripts.class);
		// WebUtility.explicitWait(Locators.getLocators("loc.btn.services"));
		home = new HeatClinicPage(driver);
		data = new ValidatingData();
		heatClinicFlow = new HeatClinicPageFlow(driver);
		LogReport.getlogger();
		// log = extent.startTest("HomepageRedirection");
		heatClinicFlow.clickHeader(header);
		log.info("Starting Redirection validation");
		home.isUserRedirectionCorrect(title);
		log.info("Validation done");
	}

	// selecting gender for the clothing
	@Test(priority = 2)
	public void selectGenderClothing() {
		heatClinicFlow.selectClothingGender(data.getValidatingData("clothing"));
		log.info("selecting clothing gender");
	}

	// choosing shirt
	@Test(priority = 4)
	public void chooseShirt() {
		log.info("select shirt and color with size");
		try {
			heatClinicFlow.selectShirt(data.getValidatingData("personalizeName"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("selected the corresponding size and color");
		log.info("clothing validation starting");
	}

	// validating mens clothing view mens message
	@Test(priority = 3)
	public void validateMensClothing() {
		log.info("validating viewmens page");
		home.validateViewMensDisplayed();
		log.info("Redirected to viewmens page");
	}

	// validating cart details
	@Test(priority = 5, dataProvider = "cartDetails", dataProviderClass = TestDataProvider.class)
	public void validateCart(String productName, String size, String personalizeName, String color, String price,
			String TotalPrice) {
		log.info("validating cart details");
		home.validateCartDetails(productName, size, personalizeName, color, price, TotalPrice);
		log.info(" cart details are correct");
	}

	// validating updated cart details
	@Test(priority = 6)
	public void validateUpdatedGrandTotal() {
		log.info("validating updatewd grand total");
		home.updatedCartAssertion();
		log.info("Updated amount is correct, Functionality is working properly");
	}
}
