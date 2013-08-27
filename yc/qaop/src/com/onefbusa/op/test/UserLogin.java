package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class UserLogin {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
    }

    @Test(groups = { "BAT" },description = ("test agent log in"))
    public void CP_100_2_1() throws Exception{
        Login_Succeed(TestData.Login_AgentEmail,TestData.Login_AgentPassword);
        AgentDashboardPage.load().ready(TestData.Login_AgentFirstName);
    }

    @Test(groups = { "CAT1" },description = ("test agent login with wrong password"))
    public void CP_100_2_2() throws Exception{
        Login_Fail(TestData.Login_AgentEmail, "Wrong Password");
        HomePage.tryLoginAgain_link.verifyElementPresent();
        HomePage.forgotYourPassword_link.verifyElementPresent();
        verifyPageTitle(HomePage.pageTitle);
        String [] contents = {"There seems be an error.",
                "Try to login again?", "or Forgot your password?"
                } ;
        HomePage.loginFail_table.verifyContains(contents);
    }

    @Test(groups = { "CAT1" },description = ("test try log in again link after inputting wrong password"))
    public void CP_100_2_3() throws Exception{
        Login_Fail(TestData.Login_AgentEmail, "Wrong Password");
        HomePage.tryLoginAgain_link.click();
        HomePage.loginFail_table.verifyElementNotPresent();
        HomePage.login_table.verifyElementPresent();
    }

    @Test(groups = { "CAT1" },description = ("test forget password link after inputting wrong password"))
    public void CP_100_2_4() throws Exception{
        Login_Fail(TestData.Login_AgentEmail, "Wrong Password");
        HomePage.forgotYourPassword_link.click();
        ForgetPasswordPage.load().ready();
    }

    // Tenant Page not implemented yet..
    @Test(enabled = false)
    public void CP_100_3_1() throws Exception{
        Login_Succeed(TestData.Login_TenantEmail,TestData.Login_TenantPassword);
        TenantDashboardPage.load().ready(TestData.Login_TenantFirstName);
    }

    @Test(groups = { "BAT" },description = ("test admin log in"))
    public void CP_100_4_1() throws Exception{
        Login_Succeed(TestData.Login_AdminEmail,TestData.Login_AdminPassword);
        AdminDashboardPage.load().ready();  // admin dashboard no need pass username, because the admin page title always "DASHBOARD - PENDING APPROVALS"
    }

    @Test(groups = { "CAT1" },dependsOnMethods = {"CP_100_2_1"}, //if even CP_100_2_1 fail, then skip.
            description = ("agent log in successfully, then no login panel, and display sign out link"))
    public void CP_100_4_2() throws Exception{
        Login_Succeed(TestData.Login_AdminEmail,TestData.Login_AdminPassword);
        AgentDashboardPage.load();
        AgentDashboardPage.signOut_link.verifyElementPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
