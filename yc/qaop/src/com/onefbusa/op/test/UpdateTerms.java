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
import static com.onefbusa.op.pages.OPBasePage.verifyAllElementsPresent;

public class UpdateTerms {

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
        LeaseDetailPage.updateTerm_btn.click();
    }

    @Test(groups = { "BAT" }, description = ("test after agent clicking Update Term button, system display pop up box correctly"))
    public void CP_212_4_1() throws Exception{
        UpdateLeaseTermPage.load().ready(TestData.LeaseName_UpdateTermPage);
        UpdateLeaseTermPage.extendLeaseTerm_tableTitle.verifyContainsIgnoreSpaces("EXTEND LEASE TERM", "* Denotes a required field");
        verifyMultipleElementsPresent(UpdateLeaseTermPage.extendLeaseTerm_tableElements);
        UpdateLeaseTermPage.termStartDate_label.verifyContains(TestData.Lease_Start_Date);
        UpdateLeaseTermPage.termEndDate_label.verifyContains(TestData.Lease_End_Date);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}