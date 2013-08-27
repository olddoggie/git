package aff.function_testing.accountsecurity;

import org.testng.annotations.*;

import org.testng.annotations.Test;

import database.SecurityWordSql;
import frameLib.listener.*;

import static aff.Actions.*;
import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;
import static frameLib.FrameUtilities.creatDefaultPattern;
import aff.pages.*;
import applications.*;
import utilityLib.*;

@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })
public class TestAddAccountSecurityByNormalAgent extends BaseTestSuite {
//	String accountNumber;
//	String normalAgentFdrCode;
//	int normalAgentFdrCodeIndex;

	@BeforeClass(alwaysRun = true)
	public void setUpBeforeClass() {
//		appConfigures = ApplicationProperties.getProperties("properties/configure.properties");
//		accountNumber = appConfigures.getProperty("accountSecurityAcctNumber");
//		normalAgentFdrCode = appConfigures.getProperty("normalAgentFdrCode");
//		normalAgentFdrCodeIndex = Integer.parseInt(appConfigures.getProperty("normalAgentFdrCodeIndex"));

		loadWebDriver("ie");
		fromLandingPage2SearchPage();

	}

	public void fromLandingPage2SearchPage() {
		new LandingPage(driver).open(CSR_URL);
		new LoginPage(driver).loginAff(ODS_USER_NAME, ODS_PASSWORD, NORMAL_AGENT_FDR_INDEX);
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

	// ActivitySecurity is not checked in search and corresponding mode
	@Test()
	public void testM227() {
		SecurityWordSql.deleteSecurityWord(ACCOUNT_NUMBER);

		fromSearchPage2AccountSecurityHomePage(driver, ACCOUNT_NUMBER, "radioRES");
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.historyButton.verifyEnabled(false);
		accountSecurityHomePage.submitButton.verifyEnabled(false);
		accountSecurityHomePage.verifyPageContains("PWD_0001");

		accountSecurityHomePage.cancelButton.click();
		DataPanelPage dataPanelPage = new DataPanelPage(driver);
		expected = creatDefaultPattern("Account Security", "Security Hint:", "None", "Security Word:", "None");
		dataPanelPage.accountSecuritySection.verifyMatches(expected);
		dataPanelPage.accountSecurityLinkInVerifyVDP.click();
		new AccountSecurityHomePage(driver).cancelButton.click();
	}

}
