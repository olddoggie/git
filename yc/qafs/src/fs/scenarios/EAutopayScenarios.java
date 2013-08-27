package fs.scenarios;

import static applications.BasePage.*;
import static frameLib.FrameUtilities.creatDefaultPattern;
import static frameLib.MyVerifyAssert.verifyEquals;
import static fs.FsConstants.*;
import static myconstant.AccountDefaultValues.*;
import static myconstant.SpringDb.*;
import static myconstant.TestAccounts.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import myaccount.TestBed;

import org.testng.ITestResult;
import org.testng.annotations.*;

import utilityLib.Tools;
import fs.BaseTestSuiteFs;
import fs.pages.*;
@SuppressWarnings("static-access")
@Test(singleThreaded = true)
public class EAutopayScenarios extends BaseTestSuiteFs {
	private String ABA_NUMBER_2 = "011000138";
	private String CHECKING_ACCOUNT_NUMBER_2 = "10002";
	private String SYSTEM_DATE = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

	
	@Parameters({ "browserType" })
	@BeforeMethod(alwaysRun = true)
	private void setUpBeforeMethod(String browserType) throws Exception {
		TestBed.resetTestBed();	
		TestBed.add2TestBed(CCA_FAKEFDR1);
		CCA_FAKEFDR1.deleteAccountOnHold();
		CCA_FAKEFDR1.deleteLock();
		CCA_FAKEFDR1.restoreFakeFDRAccount();
		CCA_FAKEFDR1.deleteAutopay();
		//CCA_FAKEFDR1.deleteAllEnrolledDdas();
		CCA_FAKEFDR1.deleteEnrolledDdasByAccountNumber(CHECKING_ACCOUNT_NUMBER_DEFAULT);
		CCA_FAKEFDR1.deleteEnrolledDdasByAccountNumber(CHECKING_ACCOUNT_NUMBER_2);
		CCA_FAKEFDR1.deleteAffinityNotes();
		openBrowser(browserType);
	}

	@Parameters({ "browserType" })
	@AfterMethod(alwaysRun = true)
	private void setUpAfterMethod(ITestResult result, String browserType) throws Exception {
		//captureScreenshot(result);
		closeBrowser();
	}

	/**
	 * CM has no enrolled checking account
	 */
	@Test(dataProvider = "eautpay_0001_data_table")
	public void test_eautpay_0001(String testCaseId, String testDescription, String internalStatus, String externalStatus, String creditLimit,
			String currentBalance, String lastStatementBalance, String minmumPaymentDue, String paymentType, String paymentAmount, String paymentDate)
			throws SQLException, ParseException {
		updateFakeFdrCapTable(internalStatus, externalStatus, creditLimit, currentBalance, lastStatementBalance, minmumPaymentDue);
		go2AccountOverviewPage(FS_TESTBED_USERNAME);
	
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();;
		EAutopayHomeNotEnrolledPage.load().enrollNowForEAutopayImg.click();

		EAutopayEnrollAccountPage.load().enrollAccount();
		EAutopayConfirmEnrollmentInformationPage.load().continueButton.click();
		EAutopayEnrollTermsPage.load().enrollTerms();
		EAutopayEnrollSuccessPage.load().makeAPaymentButton.click();

		paymentDate = parsePaymentDate(paymentDate);
		EAutopayMakeAPaymentPage.load().makePayment(paymentDate, paymentType, paymentAmount);
		EAutopayConfirmPaymentPage.load().authorizePaymentButton.click();
		verifyAutoPayOnFE(ADDRESS_LINE_1_DEFAULT, CITY_DEFAULT, STATE_DEFAULT, ZIP5_DEFAULT, ABA_NUMBER_DEFAULT, CHECKING_ACCOUNT_NUMBER_DEFAULT,
				paymentAmount, paymentDate);
		EAutopayPaymentSuccessPage.load().logOutLink.click();

		// Logout and verify
		navigateTo(FS_URL);
		LoginPage.load().login(FS_TESTBED_USERNAME, FS_PASSWORD);
		PersonalImagePage.load().selectBasketImage();
		NavigationPage.load().eAutopayLink.click();
		NavigationPage.load().managetPaymentAccountsSubLink.waitUntilElementPresent().click();
		verifyExternalDDAOnFE(ABA_NUMBER_DEFAULT, CHECKING_ACCOUNT_NUMBER_DEFAULT);
		verifyAutoPayInDB(paymentAmount, SYSTEM_DATE, paymentDate);
	}

