package com.onefbusa.op.test;

import static applications.BasePage.closeBrowser;
import static applications.BasePage.navigateTo;
import static applications.BasePage.openBrowser;
import static com.onefbusa.op.pages.OPBasePage.Login_Succeed;
import static com.onefbusa.op.pages.OPBasePage.verifyMultipleElementsPresent;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.onefbusa.op.pages.AdminDashboardPage;
import com.onefbusa.op.pages.AgentDashboardPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;

public class ShowOPAdminLandingDashboard {
	
    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AdminEmail, TestData.Login_AdminPassword);
    }

    @Test (groups = { "BAT" }, description = ("test admin user log in"))
    // User logged in as OP admin and see the correct title, Header and Footer
    public void CP_400_1_1() throws Exception{
    	AdminDashboardPage.load().ready();
        verifyMultipleElementsPresent(AdminDashboardPage.originalDisplayedElements);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
       closeBrowser();
    }

}
