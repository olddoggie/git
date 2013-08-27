//package fs.bat;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Set;
//
//import main.Account;
//import main.AccountMCycle;
//import main.TestBed;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.ElementNotVisibleException;
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//
//import aff.pages.AutopayPopupPage;
//import aff.pages.LandingPage;
//import aff.pages.AffLoginPage;
//import aff.pages.AffNavigationPage;
//import aff.pages.SearchPage;
//
//import static aff.pages.AffinityBasePage.closeAffinityBrowser;
//import static aff.properties.Affinity.CSR_URL;
//import static aff.properties.Affinity.SECURITY_AGENT_FDR_INDEX;
//import static applications.BasePage.*;
//import frameLib.FrameUtilities;
//import frameLib.listener.*;
//import fs.BaseTestSuiteFs;
//import fs.pages.*;
//import static applications.BasePage.openBrowser;
//import static frameLib.FrameUtilities.creatDefaultPattern;
//import static fs.FsConstants.*;
//import static myconstant.AccountDefaultValues.*;
//import static myconstant.TestAccounts.*;
//import static myconstant.FsConfig.*;
//import static myconstant.General.*;
//import static aff.pages.AffinityBasePage.*; 
//
//@SuppressWarnings("static-access")
//public class __DeleteAutopay extends BaseTestSuiteFs {
//	private AccountMCycle CCA_BAT = new AccountMCycle(CCA_BAT_ACCOUNT_NUMBER);
//	private String batAccountUserName = "ccabat";
//	private String batAccountNewPassword = "password1";
//
//	@Parameters({ "browserType" })
//	@BeforeMethod(alwaysRun = true)
//	private void setUpBeforeMethod(String browserType) throws Exception {
//		openBrowser(browserType);
//	}
//
//	@AfterMethod(alwaysRun = true)
//	public void setUpAfterMethod(ITestResult result) {
//		//closeBrowser();
//		driver.quit();
//	}
//
//	@Test
//	public void tc_deleteAutopay() {
//		navigateTo(CSR_URL);
//		LandingPage.load().submitForm();
//		AffLoginPage.load().loginAff(ODS_USER_NAME, ODS_PASSWORD,
//				SECURITY_AGENT_FDR_INDEX);
//		
//		SearchPage.load().researchRadioButton.click();
//		SearchPage.uncheckAutoPullOn();
//		SearchPage.searchByAcctNumber(CCA_BAT_ACCOUNT_NUMBER);
//		
//		AffNavigationPage.load().paymentLink.click();
//		AffNavigationPage.autopaySubTab.waitUntilElementEnabledInFrame(CONTENT_FRAME).click();
//		AutopayPopupPage.load();
//		
//		for (int i = 0; i < 10; i++) {
//			try {
//				//AutopayPopupPage.okButton.click();
//				AutopayPopupPage.okButton.isDisplayed();
//				AutopayPopupPage.okButton.click();
//				break;
//			} catch (Exception e) {
//				FrameUtilities.mySleep(0.5);
//				try {
//					AutopayPopupPage.amount.getText();
//					break;
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}				
//			}
//		}
//		
////		AutopayPopupPage.amount.getText();
////		AutopayPopupPage.okButton.waitUntilElementPresent().click();
////		AutopayPopupPage.changeReasonDropdownList.selectByIndex(1);
////		AutopayPopupPage.changeReasonDropdownList.selectByVisibleText("Lack of Funds");
////		
////		System.out.println(driver.getPageSource());
////		driver.findElement(By.cssSelector("SPAN[label='changeReasonId']"));
////		driver.findElement(By.id("_jsx_0_f")).sendKeys("New Arrangement")
////		driver.findElement(By.id("_jsx_0_f")).getAttribute("class");
////		driver.findElements(By.className("jsx30select_optionlist"));
//		AutopayPopupPage.cancelAutopay();
//		AutopayPopupPage.close();
//		closeAffinityBrowser();
//	}
//}
