package aff.function_testing.accountsecurity;

import org.testng.annotations.*;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.testng.annotations.Test;
import database.SecurityWordSql;
import frameLib.listener.*;
import static aff.Actions.*;
import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;
import aff.pages.*;
import applications.*;
import utilityLib.*;
import static frameLib.FrameUtilities.creatDefaultPattern;

@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })
public class TestAddAccountSecurityBySecurityAgent extends BaseTestSuite {
//	String accountNumber;
//	String securityAgentFdrCode;
//	int securityAgentFdrCodeIndex;

	@BeforeClass(alwaysRun = true)
	public void setUpBeforeClass() {
		loadWebDriver("ie");
		fromLandingPage2SearchPage();
	}

	public void fromLandingPage2SearchPage() {
		new LandingPage(driver).open(CSR_URL);
		new LoginPage(driver).loginAff(ODS_USER_NAME, ODS_PASSWORD, SECURITY_AGENT_FDR_INDEX);
	}

	@AfterClass(alwaysRun = true)
	public void tearDownAfterClass() {
		new MainPage(driver).closeAffinityBrowser();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUpBeforeMethod() {
		if (!MyTestCaseStatusListener.status) {
			new MainPage(driver).closeAffinityBrowser();
			loadWebDriver("ie");
			fromLandingPage2SearchPage();
			MyTestCaseStatusListener.status = true;
		}
	}

	@DataProvider(name = "securityWordDp")
	public Object[][] createSecurityWordDp() {
		return new Object[][] { { "radioINB", "Father's middle name", "", "yu", "01", "01", "2011" },
				{ "radioTRN", "Mother's middle name", "", "Brandon", "02", "27", "2011" },
				{ "radioOUT", "Name 1st school attnd in 3rd Grade", "", "securityword", "12", "31", "2012" },
				{ "radioCOR", "City living at age 16", "", "yu", "01", "01", "2011" },
				{ "radioWEB", "High school mascot", "", "yu", "01", "01", "2011" }, { "radioRES", "Other", "other Hint", "wu", "04", "01", "2012" },
				{ "radioOTH", "Other", "other Hint", "wu", "04", "01", "2012" } };
	}

	@Test(dataProvider = "securityWordDp")
	public void testM227(String channel, String securityHint, String otherSecurityHint, String securityWord, String effectiveMonth,
			String effectiveDay, String effectiveYear) {

		SecurityWordSql.deleteSecurityWord(ACCOUNT_NUMBER);
		// Verify account security home page
		SearchPage searchPage = new SearchPage(driver);
		searchPage.getChannelRadio(channel).click();
		searchPage.searchByAcctNumber(ACCOUNT_NUMBER);
		new MainPage(driver).getReady();
		new CriticalNotePage(driver).nonmonLink.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.verifyPageContains("PWD_0001");

		// Verify confirm page
		accountSecurityHomePage.addUpdateRemoveSW(securityHint, otherSecurityHint, securityWord, effectiveMonth, effectiveDay, effectiveYear);
		AccountSecurityConfirmPage accountSecurityConfirmPage = new AccountSecurityConfirmPage(driver);
		actual = accountSecurityConfirmPage.accountSecurityLeftTable.getText().replaceAll("\\n", "");
		if (otherSecurityHint.isEmpty()) {

			expected = creatDefaultPattern(securityHint, securityWord, effectiveMonth, effectiveDay, effectiveYear);
		} else {
			expected = creatDefaultPattern(otherSecurityHint, securityWord, effectiveMonth, effectiveDay, effectiveYear);
		}
		accountSecurityConfirmPage.accountSecurityLeftTable.verifyMatches(expected);
		accountSecurityConfirmPage.verifyPageContains("PWD_0002");

		// verify History page
		accountSecurityConfirmPage.okButton.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		new AccountSecurityHomePage(driver).historyButton.click();
		AccountSecurityHistoryPage accountSecurityHistoryPage = new AccountSecurityHistoryPage(driver);
		expected = creatDefaultPattern(new SimpleDateFormat("MM/dd/yyyy").format(new Date()), SECURITY_AGENT_FDR_CODE, "ADD", securityHint,
				securityWord, effectiveMonth, "/", effectiveDay, "/", effectiveYear);
		accountSecurityHistoryPage.myWebelement("xpath://table[@class='acctsec_hist_table']//tbody/tr[1]", "The first row in history page")
				.verifyMatches(expected);

		// verify result page
		accountSecurityHistoryPage.backButton.click();
		new AffNavigationPage(driver).resultLink.click();
		ResultCsrPage resultCsrPage = new ResultCsrPage(driver);
		resultCsrPage.securityActivityCheckbox.verifySelected(false);
		resultCsrPage.resolveCall();
	}

	// ActivitySecurity is not checked in search and corresponding mode
	@Test(groups = { "tg10_10" })
	public void testA227_10_0030() {
		SecurityWordSql.deleteSecurityWord(ACCOUNT_NUMBER);
		fromSearchPage2AccountSecurityHomePage(driver, ACCOUNT_NUMBER, "radioRES");
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.historyButton.verifyEnabled(false);
		accountSecurityHomePage.verifyPageContains("PWD_0001");

		accountSecurityHomePage.addUpdateRemoveSWQuick("City living at age 16");
		new AccountSecurityConfirmPage(driver).okButton.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.historyButton.verifyEnabled(true);
		accountSecurityHomePage.verifyPageContains("PWD_0003");
	}

	@Test(groups = { "tg10_20" })
	public void testA227_10_0010() {
		new AccountSecurityHomePage(driver).addUpdateRemoveSWQuick("Removed");
		new AccountSecurityConfirmPage(driver).okButton.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.historyButton.verifyEnabled(true);
		accountSecurityHomePage.verifyPageContains("PWD_0001");
	}

	@Test(groups = { "tg10_30" })
	public void testA227_40_0050() {
		new AccountSecurityHomePage(driver).sendLetterButton.click();
		new AccountSecurityConfirmPage(driver).verifyPageContains("PWD_0002");
		new AccountSecurityConfirmPage(driver).okButton.click();
		new NonMonHomePage(driver).accountSecurityLink.click();

	}

	@Test(groups = { "tg10_40" })
	public void testA227_40_0060() {
		new AccountSecurityHomePage(driver).cancelButton.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
	}

	@Test(groups = { "tg10_50" })
	public void testA227_40_0070() {
		new AccountSecurityHomePage(driver).historyButton.click();
		new AccountSecurityHistoryPage(driver).backButton.click();
		new AffNavigationPage(driver).resultLink.click();
		new ResultCsrPage(driver).resolveCall();
	}

	@Test(groups = { "tg20_10" })
	public void testA227_40_0020() {
		SecurityWordSql.deleteSecurityWord(ACCOUNT_NUMBER);
		fromSearchPage2AccountSecurityHomePage(driver, ACCOUNT_NUMBER, "radioRES");
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.addUpdateRemoveSW("Removed", "", "yu", "01", "01", "2011");
		accountSecurityHomePage.verifyPageContains("A_0402");

	}

	@Test(groups = { "tg20_20" })
	public void testA227_40_0025() {
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.addUpdateRemoveSW("", "", "yu", "01", "01", "2011");
		accountSecurityHomePage.verifyPageContains("A_0403");
	}

	@Test(groups = { "tg20_30" })
	public void testA227_40_0030() {
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.addUpdateRemoveSW("City living at age 16", "", "san rafael", "13", "01", "2011");
		accountSecurityHomePage.verifyPageContains("A_0404");

		accountSecurityHomePage.addUpdateRemoveSW("City living at age 16", "", "san rafael", "01", "34", "2011");
		accountSecurityHomePage.verifyPageContains("A_0404");

		accountSecurityHomePage.addUpdateRemoveSW("City living at age 16", "", "san rafael", "01", "31", "401");
		accountSecurityHomePage.verifyPageContains("A_0404");
	}

	@Test(groups = { "tg20_40" })
	public void testA227_40_0040() {
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.addUpdateRemoveSW("City living at age 16", "", "", "01", "01", "2011");
		accountSecurityHomePage.verifyPageContains("A_0405");
	}

}
