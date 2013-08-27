package com.onefbusa.op.test;

import com.onefbusa.op.pages.AgentDashboardPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.pages.LoginPage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class UserSignOut {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail,TestData.Login_AgentPassword);
        AgentDashboardPage.load();
    }

    @Test(groups = { "CAT1" }, description = ("test user sign out"))
    public void CP_100_5_1() throws Exception{
        SignOut_Succeed();
        HomePage.load().ready();
    }

    // No prompt box in CAT1
    @Test(enabled = false)
    public void CP_100_5_2() throws Exception{
        AgentDashboardPage.signOut_link.click();
        AgentDashboardPage.signOut_table.verifyContains("SIGN OUT OF OP.COM?");
        AgentDashboardPage.signOutYes_link.verifyElementPresent();
        AgentDashboardPage.signOutNo_link.verifyElementPresent();
    }

    @Test(enabled = false, description = ("test if user click NO button while sign out, page stays"))
    public void CP_100_5_3() throws Exception{
        SignOut_Fail();
        AgentDashboardPage.load().ready(TestData.Login_AgentFirstName);
    }

    @Test(groups = { "CAT1" }, dependsOnMethods = {"CP_100_5_1"},
            description = ("test after sign out, user has no way to access previous Role page without log in again"))
    public void CP_100_5_4() throws Exception{
        SignOut_Succeed();
        HomePage.load();
        navigateTo(General.AgentDashboard_URL);
        LoginPage.load().ready();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
