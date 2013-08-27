package aff.function_testing.accountsecurity;


import org.testng.annotations.*;

import org.testng.annotations.Test;

import database.SecurityWordSql;
import frameLib.listener.*;

import static aff.Actions.*;
import static frameLib.FrameUtilities.*;
import aff.pages.*;
import applications.*;

@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })
//@Listeners({ MyTestListener.class, MyGroupInterceptor.class })
public class TestEditAccountSecurityBySecurityAgent extends TestAffCsrAccountSecurityBase {

	@Test(priority = -1, description = "this is the description of TestM228SetPrecondition")
	public void TestM228SetPrecondition() {
		SecurityWordSql.deleteSecurityWord(accountNumber);

		// set precondition by entering a security word
		fromSearchPage2AccountSecurityHomePage(driver, accountNumber, "radioRES");
		new AccountSecurityHomePage(driver).addUpdateRemoveSW("Father's middle name", "", "yu", "01", "01", "2011");
		new AccountSecurityConfirmPage(driver).okButton.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		new AccountSecurityHomePage(driver).resultLink.click();
		new ResultCsrPage(driver).resolveCall();
	}

	@Test(groups = { "M228_10" })
	public void testM228_10() {

		// verify homepage
		fromSearchPage2AccountSecurityHomePage(driver, accountNumber, "radioINB");
		verifyAccountSecurityHomePage("Father's middle name", "", "yu", "01", "01", "2011");

		// verify confirm page
		new AccountSecurityHomePage(driver).addUpdateRemoveSW("Mother's middle name", "", "Brandon", "02", "27", "2011");
		expected = creatDefaultPattern("Mother's middle name", "", "Brandon", "02", "27", "2011");
		AccountSecurityConfirmPage accountSecurityConfirmPage = new AccountSecurityConfirmPage(driver);
		accountSecurityConfirmPage.accountSecurityLeftTable.verifyMatches(expected);

		// verify History page
		new AccountSecurityConfirmPage(driver).okButton.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		new AccountSecurityHomePage(driver).historyButton.click();
		historyCurrent = creatDefaultPattern(todayStr, securityAgentFdrCode, "CHANGE", "Mother's middle name", "Brandon", "02/27/2011")
				+ creatDefaultPattern(todayStr, securityAgentFdrCode, "ADD", "Father's middle name", "yu", "01/01/2011");
		historyAll = historyCurrent + historyAll;
		;
		AccountSecurityHistoryPage accountSecurityHistoryPage = new AccountSecurityHistoryPage(driver);
		accountSecurityHistoryPage.historyTableBody.verifyMatches(historyAll);

		// verify result page
		accountSecurityHistoryPage.backButton.click();
		new AffNavigationPage(driver).resultLink.click();
		ResultCsrPage resultCsrPage = new ResultCsrPage(driver);
		resultCsrPage.securityActivityCheckbox.verifySelected(true);
		resultCsrPage.resolveCall();

	}

	@Test(groups = { "M228_20" })
	public void testM228_20() {
		{

			// verify account security home page
			fromSearchPage2AccountSecurityHomePage(driver, accountNumber, "radioTRN");
			verifyAccountSecurityHomePage("Mother's middle name", "", "Brandon", "02", "27", "2011");

			// Verify history page
			new AccountSecurityHomePage(driver).historyButton.click();
			AccountSecurityHistoryPage accountSecurityHistoryPage = new AccountSecurityHistoryPage(driver);
			accountSecurityHistoryPage.historyTableBody.verifyMatches(expected);
			accountSecurityHistoryPage.backButton.click();

			// verify confirm page
			new AccountSecurityHomePage(driver).addUpdateRemoveSW("Other", "other Hint", "wu", "04", "01", "2012");
			expected = creatDefaultPattern("other Hint", "wu", "04", "01", "2012");
			AccountSecurityConfirmPage accountSecurityConfirmPage = new AccountSecurityConfirmPage(driver);
			accountSecurityConfirmPage.accountSecurityLeftTable.verifyMatches(expected);

			// verify History page
			accountSecurityConfirmPage.okButton.click();
			new NonMonHomePage(driver).accountSecurityLink.click();
			new AccountSecurityHomePage(driver).historyButton.click();
			historyCurrent = creatDefaultPattern(todayStr, securityAgentFdrCode, "CHANGE", "other Hint", "wu", "04/01/2012");
			historyAll = historyCurrent + historyAll;
			accountSecurityHistoryPage = new AccountSecurityHistoryPage(driver);
			accountSecurityHistoryPage.historyTableBody.verifyMatches(historyAll);

			// verify result page
			accountSecurityHistoryPage.backButton.click();
			new AccountSecurityHomePage(driver).resultLink.click();
			ResultCsrPage resultCsrPage = new ResultCsrPage(driver);
			resultCsrPage.securityActivityCheckbox.verifySelected(true);
			resultCsrPage.resolveCall();

		}
	}

	@Test(groups = { "M228_30" })
	public void testM228_30() {
		{
			// //////////logout
			// verify account security home page
			fromSearchPage2AccountSecurityHomePage(driver, accountNumber, "radioRES");
			verifyAccountSecurityHomePage("Other", "other Hint", "wu", "04", "01", "2012");

			// Verify history page
			new AccountSecurityHomePage(driver).historyButton.click();
			AccountSecurityHistoryPage accountSecurityHistoryPage = new AccountSecurityHistoryPage(driver);
			accountSecurityHistoryPage.historyTableBody.verifyMatches(historyAll);
			accountSecurityHistoryPage.backButton.click();

			// verify confirm page
			new AccountSecurityHomePage(driver).addUpdateRemoveSW("Removed", "", "wu", "04", "27", "2012");
			expected = creatDefaultPattern("None", "None", "04", "27", "2012");
			AccountSecurityConfirmPage accountSecurityConfirmPage = new AccountSecurityConfirmPage(driver);
			accountSecurityConfirmPage.accountSecurityLeftTable.verifyMatches(expected);

			// verify History page
			new AccountSecurityConfirmPage(driver).okButton.click();
			new NonMonHomePage(driver).accountSecurityLink.click();
			new AccountSecurityHomePage(driver).historyButton.click();
			historyCurrent = creatDefaultPattern(todayStr, securityAgentFdrCode, "REMOVE", "None", "None", "04/27/2012");
			historyAll = historyCurrent + historyAll;
			accountSecurityHistoryPage = new AccountSecurityHistoryPage(driver);
			accountSecurityHistoryPage.historyTableBody.verifyMatches(historyAll);

			// verify result page
			accountSecurityHistoryPage.backButton.click();
			new AccountSecurityHomePage(driver).resultLink.click();
			ResultCsrPage resultCsrPage = new ResultCsrPage(driver);
			resultCsrPage.securityActivityCheckbox.verifySelected(false);
			resultCsrPage.resolveCall();

			// //////////logout
			// verify account security home page
			fromSearchPage2AccountSecurityHomePage(driver, accountNumber, "radioTRN");
			verifyAccountSecurityHomePage("", "", "", todayMonth, todayDay, todayYear);

			// Verify history page
			new AccountSecurityHomePage(driver).historyButton.click();
			accountSecurityHistoryPage = new AccountSecurityHistoryPage(driver);
			accountSecurityHistoryPage.historyTableBody.verifyMatches(historyAll);
			accountSecurityHistoryPage.backButton.click();
		}
	}
}