	/**
	 * CM has an enrolled checking account
	 */
	@Test(dataProvider = "eautpay_0002_data_table")
	public void test_eautpay_0002(String testCaseId, String testDescription, String internalStatus, String externalStatus, String creditLimit,
			String currentBalance, String lastStatementBalance, String minmumPaymentDue, String paymentType, String paymentAmount, String paymentDate)
			throws SQLException, ParseException {

		CCA_FAKEFDR1.enrollDda(ABA_NUMBER_DEFAULT);
		CCA_FAKEFDR1.consentDdaLegalTerm();
		updateFakeFdrCapTable(internalStatus, externalStatus, creditLimit, currentBalance, lastStatementBalance, minmumPaymentDue);

		go2AccountOverviewPage(FS_TESTBED_USERNAME);
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();

		paymentDate = parsePaymentDate(paymentDate);
		EAutopayMakeAPaymentPage.load().makePayment(paymentDate, paymentType, paymentAmount);
		// EAutopayEnrollTermsPage.load().enrollTerms();
		EAutopayConfirmPaymentPage.load().authorizePaymentButton.click();
		verifyAutoPayOnFE(ADDRESS_LINE_1_DEFAULT, CITY_DEFAULT, STATE_DEFAULT, ZIP5_DEFAULT, ABA_NUMBER_DEFAULT, CHECKING_ACCOUNT_NUMBER_DEFAULT,
				paymentAmount, paymentDate);
		EAutopayPaymentSuccessPage.load().logOutLink.click();
		verifyAutoPayInDB(paymentAmount, SYSTEM_DATE, paymentDate);
		verifyAffinityNoteInDB("1");

		//go2AccountOverviewPage();
	}

	/**
	 * Edit bank info
	 */
	@Test(dataProvider = "eautpay_0003_data_table")
	public void test_eautpay_0003(String testCaseId, String testDescription, String internalStatus, String externalStatus, String creditLimit,
			String currentBalance, String lastStatementBalance, String minmumPaymentDue, String paymentType, String paymentAmount, String paymentDate)
			throws SQLException, ParseException {
		CCA_FAKEFDR1.enrollDda(ABA_NUMBER_DEFAULT);
		CCA_FAKEFDR1.consentDdaLegalTerm();
		updateFakeFdrCapTable(internalStatus, externalStatus, creditLimit, currentBalance, lastStatementBalance, minmumPaymentDue);
		go2AccountOverviewPage(FS_TESTBED_USERNAME);
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();
		EAutopayMakeAPaymentPage.load().editAccountLink.click();
		EAutopayEditBankInformationPage.load().editBankInfo(ABA_NUMBER_2, CHECKING_ACCOUNT_NUMBER_2);
		EAutopayConfirmEditBankInformationPage.load().continueButton.click();
		EAutopayEnrollTermsPage.load().enrollTerms();

		paymentDate = parsePaymentDate(paymentDate);
		EAutopayMakeAPaymentPage.load().makePayment(paymentDate, paymentType, paymentAmount);
		EAutopayConfirmPaymentPage.load().authorizePaymentButton.click();
		verifyAutoPayOnFE(ADDRESS_LINE_1_DEFAULT, CITY_DEFAULT, STATE_DEFAULT, ZIP5_DEFAULT, ABA_NUMBER_2, CHECKING_ACCOUNT_NUMBER_2, paymentAmount,
				paymentDate);
		EAutopayPaymentSuccessPage.load().logOutLink.click();
		verifyAutoPayInDB(paymentAmount, SYSTEM_DATE, paymentDate);
	}

	/**
	 * Add a second external checking account
	 */
	@Test(dataProvider = "eautpay_0004_data_table")
	public void test_eautpay_0004(String testCaseId, String testDescription, String internalStatus, String externalStatus, String creditLimit,
			String currentBalance, String lastStatementBalance, String minmumPaymentDue, String paymentType, String paymentAmount, String paymentDate)
			throws SQLException, ParseException {
		CCA_FAKEFDR1.enrollDda(ABA_NUMBER_DEFAULT);
		updateFakeFdrCapTable(internalStatus, externalStatus, creditLimit, currentBalance, lastStatementBalance, minmumPaymentDue);
		go2AccountOverviewPage(FS_TESTBED_USERNAME);
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();
		EAutopayMakeAPaymentPage.load().addAccountLink.click();
		EAutopayEnrollAccountPage.load().enrollAccount(ABA_NUMBER_2, CHECKING_ACCOUNT_NUMBER_2);
		EAutopayConfirmEnrollmentInformationPage.load().continueButton.click();
		EAutopayEnrollTermsPage.load().enrollTerms();

		paymentDate = parsePaymentDate(paymentDate);
		EAutopayMakeAPaymentPage.load().makePayment(paymentDate, paymentType, paymentAmount);
		EAutopayConfirmPaymentPage.load().authorizePaymentButton.click();
		verifyAutoPayOnFE(ADDRESS_LINE_1_DEFAULT, CITY_DEFAULT, STATE_DEFAULT, ZIP5_DEFAULT, ABA_NUMBER_2, CHECKING_ACCOUNT_NUMBER_2, paymentAmount,
				paymentDate);
		EAutopayPaymentSuccessPage.load().logOutLink.click();
		verifyAutoPayInDB(paymentAmount, SYSTEM_DATE, paymentDate);
	}

