package test;import org.testng.ITestResult;import org.testng.annotations.*;import frameLib.listener.*;import static frameLib.MyWebDriverLib.*;import static properties.Affinity.*;import static properties.Common.*;import static properties.TestingAccounts.*;//someother description
@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })
public class TS_BySecurityAgent{
Private String TEST_NAME = "cream_test";
Private String PASS_WORD = "password1";

//Use Case 224: Account Security Summary
//1. Main Flow
//2. Collection
@Test(priority = 15)
public void TC10010(){
vDP_external.AccountSecuritySection_external.Set("aaa");
AffinityActions.Advance2SWHomePageFromCritcalNotePage;
AccountSecurityHomePage accountSecurityHomePage = new AccountSecurityHomePage(driver);
accountSecurityHomePage.SecurityHintEditbox.Set("this is the hint");
accountSecurityHomePage.SecurityWordEditbox.Set("security word");
accountSecurityHomePage.SubmitButton.Click();
AccountSecurityConfirmPage accountSecurityConfirmPage = new AccountSecurityConfirmPage(driver);
accountSecurityConfirmPage.Script(message).VerifyText("script message");
accountSecurityHomePage = new AccountSecurityHomePage(driver);
accountSecurityHomePage.SecurityHintEditbox.Set("this is the hint");
}

//Use Case 224: Account Security Summary
//1. Main Flow
//2. Collection
public void TC10020(){
loginPage.LoginAffinity("jyu",  "1234qwer");
AffinityActions.Advance2SWHomePageFromCritcalNotePage;
accountSecurityHomePage.SecurityHintEditbox.Set("Father' middle name");
accountSecurityHomePage.SecurityWordEditbox.Set("YU");
accountSecurityHomePage.SubmitButton.Click();
accountSecurityConfirmPage.Script.VerifyText("Father' middle name");
LoginPage loginPage = new LoginPage(driver);
loginPage.LoginAffinity("jyu",  "1234qwer");
COD cOD = new COD(driver);
loginPage.LoginAffinity("jyu",  "1234qwer");
}

//This is a wrapper function of LoginAffinity
public void myLogin(username,password)(){
accountSecurityConfirmPage.OkButton.Click();
nonmonHomePage.AccountSecurityLink.Click();
loginPage.LoginAffinity(username, password1).Set(username, password1);
}

//This is a wrapper function of LoginAffinity
public void myLogin2(username,password)(){
accountSecurityConfirmPage.OkButton.Click();
nonmonHomePage.AccountSecurityLink.Click();
loginPage.LoginAffinity(username, password1).Set(username, password1);
}

}
