package webapplicationtest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import genericlibrary.BrowserUtilities;
import genericlibrary.LogUtilities;
import genericlibrary.ScreenshotUtilities;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class FunctionalTest extends BrowserUtilities {

	LoginPage lpobj;
	HomePage hpobj;
	Logger log;

	public FunctionalTest() {
		super();
	}

	@BeforeTest
	public void launch() throws IOException {

		DOMConfigurator.configure("log4j.xml");

		BrowserUtilities.getBrowser();

		LogUtilities.info("Browser launched with url Successfully");
	}

	@BeforeMethod
	public void laodPages() {
		lpobj = PageFactory.initElements(BrowserUtilities.driver, LoginPage.class);
		hpobj = PageFactory.initElements(BrowserUtilities.driver, HomePage.class);
	}

	@Test(alwaysRun = true)
	public void vehicleCheckMenuTest_001() throws Exception {
		try {

			hpobj.signInButton.click();
			lpobj.loginUser();
			Assert.assertEquals("test", "test2");
			LogUtilities.info("Test step 1 passed Successfully");
		} catch (Exception e) {
			System.out.println("Test Failed");
		}
	}

	
	@AfterMethod
	public void captureFailedTest(ITestResult result) {
		if (result.isSuccess())
			ScreenshotUtilities.getScreenShotForPass();
		else
			ScreenshotUtilities.getScreenShotForFailed(BrowserUtilities.driver, result.getMethod());
	}

	@AfterTest
	public void crash() throws IOException {
		BrowserUtilities.closeBrowser();
	}

}
