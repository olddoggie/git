package aff.bat;

//import java.util.HashMap;
//import java.util.Map;
import java.util.*;

import org.testng.ITestResult;
import org.testng.annotations.*;

import database.DatabaseBase;

import frameLib.listener.*;
import aff.pages.*;

import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;
import static frameLib.MyWebDriverLib.*;

@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })
public class CSRTest extends SharedTest {
	@BeforeClass
	protected void setupBeforeClass() {
		loadWebDriver("ie");
	}

	// @AfterClass
	public void teardownAfterClass() {
		new AffinityBasePage(driver).closeAffinityBrowser();
		DatabaseBase.close();
	}

	// @BeforeMethod(alwaysRun = true)
	// public void setUpBeforeMethod() {
	// }

	// @AfterMethod(alwaysRun = true)
	public void setUpAfterMethod(ITestResult result) {
		// result.getMethod().isTest()
		if (!result.isSuccess()) {
			captureScreenshot(result);
			new AffinityBasePage(driver).closeAffinityBrowser();
			loadWebDriver("ie");

			new LandingPage(driver).open(CSR_URL);
			new LoginPage(driver).loginAff(ODS_USER_NAME, ODS_PASSWORD, SECURITY_AGENT_FDR_INDEX);
			SearchPage searchPage = new SearchPage(driver);
			searchPage.researchRadioButton.click();
			searchPage.uncheckAutoPullOn();
			searchPage.searchByAcctNumber(ACCOUNT_NUMBER);
		}
	}

	// @Test(priority = 15,description="Login Affinity")
	@Test(priority = 15)
	public void loginTest() {
		new LandingPage(driver).open(CSR_URL);
		new LoginPage(driver).loginAff(ODS_USER_NAME, ODS_PASSWORD, SECURITY_AGENT_FDR_INDEX);
	}

	@Test(priority = 50)
	public void csrVdpTest() {
		DataPanelPage dataPanelPage = new DataPanelPage(driver);
		Map<String, String> vdpMap = new HashMap<String, String>();
		vdpMap.put("Verify", "SSN");
		vdpMap.put("Autopay", "Print Date");
		vdpMap.put("Reissue", "Int/Ext Status");
		for (String selection : vdpMap.keySet()) {
			dataPanelPage.vdpSelect.selectByVisibleText(selection);
			mySleep(1);
			dataPanelPage.variableDataPanel.verifyContains(vdpMap.get(selection));
		}

		// for (int i = 0; i < 14; i++) {
		// dataPanelPage.vdpSelect.selectByIndex(i);
		// }

	}

	@Test(priority = 50)
	public void nonMonAccountSecurityTest() {
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		//new AccountSecurityHomePage(driver).addUpdateRemoveSW("High schooll mascot", "", "yu", "01", "01", "2011");
		new AccountSecurityHomePage(driver).addUpdateRemoveSW("High school mascot", "", "yu", "01", "01", "2011");
		new AccountSecurityConfirmPage(driver).okButton.click();
	}

	@Test(priority = 50)
	public void nonMonCardActivatationTest() {
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).cardActivationLink.click();
		NonMonCardActivationPage cardActivationPage = new NonMonCardActivationPage(driver);
		cardActivationPage.verifyPageContains("CA_");

		cardActivationPage.viewButton.click();
		NonMonCardActivationAdditionScriptPage cardActivationAdditionScriptPage = new NonMonCardActivationAdditionScriptPage(driver);
		cardActivationAdditionScriptPage.verifyPageContains("CA_0004");
		cardActivationAdditionScriptPage.backButton.click();

		new NonMonCardActivationPage(driver).terminateButton.click();
		NonMonCardActionPopUpPage cardActionPopUpPage = new NonMonCardActionPopUpPage(driver);
		cardActionPopUpPage.clickYesButton();
	}

	@Test(priority = 50)
	public void nonMonCliTest() {
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).cliLink.click();
		new CliPopUpPage(driver).clickYesButton();
		// Based on your prior payment history, we are unable to process your
		// request at this time. We suggest that you make at least 6 months of
		// timely payments and stay beneath your limit. After you have made
		// those 6 months of on-time payments, you can call to see if your
		// account is eligible for an increase. At that time, we will review
		// your payment history, whether your account has ever been over the
		// limit and if you are using your card. We will also obtain information
		// from an outside credit-reporting agency to help in determining if an
		// increase will be granted.
		new CliPage(driver).verifyPageContains("CLI");
		new CliPage(driver).okButton.click();
	}

	@Test(priority = 1000)
	public void resultCallTest() {
		new AffNavigationPage(driver).resultLink.click();
		new ResultCsrPage(driver).resolveCall();
		new SearchPage(driver);
		
	}

}