	/**
	 * Edit personal info
	 */
	@Test(dataProvider = "eautpay_0005_data_table")
	public void test_eautpay_0005(String testCaseId, String testDescription, String internalStatus, String externalStatus, String creditLimit,
			String currentBalance, String lastStatementBalance, String minmumPaymentDue, String paymentType, String paymentAmount, String paymentDate)
			throws SQLException, ParseException {
		CCA_FAKEFDR1.enrollDda(ABA_NUMBER_DEFAULT);
		updateFakeFdrCapTable(internalStatus, externalStatus, creditLimit, currentBalance, lastStatementBalance, minmumPaymentDue);
		go2AccountOverviewPage(FS_TESTBED_USERNAME);
		AccountOverviewPage.load().makeAPaymentWithEautopayImageLink.click();
		EAutopayMakeAPaymentPage.load().editAddressLink.click();
		EAutopayEditNameAndAddressPage.load().editPersonInfo("950 tanbark street", "san rafael", "94903");
		EAutopayConfirmEditNameAndAddressPage.load().continueButton.click();
		EAutopayEnrollTermsPage.load().enrollTerms();

		paymentDate = parsePaymentDate(paymentDate);
		EAutopayMakeAPaymentPage.load().makePayment(paymentDate, paymentType, paymentAmount);
		EAutopayConfirmPaymentPage.load().authorizePaymentButton.click();
		verifyAutoPayOnFE("950 tanbark street", "san rafael", "ca", "94903", ABA_NUMBER_DEFAULT, CHECKING_ACCOUNT_NUMBER_DEFAULT, paymentAmount,
				paymentDate);
		EAutopayPaymentSuccessPage.load().logOutLink.click();
		verifyAutoPayInDB(paymentAmount, SYSTEM_DATE, paymentDate);
	}


	private Object[][] getDataTable(String tableName) {
		//String xlFilePath = "resource/xls/scenarios/FSCM EAUTOPAY Scenarios for regression.xls";
		//String xlFilePath = "testcases/fs/FSCM EAUTOPAY Scenarios for regression.xls";	
		String xlFilePath = "FSCM EAUTOPAY Scenarios for regression.xls";
		String sheetName = "scenario test conditions matrix";
		String[][] dtatTable = (String[][]) Tools.getDataByTableName(xlFilePath, sheetName, tableName, executeRow);
		return (dtatTable);
	}

	@DataProvider(name = "eautpay_0001_data_table")
	private Object[][] eautpay_0001_Data() throws Exception {
		return getDataTable("eautpay_0001_data_table");
	}

	@DataProvider(name = "eautpay_0002_data_table")
	private Object[][] eautpay_0002_Data() throws Exception {
		return getDataTable("eautpay_0002_data_table");
	}

	@DataProvider(name = "eautpay_0003_data_table")
	private Object[][] eautpay_0003_Data() throws Exception {
		return getDataTable("eautpay_0003_data_table");
	}

	@DataProvider(name = "eautpay_0004_data_table")
	private Object[][] eautpay_0004_Data() throws Exception {
		return getDataTable("eautpay_0004_data_table");
	}

	@DataProvider(name = "eautpay_0005_data_table")
	private Object[][] eautpay_0005_Data() throws Exception {
		return getDataTable("eautpay_0005_data_table");
	}

	private void verifyAutoPayInDB(String expectedPaymentAmount, String expectedPaymentDate, String expectedDueDate) throws SQLException {
		// 101 is the date style -- mm/dd/yyyy
		String strSql = "select convert(varchar,payamt) as payamt, convert(varchar, pdate, 101) as pdate ,convert(varchar, duedate, 101)as duedate from autopay where creditcardnum1 = '"
				+ CCA_FAKEFDR1.getAccountNumber() + "'";
		Map<String, Object> resultMap = AUTOPAY.queryForMap(strSql);
		String actPaymentAmount = (String) resultMap.get("payamt");
		String actualPaymentDate = (String) resultMap.get("pdate");
		String actualDueDate = (String) resultMap.get("duedate");
		verifyEquals("verifyAutoPayInDB:" + actPaymentAmount + ":" + expectedPaymentAmount, actPaymentAmount, expectedPaymentAmount.replace(",", ""));
		verifyEquals("verifyAutoPayInDB" + actualPaymentDate + ":" + expectedPaymentDate, actualPaymentDate, expectedPaymentDate);
		verifyEquals("verifyAutoPayInDB" + actualDueDate + ":" + expectedDueDate, actualDueDate, expectedDueDate);

	}


