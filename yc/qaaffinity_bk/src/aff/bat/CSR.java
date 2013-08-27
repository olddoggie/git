package aff.bat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import myaccount.Account;
import myaccount.AccountMCycle;
import myaccount.TestBed;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.*;

import aff.BaseTestSuiteAffinity;
import aff.pages.*;

import static aff.properties.Affinity.CSR_URL;
import static aff.properties.Affinity.SECURITY_AGENT_FDR_INDEX;
import static applications.BasePage.*;
import frameLib.listener.*;
import aff.BaseTestSuiteAffinity;
import static aff.pages.AffinityBasePage.*;
import static applications.BasePage.openBrowser;
import static frameLib.FrameUtilities.creatDefaultPattern;
import static myconstant.AccountDefaultValues.*;
import static myconstant.TestAccounts.*;
import static myconstant.FsConfig.*;
import static myconstant.General.*;

@SuppressWarnings("static-access")
public class CSR extends BaseTestSuiteAffinity {
	//private AccountMCycle CCA_BAT = new AccountMCycle(CCA_BAT_ACCOUNT_NUMBER);
	private String batAccountUserName = "ccabat";
	private String batAccountNewPassword = "password1";

	 @Parameters({"browserType" })
	 @BeforeClass
	 protected void setupBeforeClass(String browserType) {
		 openBrowser(browserType);
	 }
	//
	// @AfterClass
	// public void teardownAfterClass() {
	// new BasePage.load().closeBrowser();;
	// }

//	@Parameters({ "browserType" })
//	@BeforeMethod(alwaysRun = true)
//	private void setUpBeforeMethod(String browserType) throws Exception {
//		openBrowser(browserType);
//	}

	@AfterMethod(alwaysRun = true)
	public void setUpAfterMethod(ITestResult result) {
		//closeBrowser();
	}

	/** login */
	@Test(priority = 15, description = "Login Affinity")
	public void loginTest() {
		navigateTo(CSR_URL);
		LandingPage.load().submitForm();
		AffLoginPage.load().loginAff(ODS_USER_NAME, ODS_PASSWORD,
				SECURITY_AGENT_FDR_INDEX);
	}

	@Test(priority = 20)
	public void searchTest(){
		SearchPage.load().researchRadioButton.click();
		SearchPage.uncheckAutoPullOn();
		SearchPage.searchByAcctNumber(CCA_BAT_ACCOUNT_NUMBER);

		// verify there is no error message; errorMessage is empty string; not null	
		AffNavigationPage.load().errorMessage.verifyEquals("");		
	}
	
	
	@Test(priority = 50)
	public void autopayTest(){
		//NavigationPage.load().paymentLink.waitUntilElementPresentInFrame(CONTENT_FRAME).click();
		AffNavigationPage.load().paymentLink.click();		
		//NavigationPage.paymentLink.waitUntilElementPresent().click();
		//NavigationPage.autopaySubTab.waitUntilElementPresentInFrame(CONTENT_FRAME).click();	
		AffNavigationPage.autopaySubTab.click();
		AutopayPopupPage.load().okButton.waitUntilElementPresent().click();		
		AutopayPopupPage.close();
		AffNavigationPage.load().autopaySubTab.click();	
		AutopayPopupPage.load().okButton.waitUntilElementPresent().click();	
		AutopayPopupPage.close();
		closeAffinityBrowser();
	}
	
}
