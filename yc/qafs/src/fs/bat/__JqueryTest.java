package fs.bat;

import static applications.BasePage.closeBrowser;
import static applications.BasePage.navigateTo;
import static applications.BasePage.openBrowser;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import fs.BaseTestSuiteFs;
import fs.pages.AccountOverviewPage;
import fs.pages.LoginPage;
import static applications.BasePage.*;
import fs.pages.PersonalImagePage;

public class __JqueryTest extends BaseTestSuiteFs{

	@BeforeMethod(alwaysRun = true)
	private void setUpBeforeMethod() throws Exception {
		//CCA_BAT.deleteLock();
		openBrowser("ie");
	}

	@AfterMethod(alwaysRun = true)
	public void setUpAfterMethod(ITestResult result) {
		closeBrowser();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void tc_cca_login() {
		navigateTo("https://qa2support.1fbusa.com/fscm");
		LoginPage.load().login("ccabat", "password1");
		PersonalImagePage.load().selectBasketImage();
		AccountOverviewPage.load();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("$(document).ready();");
		jse.executeScript("$('[name=os]').click();");
		jse.executeScript("$([name=os]).click();");
		String script = "$(a[title='View Statement']:first_child).click();";
		jse.executeScript("$(a[title='View Statement']);");
		jse.executeScript("return $([title='View Statement']);");
		jse.executeScript("$([title='View Statement']).click();");
		jse.executeScript("return $('td.tright');");
		jse.executeScript("return $('td.tright :first');");
		jse.executeScript("return $('tr.even[nodeIndex=4]');");
		jse.executeScript("$('td.tright :last').click();");
		jse.executeScript("$('td.tright :last').hide();");
		jse.executeScript("return $(\"" + "[title='View Statement'" + "]\");");
		
		
		String jQuerySelector = "#abc_button " + "button:contains('New')";
				 String findButton = "return $(\"" + "[title='View Statement')" + "\").html();";

	}
}
