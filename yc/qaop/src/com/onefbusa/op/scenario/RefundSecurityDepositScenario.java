package com.onefbusa.op.scenario;

import frameLib.MyWebElement;
import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static com.onefbusa.op.scenario.RemoveAgencyScenario.RemoveAgencyByName;
import static com.onefbusa.op.scenario.RemovePropertyScenario.RemovePropertyByAgencyName;
import static frameLib.PrintTestCases.print;

public class RefundSecurityDepositScenario {
    // refund security deposit for a pending lease and then make the lease resolved
    public static void  RefundSecurityDeposit(String... inputs) throws Exception {
        // add a clean basic property combined with agency
        AgentDashboardPage.leases_tab.click();
        AgentYourLeasesPage.viewPendingLease_Btn.click();
        LeaseDetailPage.refundSecurityDeposit_btn.click();
        RefundSecurityDepositPopUpBox.RefundLease(inputs);
    }

    public static void main(String[] args) {
        try {
            openBrowser("firefox");
            navigateTo(General.SITE_URL);
            Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
            RefundSecurityDeposit(TestData.RefundLease_Inputs);
            closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
