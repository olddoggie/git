package aff.bat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestResult;
import org.testng.annotations.*;

import aff.BaseTestSuiteAffinity;
import aff.pages.*;
import static applications.BasePage.*;
import static aff.pages.AffinityBasePage.*;
import static aff.AffinityConstants.*;
import static myconstant.TestAccounts.*;
import static utilityLib.Tools.*;

@SuppressWarnings("static-access")
public class CSR extends BaseTestSuiteAffinity {
	// private AccountMCycle CCA_BAT = new
	// AccountMCycle(CCA_BAT_ACCOUNT_NUMBER);
	private String batAccountUserName = "ccabat";
	private String batAccountNewPassword = "password1";

	// @Parameters({"browserType" })
	// @BeforeClass
	// protected void setupBeforeClass(String browserType) {
	// openBrowser(browserType);
	// }
	//
	// @AfterClass
	// public void teardownAfterClass() {
	// new BasePage.load().closeBrowser();;
	// }

	@Parameters({ "browserType" })
	@BeforeMethod(alwaysRun = true)
	private void setUpBeforeMethod(String browserType) throws Exception {
		resetAffinity();
		openBrowser(browserType);
		login(CSR_URL);
		SearchPageCSR.load().researchRadioButton.click();
		SearchPageCSR.uncheckAutoPullOn();
		SearchPageCSR.searchByAcctNumber(CCA_BAT_ACCOUNT_NUMBER);
		AffNavigationPage.load();
		AddNotePage.Load();
		DataPanelPage.load();	

	}

	@AfterMethod(alwaysRun = true)
	public void setUpAfterMethod(ITestResult result) {
		closeAffinityBrowser();
	}

	// @Test()
	public void searchTest() {
		AffNavigationPage.load().errorMessage.verifyEquals("");
	}

	@Test()
	public void DataPanelTest() {
		DataPanelPage.load().vdpSelect.selectByVisibleText("Payment (PMT)");
		DataPanelPage.vdpSelect.selectByVisibleText("Verify");
		DataPanelPage.verdAddressButton.click();
		myWebelement("xpath://tr[contains(.,'Verd Address Dt')]").verifyContains(getDateString("MM/yyyy","PST"));
		
		
		//<div tabIndex="-1" id="verify" style="display: none;">
		//<div tabIndex="-1" id="verify" ">		
//		DataPanelPage.PaymentPanel.verifyAttributeContains("style", "");
//		DataPanelPage.verifyPanel.verifyAttributeContains("style", "display: none");
//		DataPanelPage.PaymentPanel.getAttribute("style").equalsIgnoreCase("");
//		DataPanelPage.vdpSelect.selectByVisibleText("Verify");
//		DataPanelPage.verifyPanel.verifyAttributeContains("style", "");
//		DataPanelPage.verdAddressButton.click();
//		DataPanelPage.verdAddressButton.waitUntilAjaxCallsComplete();
//		myWebelement("xpath://tr[contains(.,'Verd Address Dt')]").verifyContains(getDateString("MM/yyyy","PST"));
	}



	// @Test()
	// public void autopayTest(){
	// //NavigationPage.load().paymentLink.waitUntilElementPresentInFrame(CONTENT_FRAME).click();
	// AffNavigationPage.load().paymentLink.click();
	// //NavigationPage.paymentLink.waitUntilElementPresent().click();
	// //NavigationPage.autopaySubTab.waitUntilElementPresentInFrame(CONTENT_FRAME).click();
	// AffNavigationPage.autopaySubTab.click();
	// AutopayPopupPage.load().okButton.waitUntilElementPresent().click();
	// AutopayPopupPage.close();
	// AffNavigationPage.load().autopaySubTab.click();
	// AutopayPopupPage.load().okButton.waitUntilElementPresent().click();
	// AutopayPopupPage.close();
	// closeAffinityBrowser();
	// }

}
