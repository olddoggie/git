package aff;

import org.testng.annotations.*;
import frameLib.listener.*;
import aff.pages.*;
import applications.*;
import rtb.*;


//@Listeners({ MyTestListener.class, MyTestInvokedMethodListener.class,MyGroupInterceptor.class})
@Listeners({ MyTestVerifyAssertListener.class,MyGroupListener.class})
public class BaseTestSuiteAffinity extends BaseTestSuite {
		
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
