package aff.bat;


import java.text.MessageFormat;
import org.testng.annotations.*;

import frameLib.FrameUtilities;
import aff.BaseTestSuiteAffinity;
import aff.pages.*;

import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;
import static frameLib.FrameUtilities.*;

@Listeners({frameLib.listener.MyTestCaseStatusListener.class,frameLib.listener.MyTestVerifyAssertListener.class} )
public class WebElementVerify extends BaseTestSuiteAffinity {

	@BeforeClass(alwaysRun = true)
	public void setUpBeforeTestSuite340CAT5() {
//		RtbUtil.deleteBwFolder("1fbbizprocess");
//		String[] files = { "resource/xls/QA_Term_cat4.xls" };
//		RtbUtil.putFiles2BW(files);
//		RtbUtil.generateScenarios("xls/QA_Term_cat4.xls");
		
		loadWebDriver("ie");
		new LandingPage(driver).open(CSR_URL);
	}

	@Test
	public void testWebElement() {
		FrameUtilities.verifyAllElementsPresent(new LoginPage(driver));	
	}

	
//	//@Test
//	public void testPromontionOnlyExpired() {
//		//4017240003000317
//		setScenarioFor1fbBizProcess("s_PromontionOnlyExpired");
//		fromSearchPage2TermPage("4017240003000317");
//		//fromSearchPage2TermPage(appConfigures.getProperty("accountNumber"));
//		TermsPage termsPage = new TermsPage(driver);
//		termsPage.currentTermsButton.click();
//		termsPage.currentTermsHead.click();
//		termsPage.currentTermsScript.verifyContains("APR_001");
////		termsPage.currentTerms.verifyContainsIngoreSpaces("Current Purchase APR", "1.00% PROMO");
////		termsPage.currentTerms.verifyContainsIngoreSpaces("Current Cash APR", "1.00% PROMO");
////		termsPage.currentTerms.verifyContainsIngoreSpaces("Interest Grace", "Purch + Cash");
//		
////		String scriptSay = "Your account is currently subject to the promotional APR of 1.00% for both purchases and cash. The promotional rate will remain in affect on your purchases and cash balance until {0}. After the promotional rate expires (on {1}), the APR for your entire balance will be 23.90% for purchases and 26.90% for cash. That amounts to a total interest charge of approximately $2.00 per month for every $100 you carry in an outstanding purchase balance and approximately $2.25 per month for every $100 you carry in an outstanding cash balance. You know, of course, that if you pay your balance in full each month, there is NO interest charged.";
////		//260 days is defined in s_PromontionOnly
////		String promotionExpireDate = getDay(260,"MM/dd/yyyy");
////		termsPage.termsScriptSay.verifyContainsIngoreSpaces(MessageFormat.format(scriptSay,promotionExpireDate,promotionExpireDate ));
//		termsPage.searchLink.click();
//	}	
//	
//	
//	public void fromSearchPage2TermPage(String accountNumber) {
//		new SearchPage(driver).researchRadio.click();
//		new SearchPage(driver).searchByAcctNumber(accountNumber);
//		new MainPage(driver).getReady();
//		new CriticalNotePage(driver).nonmonLink.click();
//		new NonmonHomePage(driver).termsLink.click();
//	}
}