package aff.bat;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import aff.BaseTestSuiteAffinity;
import aff.pages.*;
import static aff.properties.TestingAccountsProperties.*;


public class SharedTest extends BaseTestSuiteAffinity {
	
	@Test(priority = 20)
	public void searchTest(){
		SearchPage searchPage = new SearchPage(driver);
		searchPage.researchRadioButton.click();
		searchPage.uncheckAutoPullOn();
		//searchPage.searchByAcctID(ACCOUNT_ID);
		searchPage.searchByAcctNumber(ACCOUNT_NUMBER);
		//new MainPage(driver).getReady();
		// verify there is no error message; errorMessage is empty string; not null		
		new AffNavigationPage(driver).errorMessage.verifyEquals("");		
	}	
	@Test(priority = 50)
	public void autopayTest(){
		new AffNavigationPage(driver).paymentLink.click();
		new AffNavigationPage(driver).autopaySubTab.waitUntilElementPresent().click();	
		AutopayPopupPage autopayPopupPage = new AutopayPopupPage(driver);
		autopayPopupPage.okButton.waitUntilElementPresent().click();
		autopayPopupPage.close();
	}
	
	@Test(priority = 200)
	public void c2cTest()
	{		
		new AffNavigationPage(driver).paymentLink.click();
		new AffNavigationPage(driver).c2cTab.click();
		
		Credit2C1Page c2C1Page = new Credit2C1Page(driver);
		c2C1Page.lastFourDigits.clearSet("5677");
		c2C1Page.expirationMonth.clearSet("07");
		c2C1Page.expirationYear.clearSet("18");
		c2C1Page.nextButton.click();
	//Enroll Source Account
		Credit2C2Page c2C2Page = new Credit2C2Page(driver);	
		c2C2Page.cardTypeDropDown.selectByVisibleText("Visa");
		c2C2Page.nameOnCardEditBox.clearSet("Affinity BAT");
		c2C2Page.callerIsOwnerRadioBox.click();	
		c2C2Page.nextButton.click();
	//Enroll Source Account	
		Credit2C3Page c2C3Page = new Credit2C3Page(driver);
		c2C3Page.accountNumberFst4DigitsEditBox.clearSet("4552");
		c2C3Page.accountNumberSnd4DigitsEditBox.clearSet("7204");
		c2C3Page.accountNumberTrd4DigitsEditBox.clearSet("1234");
		c2C3Page.nickNameEditBox.clearSet( "Affinity BAT 2");
		c2C3Page.Pull1FBAddrButton.click();
		c2C3Page.phone1EditBox.clearSet("415");
		c2C3Page.phone2EditBox.clearSet("232");
		c2C3Page.phone3EditBox.clearSet("1234");
		c2C3Page.storeDetailNoRadioBox.click();
		c2C3Page.nextButton.click();
	//C2C Transfer - Required Information
		Credit2C4Page c2C4Page = new Credit2C4Page(driver);
		c2C4Page.transferAmountEditBox.clearSet("12");
		c2C4Page.nextButton.click();
	//C2C Transfer -  Confirmation
		Credit2C5Page c2C5Page = new Credit2C5Page(driver);
		c2C5Page.addC2CButton.click();
	//Transfer Successful
		Credit2C6Page c2C6Page = new Credit2C6Page(driver);
		c2C6Page.okButton.click();	
		new AffNavigationPage(driver).paymentLink.click();	
	}
	
	@Test(priority = 201)
	public void transactionTest(){
		new AffNavigationPage(driver).paymentLink.click();		
		new AffNavigationPage(driver).transactionSubTab.click();
		Transactions1Page transactions1Page = new Transactions1Page(driver);
		transactions1Page.clickPend5677Transaction();
	//View Funds Transfer Detail
		Transactions2Page transactions2Page = new Transactions2Page(driver);
		transactions2Page.transferChngRsn.selectByVisibleText("Cust Upset");
		transactions2Page.cancelButton.click();
	//Cancel Funds Transfer Transactions	
		TransactionsCancel1Page transactionsCancel1Page = new TransactionsCancel1Page(driver);
		transactionsCancel1Page.expirationDateMonth.clearSet("07");
		transactionsCancel1Page.expirationDateYear.clearSet("18");
		transactionsCancel1Page.nextButton.click();
	//Confirm Cancel Funds Transfer Transactions	
		TransactionsCancel2Page transactionsCancel2Page = new TransactionsCancel2Page(driver);
		transactionsCancel2Page.okButton.click();
	}
	
