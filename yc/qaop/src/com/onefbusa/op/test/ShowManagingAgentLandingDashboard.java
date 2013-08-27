package com.onefbusa.op.test;

import com.onefbusa.op.pages.AgentDashboardPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class ShowManagingAgentLandingDashboard {
	
    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
    }

    @Test (groups = { "BAT" }, description = ("test agent dashboard after login successfully"))
    public void CP_200_1_1() throws Exception{
        AgentDashboardPage.load().ready(TestData.Login_AgentFirstName);
        verifyMultipleElementsPresent(AgentDashboardPage.originalDisplayedElements);
    }

    @Test(groups = { "BAT" }, description = ("agent sign out successfully, system goes to landing page"))
    public void CP_100_5_2() throws Exception{
        AgentDashboardPage.load();
        AgentDashboardPage.SignOut_Succeed();
        HomePage.load().ready();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
       closeBrowser();
    }

}
