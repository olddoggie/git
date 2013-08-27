package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.AddLeaseScenario;
import com.onefbusa.op.scenario.ConcludeLeaseScenario;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static com.onefbusa.op.pages.OPBasePage.verifyAllElementsPresent;

public class RefundSecurityDepositInfo {

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
        LeaseDetailPage.dashboard_tab.click();
        AgentDashboardPage.load();
        // make lease pending, attention to the parameter 2 'cause this lease in TestData will be listed the second in Your Leases Page
        ConcludeLeaseScenario.Conclude_Lease(1);
        // after run pre scenario, page directly goes to Lease Detail Page
        LeaseDetailPage.load();
        LeaseDetailPage.refundSecurityDeposit_btn.click();
        Thread.sleep(500);  // wait for the pop up window display
    }

    @Test(groups = { "BAT" }, description = ("test elements in RefundSecurityDeposit pop up box"))
    public void CP_211c_1_1() throws Exception{
        RefundSecurityDepositPopUpBox.load().ready();
        verifyAllElementsPresent(new RefundSecurityDepositPopUpBox());
        RefundSecurityDepositPopUpBox.securityRefundComment_selectBox.verifyFirstSelectedOption("Select...");
        RefundSecurityDepositPopUpBox.refundReason_row1.verifyFirstSelectedOption("Select...");
        RefundSecurityDepositPopUpBox.refundReason_row2.verifyFirstSelectedOption("Select...");
        RefundSecurityDepositPopUpBox.refundReason_row3.verifyFirstSelectedOption("Select...");
        RefundSecurityDepositPopUpBox.refundReason_row1.verifyAllOptions(TestData.visibleOptionsInSelectBox);
        RefundSecurityDepositPopUpBox.refundReason_row2.verifyAllOptions(TestData.visibleOptionsInSelectBox);
        RefundSecurityDepositPopUpBox.refundReason_row3.verifyAllOptions(TestData.visibleOptionsInSelectBox);
    }

    @Test(groups = { "BAT" }, description = ("Refund a lease by agent in pop up box"))
    public void CP_211c_1_11() throws Exception{
        RefundSecurityDepositPopUpBox.load();
        RefundSecurityDepositPopUpBox.RefundLease(TestData.RefundLease_Inputs);
        LeaseDetailPage.load().ready("RESOLVED");
    }

    @Parameters({"browser"})
    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass(String browser) throws Exception{
        closeBrowser();
    }

}