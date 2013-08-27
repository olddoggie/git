//package fs.function_testing.login;
//
//import myconstant.TestingAccount;
//
//import org.testng.annotations.*;
//
//import applications.BasePage;
//
//
//import fs.BaseTestSuiteFs;
//import fs.pages.*;
//
//import utilityLib.Tools;
//import static fs.FsConstants.*;
//import static myconstant.AccountsProperties.*;
//import static myconstant.TestingAccount.*;
//
//@Listeners({ frameLib.listener.MyTestCaseStatusListener.class, frameLib.listener.MyTestVerifyAssertListener.class })
//public class TestSuite750CAT1 extends BaseTestSuiteFs {
//
//	@BeforeClass(alwaysRun = true)
//	public void setUpBeforeTestSuite340CAT5() {
//		openBrowser("ie");
//	}
//
//	@DataProvider(name = "login_mobile")
//	public Object[][] mpdStep1_4Data() throws Exception {
//		Object[][] retObjArr = Tools.getTableArray("resource/xls/fs_login_auto.xls", "login_mobile");
//		return (retObjArr);
//	}
//
//	@Test(dataProvider = "login_mobile")
//	public void testLogin(String caseId, String cca1, String cca2, String dda1, String dda2, String cca1IntStatus, String cca1ExtStatus,
//			String cca1Cb, String cca2IntStatus, String cca2ExtStatus, String cca2Cb, String dda1Status, String dda2Status) throws Exception {
//		if (caseId.startsWith("tc")) {
//			TestingAccount.resetTestBed();
//			procoessCCA(CCA1, cca1, cca1IntStatus, cca1ExtStatus, cca1Cb);
//			procoessCCA(CCA2, cca2, cca2IntStatus, cca2ExtStatus, cca2Cb);
//			procoessDDA1(dda1, dda1Status);
//			procoessDDA2(dda2, dda2Status);
//			System.out.print(caseId + " ");
//
//			new BasePage(driver).navigateTo(FSCM_MOBILE);
//			new MobileLoginPage(driver).login(ACTIVE_PARTY_USER_NAME, ACTIVE_PARTY_PASSWORD);
//			new MobilePersonalImagePage(driver).selectBasketImage();
//			System.out.println(driver.getTitle());
//			new BasePage(driver).navigateTo(FS_LOGOUT_URL);			
//		}
//
//	}
//
//	public static void procoessDDA2(String dda, String status) {
//		if (dda.equalsIgnoreCase("Y")) {
//			if (status.equalsIgnoreCase("active"))
//				DDA2_ACTIVE.add2TestBed();
//			if (status.equalsIgnoreCase("closed"))
//				DDA2_CLOSED.add2TestBed();
//			if (status.equalsIgnoreCase("dormant"))
//				DDA2_DORMANT.add2TestBed();
//			if (status.equalsIgnoreCase("locked"))
//				DDA2_LOCKED.add2TestBed();
//
//		}
//	}
//
//	public static void procoessDDA1(String dda, String status) {
//		if (dda.equalsIgnoreCase("Y")) {
//			if (status.equalsIgnoreCase("active"))
//				DDA1_ACTIVE.add2TestBed();
//			if (status.equalsIgnoreCase("closed"))
//				DDA1_CLOSED.add2TestBed();
//			if (status.equalsIgnoreCase("dormant"))
//				DDA1_DORMANT.add2TestBed();
//			if (status.equalsIgnoreCase("locked"))
//				DDA1_LOCKED.add2TestBed();
//
//		}
//	}
//
//	public static void procoessCCA(TestingAccount tAccount, String cca, String ccaIntStatus, String ccaExtStatus, String ccaCb) {
//		if (cca.equalsIgnoreCase("Y")) {
//			tAccount.add2TestBed();
//			//internal status and external status can't be null
//			if (ccaIntStatus.isEmpty()) ccaIntStatus = " ";			
//			if (ccaExtStatus.isEmpty()) ccaExtStatus = " ";
//			
//			tAccount.updateInternalStatus(ccaIntStatus);
//			tAccount.updateExternalStatus(ccaExtStatus);
//			tAccount.updateCrbrRprtCdInFakeFDR(ccaCb);
//
//	}
//	}
//	// public static String procoessDDA2(String dda, String status){
//	// String sql="";
//	// if(dda.equalsIgnoreCase("Y"))
//	// {
//	// if(status.equalsIgnoreCase("active")) sql =
//	// CodSql_obsolete.joinTestAccountSql(DDA2_ACTIVE_ACCOUNT_NUMBER);
//	// if(status.equalsIgnoreCase("closed"))sql =
//	// CodSql_obsolete.joinTestAccountSql(DDA2_CLOSED_ACCOUNT_NUMBER);
//	// if(status.equalsIgnoreCase("dormant"))sql =
//	// CodSql_obsolete.joinTestAccountSql(DDA2_DORMANT_ACCOUNT_NUMBER);
//	// if(status.equalsIgnoreCase("locked"))sql =
//	// CodSql_obsolete.joinTestAccountSql(DDA2_LOCKED_ACCOUNT_NUMBER);
//	//
//	// }
//	// return sql;
//	// }
//
//	// public static String procoessCCA1_org(String cca, String ccaIntStatus,
//	// String ccaExtStatus,String ccaCb){
//	// String sql="";
//	// if(cca.equalsIgnoreCase("Y"))
//	// {
//	// sql = CodSql_obsolete.joinTestAccountSql(CCA1_ACCOUNT_NUMBER);
//	// if(ccaIntStatus.isEmpty())ccaIntStatus = " ";
//	// sql = sql +
//	// CodSql_obsolete.updateInternalStatusInFakeFDRSql(CCA1_ACCOUNT_NUMBER,
//	// ccaIntStatus);
//	// if(ccaExtStatus.isEmpty())ccaExtStatus = " ";
//	// sql = sql +
//	// CodSql_obsolete.updateExternalStatusInFakeFDRSql(CCA1_ACCOUNT_NUMBER,
//	// ccaExtStatus);
//	// if(ccaCb.isEmpty())ccaCb = " ";
//	// sql = sql +
//	// CodSql_obsolete.updateCrbrRprtCdInFakeFDR(CCA1_ACCOUNT_NUMBER, ccaCb);
//	// }
//	// return sql;
//	// }
//	//
//	// public static String procoessCCA1(String cca, String ccaIntStatus, String
//	// ccaExtStatus,String ccaCb){
//	// String sql="";
//	// if(cca.equalsIgnoreCase("Y"))
//	// {
//	// CCA1.add2TestBed();
//	// if(ccaIntStatus.isEmpty())ccaIntStatus = " ";
//	// CCA1.updateInternalStatus(ccaIntStatus);
//	// if(ccaExtStatus.isEmpty())ccaExtStatus = " ";
//	// CCA1.updateExternalStatus(ccaExtStatus);
//	// if(ccaCb.isEmpty())ccaCb = " ";
//	// CCA1.updateCrbrRprtCdInFakeFDR(ccaCb);
//	// }
//	// return sql;
//	// }
//
//	// public static String procoessCCA2(String cca, String ccaIntStatus, String
//	// ccaExtStatus,String ccaCb){
//	// String sql="";
//	// if(cca.equalsIgnoreCase("Y"))
//	// {
//	// sql = CodSql_obsolete.joinTestAccountSql(CCA2_ACCOUNT_NUMBER);
//	// if(ccaIntStatus.isEmpty())ccaIntStatus = " ";
//	// sql = sql +
//	// CodSql_obsolete.updateExternalStatusInFakeFDRSql(CCA2_ACCOUNT_NUMBER,
//	// ccaIntStatus);
//	// if(ccaExtStatus.isEmpty())ccaExtStatus = " ";
//	// sql = sql +
//	// CodSql_obsolete.updateExternalStatusInFakeFDRSql(CCA2_ACCOUNT_NUMBER,
//	// ccaExtStatus);
//	// if(ccaCb.isEmpty())ccaCb = " ";
//	// sql = sql +
//	// CodSql_obsolete.updateCrbrRprtCdInFakeFDR(CCA2_ACCOUNT_NUMBER, ccaCb);
//	// }
//	// return sql;
//	// }
//
//	//
//	// @Test
//	// public void testScraOnly() {
//	// //5432270003000144
//	// setScenarioFor1fbBizProcess("s_ScraOnly");
//	// fromSearchPage2TermPage(ACCOUNT_NUMBER);
//	// TermsPage termsPage = new TermsPage(driver);
//	// termsPage.scriptButton.click();
//	// termsPage.currentTermsHead.click();
//	// termsPage.currentTermsScript.verifyContains("APR_026");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("1Current Purchase APR",
//	// "6.00%");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Current Cash APR",
//	// "6.00%");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("1Interest Grace",
//	// "Purch + Cash");
//	// termsPage.termsScriptSay
//	// .verifyContainsIngoreSpaces("1Your account is currently eligible for the Service Members Civil Relief Act (SCRA) rate of 6.00% for purchases and 6.00% for cash. That amounts to a total interest charge of approximately $2.00 per month for every $100 you carry in an outstanding purchase balance and approximately $2.25 per month for every $100 you carry in an outstanding cash balance. You know, of course, that if you pay your balance in full each month, there is NO interest charged");
//	// termsPage.searchLink.click();
//	// }
//	//
//	// //@Test
//	// //Difference between testScraOnly() --- "Current Purchase APR" = "0.00%"
//	// public void testScraOnly0PurcaseHighCash() {
//	// //5432270003000144
//	// setScenarioFor1fbBizProcess("s_ScraOnly");
//	// fromSearchPage2TermPage(ACCOUNT_NUMBER);
//	// TermsPage termsPage = new TermsPage(driver);
//	// termsPage.scriptButton.click();
//	// termsPage.currentTermsHead.click();
//	// termsPage.currentTermsScript.verifyContains("APR_026");
//	// // only difference from testScraOnly()
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Current Purchase APR",
//	// "0.00%");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Current Cash APR",
//	// "6.00%");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Interest Grace",
//	// "Purch + Cash");
//	// termsPage.termsScriptSay
//	// .verifyContainsIngoreSpaces("Your account is currently eligible for the Service Members Civil Relief Act (SCRA) rate of 6.00% for purchases and 6.00% for cash. That amounts to a total interest charge of approximately $2.00 per month for every $100 you carry in an outstanding purchase balance and approximately $2.25 per month for every $100 you carry in an outstanding cash balance. You know, of course, that if you pay your balance in full each month, there is NO interest charged");
//	// termsPage.searchLink.click();
//	// }
//	//
//	//
//	// //@Test
//	// public void testPromontionOnly() {
//	// //4017240003000317
//	// setScenarioFor1fbBizProcess("s_PromontionOnly");
//	// fromSearchPage2TermPage("4017240003000317");
//	// //fromSearchPage2TermPage(appConfigures.getProperty("accountNumber"));
//	// TermsPage termsPage = new TermsPage(driver);
//	// termsPage.scriptButton.click();
//	// termsPage.currentTermsHead.click();
//	// termsPage.currentTermsScript.verifyContains("APR_025");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Current Purchase APR",
//	// "1.00% PROMO");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Current Cash APR",
//	// "1.00% PROMO");
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Interest Grace",
//	// "Purch + Cash");
//	//
//	// String scriptSay =
//	// "Your account is currently subject to the promotional APR of 1.00% for both purchases and cash. The promotional rate will remain in affect on your purchases and cash balance until {0}. After the promotional rate expires (on {1}), the APR for your entire balance will be 23.90% for purchases and 26.90% for cash. That amounts to a total interest charge of approximately $2.00 per month for every $100 you carry in an outstanding purchase balance and approximately $2.25 per month for every $100 you carry in an outstanding cash balance. You know, of course, that if you pay your balance in full each month, there is NO interest charged.";
//	// //260 days is defined in s_PromontionOnly
//	// String promotionExpireDate = getDay(260,"MM/dd/yyyy");
//	// termsPage.termsScriptSay.verifyContainsIngoreSpaces(MessageFormat.format(scriptSay,promotionExpireDate,promotionExpireDate
//	// ));
//	// termsPage.searchLink.click();
//	// }
//	//
//	//
//	// //@Test
//	// public void testPromontionOnlyExpired() {
//	// //4017240003000317
//	// setScenarioFor1fbBizProcess("s_PromontionOnlyExpired");
//	// fromSearchPage2TermPage("4017240003000317");
//	// //fromSearchPage2TermPage(appConfigures.getProperty("accountNumber"));
//	// TermsPage termsPage = new TermsPage(driver);
//	// termsPage.scriptButton.click();
//	// termsPage.currentTermsHead.click();
//	// termsPage.currentTermsScript.verifyContains("APR_001");
//	// //
//	// termsPage.currentTerms.verifyContainsIngoreSpaces("Current Purchase APR",
//	// "1.00% PROMO");
//	// // termsPage.currentTerms.verifyContainsIngoreSpaces("Current Cash APR",
//	// "1.00% PROMO");
//	// // termsPage.currentTerms.verifyContainsIngoreSpaces("Interest Grace",
//	// "Purch + Cash");
//	//
//	// // String scriptSay =
//	// "Your account is currently subject to the promotional APR of 1.00% for both purchases and cash. The promotional rate will remain in affect on your purchases and cash balance until {0}. After the promotional rate expires (on {1}), the APR for your entire balance will be 23.90% for purchases and 26.90% for cash. That amounts to a total interest charge of approximately $2.00 per month for every $100 you carry in an outstanding purchase balance and approximately $2.25 per month for every $100 you carry in an outstanding cash balance. You know, of course, that if you pay your balance in full each month, there is NO interest charged.";
//	// // //260 days is defined in s_PromontionOnly
//	// // String promotionExpireDate = getDay(260,"MM/dd/yyyy");
//	// //
//	// termsPage.termsScriptSay.verifyContainsIngoreSpaces(MessageFormat.format(scriptSay,promotionExpireDate,promotionExpireDate
//	// ));
//	// termsPage.searchLink.click();
//	// }
//	//
//	//
//	// public void fromSearchPage2TermPage(String accountNumber) {
//	// new SearchPage(driver).researchRadioButton.click();
//	// new SearchPage(driver).searchByAcctNumber(accountNumber);
//	//new MainPage(driver).getReady();
//	// new CriticalNotePage(driver).nonmonLink.click();
//	// new NonMonHomePage(driver).termsLink.click();
//	// }
//}