	private void verifyAffinityNoteInDB(String expCount) throws SQLException {

		String strSql = "select count(*) from dbo.cisnote where mem_cardhdlr = '" + CCA_FAKEFDR1.getAccountNumber() + "'";
		String noteCount = AFFINITY_NOTES.queryForInt(strSql) + "";
		verifyEquals("verifyAffinityNoteInDB", noteCount, expCount);
	}

	private void verifyAutoPayOnFE(String expectedAddressLine1, String expectedCity, String expectedState, String expectedZipCode,
			String expectedAbaNumber, String expectedDDANumber, String expectedPaymentAmount, String expectedPaymentDate) throws SQLException {

		EAutopayPaymentSuccessPage.load().paymentInfoList.verifyMatchesByLowerCase(creatDefaultPattern("\\d+", "pfirstname", "plastname",
				expectedAddressLine1.toLowerCase(), expectedCity.toLowerCase(), expectedState.toLowerCase(), expectedZipCode,
				expectedAbaNumber.substring(expectedAbaNumber.length() - 4), expectedDDANumber.substring(expectedDDANumber.length() - 4),
				expectedPaymentAmount, expectedPaymentDate));
	}

	private void verifyExternalDDAOnFE(String expectedAbaNumber, String expectedDDANumber) {
		String ddsStr = creatDefaultPattern("pfirstname", "plastname", "addressline1", "san francisco", "ca", "94111",
				expectedAbaNumber.substring(expectedAbaNumber.length() - 4), expectedDDANumber.substring(expectedDDANumber.length() - 4));
		EAutopayManagePaymentAccountsPage.load().firstAccoutInfo.verifyMatchesByLowerCase(ddsStr);
	}


//	private void go2AccountOverviewPage() {
//		navigateTo(FS_URL);
//		LoginPage.load().login(USERNAME, PASSWORD);
//		PersonalImagePage.load().selectBasketImage();
//	}

	private void updateFakeFdrCapTable(String internalStatus, String externalStatus, String creditLimit, String currentBalance,
			String lastStatementBalance, String minmumPaymentDue) {
		if (internalStatus.isEmpty())
			internalStatus = " ";
		if (externalStatus.isEmpty())
			externalStatus = " ";
		StringBuilder sql = new StringBuilder();
		sql.append("update FFDRADMIN.CAP c set ");
		sql.append("INRL_STTS_CD = '" + internalStatus + "', ");
		sql.append("EXTR_STTS_CD = '" + externalStatus + "', ");
		sql.append("CRDT_LIMT_AM = '" + creditLimit + "', ");
		sql.append("CRRN_BAL_AM = '" + currentBalance + "', ");
		sql.append("LAST_STMT_BAL_AM = '" + lastStatementBalance + "', ");
		// If (Current Balance F01014 less Credit Limit F01021) is a positive
		// number add this calculated value to Amt Due F01017 else display Amt
		// Due F01017
		sql.append("CTD_UNPD_BPD_AM = '" + minmumPaymentDue + "' ");
		sql.append("where ENTR_ID = '" + CCA_FAKEFDR1.getAccountNumber() + "'");
		COD.execute(sql.toString());
	}

	/**
	 * Convert the payment date ("Current Date" and "Payment Due Date" in excel)
	 * to real date string. For example. 12/28/12
	 */
	private String parsePaymentDate(String paymentDate) {
		String myPaymentDate = "";
		if (paymentDate.toLowerCase().contains("current")) {
			myPaymentDate = SYSTEM_DATE;
		} else if (paymentDate.toLowerCase().contains("payment due")) {
			myPaymentDate = EAutopayMakeAPaymentPage.load().dueDateValue.getText();
		}
		return myPaymentDate;
	}

	// /**
	// * the method is designed to replace a string in the input array
	// * for example, replace the $ sign. $75 to 70
	// */
	// private void parseInputData(String[][] source, String before, String
	// after) {
	// for (int i = 0; i < source.length; i++) {
	// for (int j = 0; j < source[i].length; j++) {
	// source[i][j] = source[i][j].replace(before, after);
	// }
	// }
	// }
}
