package aff;


import static applications.BasePage.navigateTo;
import static myconstant.General.*;
import utilityLib.Tools;
import aff.pages.*;
import applications.*;



@SuppressWarnings("static-access")
public class BaseTestSuiteAffinity extends BaseTestSuite {
	
	
	public static void login(String url) {
		navigateTo(url);
		LandingPage.load().submitForm();
		AffLoginPage.load().loginAff(ODS_USER_NAME, ODS_PASSWORD,
				SECURITY_AGENT_FDR_INDEX);
	}
	
	public static void resetAffinity() {
		// String[] processNames = { "iexplore.exe", "extra.exe",
		// "affinitysharevariables.exe", "ffbsharedmemory.exe" };
		Tools.killProcess("iexplore.exe");
		Tools.killProcess("IEDriverServer.exe");
		//killProcesses("ffbsharedmemory.exe");
	}
	
	
	
	
//	
//	public void fromLandingPage2SearchPage(String application)
//	{	
//	new LandingPage(driver).open(application);
//	new LoginPage(driver).loginAff(ODS_USER_NAME, ODS_PASSWORD);
//	}
//
//	
//	public void fromSearchPage2TermPage(String accountNumber) {
//		new SearchPage(driver).researchRadioButton.click();
//		new SearchPage(driver).searchByAcctNumber(accountNumber);
//		//new MainPage(driver).getReady();
//		new NavigationPage(driver).nonmonLink.click();
//		new NonMonHomePage(driver).termsLink.click();
//	}
}
