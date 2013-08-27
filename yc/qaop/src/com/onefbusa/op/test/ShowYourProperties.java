package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class ShowYourProperties {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // pre step to create a clean property
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        PropertyDetailPage.properties_tab.click();
    }

    @Test(groups = { "BAT" }, description = ("test Show Your Properties page"))
    public void CP_220_1_1() throws Exception{
        AgentYourPropertiesPage.load().ready();
        String[] column_titles = {"NAME / ADDRESS", "FLOORS & UNIT", "PENDING REFUNDS"};
        verifyMultipleElementsEquals(AgentYourPropertiesPage.propertiesTable_titles,column_titles);
        verifyStatusColor(AgentYourPropertiesPage.propertiesTable_pendingRefunds1,"PENDING");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}