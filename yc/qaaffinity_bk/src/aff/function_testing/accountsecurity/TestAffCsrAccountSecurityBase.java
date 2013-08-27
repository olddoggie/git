package aff.function_testing.accountsecurity;


import org.testng.annotations.*;

import frameLib.listener.*;

import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;
import static frameLib.FrameUtilities.formatToday;
import aff.bat.CSRTest;
import aff.pages.*;
import applications.*;
import utilityLib.*;

//@Listeners({ MyTestListener.class, MyTestInvokedMethodListener.class,MyGroupInterceptor.class})
@Listeners({ MyTestVerifyAssertListener.class,MyGroupListener.class})
public class TestAffCsrAccountSecurityBase extends BaseTestSuite {
	protected String accountNumber ;
	protected String securityAgentFdrCode;
	protected int securityAgentFdrCodeIndex;
	protected String historyAll = "";
	protected String historyCurrent = "";
	protected String todayStr = formatToday("MM/dd/yyyy");
	protected String todayMonth = todayStr.split("/")[0];
	protected String todayDay = todayStr.split("/")[1];
	protected String todayYear = todayStr.split("/")[2];
	
	@BeforeClass(alwaysRun = true)
	public void setUpBeforeClass() {
		
		loadWebDriver("ie");
		fromLandingPage2SearchPage();
	}

	
	public void fromLandingPage2SearchPage()
	{
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

	public void verifyAccountSecurityHomePage(String securityHint, String otherSecurityHint, String securityWord,
			String effectiveMonth, String effectiveDay, String effectiveYear)
	{
		AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
		accountSecurityHomePage.securityHintDropDown.verifyFirstSelectedOption(securityHint);

		if(!otherSecurityHint.isEmpty())
		{
			accountSecurityHomePage.otherSecurityHintEditBox.verifyEquals(otherSecurityHint, "value");
		}
		accountSecurityHomePage.securityWordEditBox.verifyEquals(securityWord, "value");
		accountSecurityHomePage.effectiveMonthEditBox.verifyEquals(effectiveMonth, "value");
		accountSecurityHomePage.effectiveDayEditBox.verifyEquals(effectiveDay, "value");
		accountSecurityHomePage.effectiveYearEditBox.verifyEquals(effectiveYear, "value");
	}
}
