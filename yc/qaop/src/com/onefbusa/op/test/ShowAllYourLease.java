package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
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

public class ShowAllYourLease {

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
        LeaseDetailPage.load().leases_tab.click();
    }

    @Test(groups = { "BAT" }, description = ("test Show All Your Leases page"))
    public void CP_210_1_1() throws Exception{
        AgentYourLeasesPage.load().ready();
        String[] column_titles = {"PROPERTY", "TENANT", "TERM", "RENT", "SECURITY", "STATUS"};
        verifyMultipleElementsEquals(AgentYourLeasesPage.leaseTable_titles,column_titles);
        verifyMultipleElementsContainsIgnoreSpace(AgentYourLeasesPage.leaseTable_items_row1,TestData.LeaseInfoTable_in_your_leases_active) ;
        verifyStatusColor(AgentYourLeasesPage.leaseTable_status1, "ACTIVE_YourLease");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}