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
//public class FS1408 extends BaseTestSuiteFs {
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
//	/**A1408.50.0120 --A1408.50.0200,A1408.50.0215 */
//	@Test(groups = {"groupA", "groupB"})
//	public void test_differentPaymentOptions() {
//		
//	}	
//	
//	/**A1408.50.0210, A1408.50.0220 --A1408.50.0240*/
//	@Test(groups = "important")
//	public void test_differentPaymentOptions2() {
//		
//	}
//	
//	
//	/** One CCA */
//	public void test_M1408() {
////		A1408.50.0168
////		User access from a mobile device and taps Amount row
////		A1408.50.0169
////		User access from a mobile device and taps Payment Date row
//	}
//	/** One CCA BR1094
//	 * A1408.50.0070 -- A1408.50.0110*/
//	public void test_A1408_50_0070(){
////		A1408.50.0070
////		USER clicks the Continue button 
////		BR1094
//	}
//	/** One CCA A1408.50.0250 - A1408.50.0280*/
//	public void test_A1408_50(){
//
//	}
//	
//
//	/** Two CCAs */
//	public void test_A1408_10_0015() {
////		A1408_10_0015
////		 System displays CCA information [FSCM:ACCT_FS1305] for selected CCA
////		 per BR1078.
////		 User can tap on the Pay To row to view other CCAs in the CCA select
////		 page.
////		A1408.50.0066
////		User with mulitiple CCAs access from a mobile device and taps Pay To row
////		A1408.50.0067
////		User with mulitiple DDAs access from a mobile device and taps Pay From row
////		A1408.50.0010
////		CM selects another CCA from CCA Account Selection drop-down 
//	}
//
//	/** One CCA on hold
//	 * A1408.50.0015 */
//	public void test_A1408_10_0035() {
////		A1408_10_0035
////		 1. System display message AP_0603 in the Make a Payment Step 1 of 3
////		 page
////		 2. System disables the Next button while the selected CCA account is
////		 one that is on hold. The Pay To label will display in red.
////		A1408.50.0015
////		System display message AP_0603 in the CCA select page
////		User cannot exit the select page until he selects a valid CCA or the Back button with the previously selected CCA to navigate back to Make a Payment Step 1 of 3 page
//
//	}
//
//	/** Two DDAs */
//	public void test_A1408_20_0015() {
//		// System displays Enrolled DDA information
//		// [FSCM:CUSTOMER_EAUTOPAYACCT_FS1410] for selected DDA per BR1079
//		// User can tap on the Pay From row to view other DDAs in the DDA select
//		// page.
//
//	}
//
//	/** One DDA on hold */
//	public void test_A1408_20_0025() {
////		A1408_20_0025
////		 1. System display message AP_0543 in Make a Payment Step 1 of 3 page
////		 2. System disables the Next button while the selected DDA account is
////		 one that is on hold. The Pay From label will display in red.
////		A1408.50.0025
////		System display message AP_0543 in the DDA select page.
////		User cannot exit the select page until he selects a valid DDA or the Back button with the previously selected DDA to navigate back to Make a Payment Step 1 of 3 page
//	}
//
//	/** One DDA's ABA number is changed or inactive */
//	public void test_A1408_20_0026() {
////		A1408_20_0026
////		 1. System display message AP_0122_M in Make a Payment Step 1 of 3
////		 page
////		 2. System disables the Next button while the selected DDA account is
////		 recently changed or inactive. The Pay From label will display in red.
////		A1408.50.0026
////		System display message AP_0122_M in the DDA select page.
////		User cannot exit the select page until he selects a valid DDA or the Back button with the previously selected DDA to navigate back to Make a Payment Step 1 of 3 page
//	}
//
//	/**External Status Code: A, B<L, U, Blank 
//	 * BR1095 */
//	public void test_A1408_45_0010() {
//		// BR 1095 If users are using mobile device, system displays Payment Date
//		// Instructions (AP_0612) and 10 Day Rule (AP_0613 if applicable) in the
//		// Payment Date detail page, AP payment posting disclaimer (AP_0506) in
//		// the Confirmation page. ACH Promo (AP_0510.a), ACH Enrollment request
//		// (AP_0510.c), and a link to view eAutopay T&C are not applicable for
//		// Mobile Web	
//		}
//
//	/** No enrolled DDA account */
//	public void test_A1408_20_0030() {
//		
//	}
//	
//	/** Has processed or pending Autopays */
//	public void test_A1408_25_0010() {
//		
//	}	
//	
//}
