//package fs.usecase;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.ResourceBundle;
//
//import myconstant.TestingAccount;
//
//import obsoletedatabase.DatabaseBase;
//
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//
//import static myconstant.AccountDefaultValues.*;
//import static myconstant.TestingAccount.*;
//
//import applications.BasePage;
//import static frameLib.FrameUtilities.*;
//import static frameLib.MyVerifyAssert.*;
//import static fs.FsConstants.*;
//import fs.BaseTestSuiteFs;
//import fs.pages.*;
//
//import utilityLib.Tools;
//
//@Test(singleThreaded = true)
//public class FS1401 extends BaseTestSuiteFs {
//	private ResourceBundle rb = ResourceBundle.getBundle("properties.ApplicationResources");
//	
//	@Parameters({ "browserType" })
//	@BeforeMethod(alwaysRun = true)
//	private void setUpBeforeMethod(String browserType) throws Exception {
//		TestingAccount.resetTestBed();
//		TestingAccount.deleteLock();		
//		TestingAccount.deleteAllEnrolledDdas();
//		CCA1.add2TestBed();
//		CCA1.restoreFakeFDRAccount();
//		CCA2.restoreFakeFDRAccount();		
//		openBrowser(browserType);
//	}
//
//	@AfterMethod(alwaysRun = true)
//	private void setUpAfterMethod(ITestResult result) throws Exception {
//		CCA2.deleteAccountOnHold();
//		CCA1.deleteAccountOnHold();
//		CCA1.restoreFakeFDRAccount();
//		CCA2.restoreFakeFDRAccount();
//		new BasePage(driver).closeBrowser();
//	}
//	
//	/**
//	 * No enrolled DDA
//	 */
//	public void test_M1401_15_WithNoEnrolledDDA(){
//		rb.getString("AP_0031");
//	}
//	/**
//	 * Enrolled DDA
//	 * also covers A1401_10_0020
//	 */
//	public void test_M1401_15_WithEnrolledDDA(){
//		CCA1.enrollDda();
//		rb.getString("AP_0032");
////		Customer can optionally:
////			a) Make a Payment. See FSCM: CUSTOMER_EAUTOPAY_FS1408:  ARRANGES EAUTOPAY PAYMENT
////			b) Manage Payment Account. See 
//		
//	}
//	/**
//	 * all CCAs on hold
//	 */
//	public void test_A1401_10_0010(){
//		CCA1.enrollDda();
//		CCA1.deleteAccountOnHold();
//		CCA1.addCcaHold();
//		CCA2.add2TestBed();
//		CCA2.deleteAccountOnHold();
//		CCA2.addCcaHold();
//		rb.getString("IC_089");		
////		1.  System displays error message IC_089.
////		2.  System displays eAutopay home page.
////		3.  Customer can NOT do the following:
////		a) Make a Payment. See FSCM: CUSTOMER_EAUTOPAY_FS1408:  ARRANGES EAUTOPAY PAYMENT
////		b) Manage Payment Account. See FSCM:CUSTOMER_EAUTOPAY_FS1403 : CUSTOMER MANAGES PAYMENT ACCOUNT
////
//	}	
//	
//	public void test_E1401_10_0010(){
////		COD, Autopay Bizworks or Autopay is down
////		LOG_4011
////		1.  System displays error message AP_0600 on the full site and AP_0600_M on the Mobile Web site
////		2.  System displays eAutopay home page.
////		3.  Customer can NOT do the following:
////		a) Make a Payment. See FSCM: CUSTOMER_EAUTOPAY_FS1408:  ARRANGES EAUTOPAY PAYMENT
////		b) Manage Payment Account. See FSCM:CUSTOMER_EAUTOPAY_FS1403 : CUSTOMER MANAGES PAYMENT ACCOUNT
//
//	}
//	
//}