	@Test(priority = 50)
	public void constantDataPanelTest() {		
		new DataPanelPage(driver).constantDataPanel.verifyContains(ACCOUNT_ID);
	}		
	
	@Test(priority = 45)
	public void noteTest() {		
		//new MainPage(driver).getReady();
		AddNotePage addNotePage = new AddNotePage(driver);
		String criticalNote = "CRITICAL" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());;
		addNotePage.addNote(criticalNote, 3);
		
//		new MainPage(driver).getReady();
//		String regularNote = "REGULAR" + new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());;
//		addNotePage.addNote(regularNote, 1);
		
		new AffNavigationPage(driver).notesLink.click();
		new AffNavigationPage(driver).condensedTab.click();
		new CondensedNotePage(driver).notesDataTable.verifyContains(criticalNote);
//		condensedNotePage.notesDataTable.verifyContains(regularNote);
		new AffNavigationPage(driver).criticalTab.click();
		
		new CriticalNotePage(driver).firstNote.click();
		new AffNavigationPage(driver).expandedTab.click();
	}	
	
	@Test(priority = 50)
	public void monGeneralTest() {
		new AffNavigationPage(driver).monLink.click();
		new MonHomePage(driver).cycle2DateLink.click();
		new MonCycle2DatePage(driver);
		
		new AffNavigationPage(driver).monLink.click();
		new MonHomePage(driver).declinedAuthorizationLink.click();
		new MonDeclineAuthorizationsPage(driver);
		
		new AffNavigationPage(driver).monLink.click();		
		new MonHomePage(driver).onlineStatementLink.click();
		new MonOnlineStatementsPage(driver);
		
		new AffNavigationPage(driver).monLink.click();
		new MonHomePage(driver).paymentHistoryLink.click();
		new MonPaymentHistoryPage(driver);
		
		new AffNavigationPage(driver).monLink.click();
		new MonHomePage(driver).statementHistoryLink.click();
		new MonStatementHistoryPage(driver);
	}
		
	@Test(priority = 50)
	public void nonMonGeneralTest() {
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).cmWebAccessLink.click();
		CmWebAccessPage cmWebAccessPage = new CmWebAccessPage(driver);
		cmWebAccessPage.scriptBox.verifyContains("CMW_");
		cmWebAccessPage.okButton.click();
		
		new NonMonHomePage(driver).overLimitServiceLink.click();
		new AffNavigationPage(driver).errorMessage.verifyContains("A_0367 - User is not authorized to use Over Limit Service.");		
	}
		
	@Test(priority = 50)
	public void nonMonChangeDemoTest() {
		//OdsSql.updateExternalStatus(ACCOUNT_NUMBER, "");
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).changCmDemoLink.click();		
		ChangeDemoPage changeDemoPage = new ChangeDemoPage(driver);
		String tempAddress1 = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
		changeDemoPage.address1EditBox.clearSet(tempAddress1);
		changeDemoPage.updateButton.click();
		changeDemoPage = new ChangeDemoPage(driver);
		changeDemoPage.address1EditBox.verifyContains(tempAddress1);
		changeDemoPage.doneButton.click();
	}
	
	@Test(priority = 50)
	public void nonMonPermission2DiscussTest() {
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).p2dLink.click();	
		String timeString = (new Date()).toString().toUpperCase();
		new Permission2DiscussPage(driver).addPermission(timeString, "Test", "AUNT");
		Permission2DiscussPage permission2DiscussPage = new Permission2DiscussPage(driver);
		permission2DiscussPage.ActiveP2DTable.verifyContains(timeString);
		//permission2DiscussPage.firstEditReleationship.verifyFirstSelectedOption("AUNT");
		permission2DiscussPage.removeFirstPermission();
		permission2DiscussPage.ActiveP2DTable.verifyNotContains(timeString);
		new Permission2DiscussPage(driver).doneButton.click();
	}	
	
	@Test(priority = 50)
	public void nonMonStatementmentServiceTest(){
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).stmtServiceLink.click();
		new StatementServicePage(driver).terminateButton.click();	
	}

	@Test(priority = 50)
	public void nonMonTermsTest(){
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).termsLink.click();
		new TermsPage(driver).scriptButton.click();
		new TermsPage(driver).termsScriptCode.verifyContains("APR_");
	}
	
	@Test(priority = 50)
	public void nonMonTextBankingTest(){
		new AffNavigationPage(driver).nonmonLink.click();
		new NonMonHomePage(driver).textBankingServiceLink.click();
		new TextBankingPage(driver).okButton.click();		
	}

}
