package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.AddLeaseScenario;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import frameLib.MyWebElement;
import org.openqa.selenium.Alert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static frameLib.PrintTestCases.print;

public class ViewTenantInfo {

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
        LeaseDetailPage.load();
    }

    @Test(groups = { "BAT" }, description = ("test Tenant info table in Lease Detail page"))
    public void CP_211_4_1() throws Exception{
        verifyMultipleElementsPresent(LeaseDetailPage.tenantInfoTableElements);
        verifyMultipleElementsContainsIgnoreSpace(LeaseDetailPage.tenantInfoTable_displayValues,TestData.SaveLease_TenantDisplayValue);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}