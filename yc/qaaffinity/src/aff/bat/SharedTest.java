package aff.bat;


import org.testng.ITestResult;
import org.testng.annotations.*;
import aff.BaseTestSuiteAffinity;
import aff.pages.*;
import static applications.BasePage.*;
import static aff.pages.AffinityBasePage.*;
import static aff.AffinityConstants.*;
import static myconstant.TestAccounts.*;


@SuppressWarnings("static-access")
public class SharedTest extends BaseTestSuiteAffinity {
	

	
	
//	@Test()
//	public void autopayTest(){
//		AffNavigationPage.load().paymentLink.click();
//		AffNavigationPage.load().autopaySubTab.waitUntilElementPresent().click();	
//		AutopayPopupPage autopayPopupPage = AutopayPopupPage.load();
//		autopayPopupPage.okButton.waitUntilElementPresent().click();
//		autopayPopupPage.close();
//	}
//	
//	@Test()
//	public void c2cTest()
//	{		
//		AffNavigationPage.load().paymentLink.click();
//		AffNavigationPage.load().c2cTab.click();
//		
//		Credit2C1Page c2C1Page = Credit2C1Page.load();
//		c2C1Page.lastFourDigits.clearSet("5677");
//		c2C1Page.expirationMonth.clearSet("07");
//		c2C1Page.expirationYear.clearSet("18");
//		c2C1Page.nextButton.click();
//	//Enroll Source Account
//		Credit2C2Page c2C2Page = Credit2C2Page.load();	
//		c2C2Page.cardTypeDropDown.selectByVisibleText("Visa");
//		c2C2Page.nameOnCardEditBox.clearSet("Affinity BAT");
//		c2C2Page.callerIsOwnerRadioBox.click();	
//		c2C2Page.nextButton.click();
//	//Enroll Source Account	
//		Credit2C3Page c2C3Page = Credit2C3Page.load();
//		c2C3Page.accountNumberFst4DigitsEditBox.clearSet("4552");
//		c2C3Page.accountNumberSnd4DigitsEditBox.clearSet("7204");
//		c2C3Page.accountNumberTrd4DigitsEditBox.clearSet("1234");
//		c2C3Page.nickNameEditBox.clearSet( "Affinity BAT 2");
//		c2C3Page.Pull1FBAddrButton.click();
//		c2C3Page.phone1EditBox.clearSet("415");
//		c2C3Page.phone2EditBox.clearSet("232");
//		c2C3Page.phone3EditBox.clearSet("1234");
//		c2C3Page.storeDetailNoRadioBox.click();
//		c2C3Page.nextButton.click();
//	//C2C Transfer - Required Information
//		Credit2C4Page c2C4Page = Credit2C4Page.load();
//		c2C4Page.transferAmountEditBox.clearSet("12");
//		c2C4Page.nextButton.click();
//	//C2C Transfer -  Confirmation
//		Credit2C5Page c2C5Page = Credit2C5Page.load();
//		c2C5Page.addC2CButton.click();
//	//Transfer Successful
//		Credit2C6Page c2C6Page = Credit2C6Page.load();
//		c2C6Page.okButton.click();	
//		AffNavigationPage.load().paymentLink.click();	
//	}
//	
//	@Test()
//	public void transactionTest(){
//		AffNavigationPage.load().paymentLink.click();		
//		AffNavigationPage.load().transactionSubTab.click();
//		Transactions1Page transactions1Page = Transactions1Page.load();
//		transactions1Page.clickPend5677Transaction();
//	//View Funds Transfer Detail
//		Transactions2Page transactions2Page = Transactions2Page.load();
//		transactions2Page.transferChngRsn.selectByVisibleText("Cust Upset");
//		transactions2Page.cancelButton.click();
//	//Cancel Funds Transfer Transactions	
//		TransactionsCancel1Page transactionsCancel1Page = TransactionsCancel1Page.load();
//		transactionsCancel1Page.expirationDateMonth.clearSet("07");
//		transactionsCancel1Page.expirationDateYear.clearSet("18");
//		transactionsCancel1Page.nextButton.click();
//	//Confirm Cancel Funds Transfer Transactions	
//		TransactionsCancel2Page transactionsCancel2Page = TransactionsCancel2Page.load();
//		transactionsCancel2Page.okButton.click();
//	}
//	
//	@Test()
//	public void constantDataPanelTest() {		
//		DataPanelPage.load().constantDataPanel.verifyContains(ACCOUNT_ID);
//	}		
//	
//	@Test(priority = 45)
//	public void noteTest() {		
//		//MainPage.load().getReady();
//		AddNotePage addNotePage = AddNotePage.load();
//		String criticalNote = "CRITICAL" + SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(Date());;
//		addNotePage.addNote(criticalNote, 3);
//		
////		MainPage.load().getReady();
////		String regularNote = "REGULAR" + SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(Date());;
////		addNotePage.addNote(regularNote, 1);
//		
//		AffNavigationPage.load().notesLink.click();
//		AffNavigationPage.load().condensedTab.click();
//		CondensedNotePage.load().notesDataTable.verifyContains(criticalNote);
////		condensedNotePage.notesDataTable.verifyContains(regularNote);
//		AffNavigationPage.load().criticalTab.click();
//		
//		CriticalNotePage.load().firstNote.click();
//		AffNavigationPage.load().expandedTab.click();
//	}	
//	
//	@Test(priority = 50)
//	public void monGeneralTest() {
//		AffNavigationPage.load().monLink.click();
//		MonHomePage.load().cycle2DateLink.click();
//		MonCycle2DatePage.load();
//		
//		AffNavigationPage.load().monLink.click();
//		MonHomePage.load().declinedAuthorizationLink.click();
//		MonDeclineAuthorizationsPage.load();
//		
//		AffNavigationPage.load().monLink.click();		
//		MonHomePage.load().onlineStatementLink.click();
//		MonOnlineStatementsPage.load();
//		
//		AffNavigationPage.load().monLink.click();
//		MonHomePage.load().paymentHistoryLink.click();
//		MonPaymentHistoryPage.load();
//		
//		AffNavigationPage.load().monLink.click();
//		MonHomePage.load().statementHistoryLink.click();
//		MonStatementHistoryPage.load();
//	}
//		
//	@Test()
//	public void nonMonGeneralTest() {
//		AffNavigationPage.load().nonmonLink.click();
//		NonMonHomePage.load().cmWebAccessLink.click();
//		CmWebAccessPage cmWebAccessPage = CmWebAccessPage.load();
//		cmWebAccessPage.scriptBox.verifyContains("CMW_");
//		cmWebAccessPage.okButton.click();
//		
//		NonMonHomePage.load().overLimitServiceLink.click();
//		AffNavigationPage.load().errorMessage.verifyContains("A_0367 - User is not authorized to use Over Limit Service.");		
//	}
//		
//	@Test()
//	public void nonMonChangeDemoTest() {
//		//OdsSql.updateExternalStatus(ACCOUNT_NUMBER, "");
//		AffNavigationPage.load().nonmonLink.click();
//		NonMonHomePage.load().changCmDemoLink.click();		
//		ChangeDemoPage changeDemoPage = ChangeDemoPage.load();
//		String tempAddress1 = SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(Date());
//		changeDemoPage.address1EditBox.clearSet(tempAddress1);
//		changeDemoPage.updateButton.click();
//		changeDemoPage = ChangeDemoPage.load();
//		changeDemoPage.address1EditBox.verifyContains(tempAddress1);
//		changeDemoPage.doneButton.click();
//	}
//	
//	@Test()
//	public void nonMonPermission2DiscussTest() {
//		AffNavigationPage.load().nonmonLink.click();
//		NonMonHomePage.load().p2dLink.click();	
//		String timeString = (Date()).toString().toUpperCase();
//		Permission2DiscussPage.load().addPermission(timeString, "Test", "AUNT");
//		Permission2DiscussPage permission2DiscussPage = Permission2DiscussPage.load();
//		permission2DiscussPage.ActiveP2DTable.verifyContains(timeString);
//		//permission2DiscussPage.firstEditReleationship.verifyFirstSelectedOption("AUNT");
//		permission2DiscussPage.removeFirstPermission();
//		permission2DiscussPage.ActiveP2DTable.verifyNotContains(timeString);
//		Permission2DiscussPage.load().doneButton.click();
//	}	
//	
//	@Test()
//	public void nonMonStatementmentServiceTest(){
//		AffNavigationPage.load().nonmonLink.click();
//		NonMonHomePage.load().stmtServiceLink.click();
//		StatementServicePage.load().terminateButton.click();	
//	}
//
	@Test()
	public void nonMonTermsTest(){
		AffNavigationPage.load().nonmonLink.click();
		NonMonHomePage.load().termsLink.click();
		TermsPage.load().scriptButton.click();
		TermsPage.load().termsScriptCode.verifyContains("APR_");
	}
	
	@Test()
	public void nonMonTextBankingTest(){
		AffNavigationPage.load().nonmonLink.click();
		NonMonHomePage.load().textBankingServiceLink.click();
		TextBankingPage.load().okButton.click();		
	}

}
