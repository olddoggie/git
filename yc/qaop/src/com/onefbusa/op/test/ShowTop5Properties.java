package com.onefbusa.op.test;

import com.onefbusa.op.pages.AgentDashboardPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.pages.LeaseDetailPage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.AddLeaseScenario;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class ShowTop5Properties {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // prepare test data
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        AddLeaseScenario.Add_EndingSoon_Lease(TestData.AgencyName, TestData.Property_Name, TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
        LeaseDetailPage.load().dashboard_tab.click();
        AgentDashboardPage.load();
    }

    @Test(groups = { "BAT" }, description = ("test property top 5 table in agent dashboard page"))
    public void CP_200_3_1() throws Exception{
        AgentDashboardPage.properties_tableTitle.verifyContainsIgnoreSpaces("PROPERTIES");
        verifyMultipleElementsPresent(AgentDashboardPage.propertiesTableElements);
        String[] propertyRecord_1 = { TestData.Property_Name + ",", TestData.Property_Address1 + ",",
                TestData.Property_City + ",", TestData.Property_State + ",", TestData.Property_Zip};
        AgentDashboardPage.properties_innerItem_1.verifyContains(propertyRecord_1);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}