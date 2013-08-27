package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class ViewLeaseDetail {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
    }

    @Test(groups = { "BAT" }, description = ("test lease detail page for ACTIVE lease"))
    public void CP_211_1_1() throws Exception{
        // prepare test data
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        AddLeaseScenario.Add_EndingSoon_Lease(TestData.AgencyName, TestData.Property_Name, TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
        LeaseDetailPage.load().ready("ACTIVE");
    }

    @Test(groups = { "BAT" }, dependsOnMethods = {"CP_211_1_1"}, description = ("test Renew ACTIVE lease"))
    public void CP_211_1_5() throws Exception{
        // renew lease
        RenewLeaseScenario.Renew_Lease_without_input();
        LeaseDetailPage.load().ready("ACTIVE");
        LeaseDetailPage.renewTimes_subTitle.verifyContains("RENEW (1)");
        LeaseDetailPage.leaseHistory_tableTitle.verifyContains("LEASE HISTORY");
        LeaseDetailPage.renewDate1_displayValue.verifyContainsIgnoreSpaces(TestData.GetNowDate());
        LeaseDetailPage.renewType1_displayValue.verifyContains("LEASE RENEWED");
    }

    @Test(groups = { "BAT" }, dependsOnMethods = {"CP_211_1_5"}, description = ("test PENDING lease"))
    public void CP_211_1_2() throws Exception{
        // make the ACTIVE lease pending
        ConcludeLeaseScenario.Conclude_Lease(1);
        LeaseDetailPage.load().ready("PENDING");
    }

    @Test(groups = { "BAT" }, dependsOnMethods = {"CP_211_1_2"}, description = ("test RESOLVED lease"))
    public void CP_211_1_4() throws Exception{
        // refund the security deposit to resolve lease
        RefundSecurityDepositScenario.RefundSecurityDeposit(TestData.RefundLease_Inputs);
        LeaseDetailPage.load().ready("RESOLVED");
        // after cases done, renew a clean property and a clean lease
        LeaseDetailPage.dashboard_tab.click();
        AgentDashboardPage.load();
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        AddLeaseScenario.Add_EndingSoon_Lease(TestData.AgencyName,  TestData.Property_Name, TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
    }

    // Disputed is NOT in CAT1 Scope
    @Test(enabled = false)
    public void CP_211_1_3() throws Exception{
        AgentDashboardPage.leases_tab.click();
        AgentYourLeasesPage.load();
        AgentYourLeasesPage.viewDisputedLease_Btn.click();
        LeaseDetailPage.load().ready("DISPUTED");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}