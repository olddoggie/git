package com.onefbusa.op.scenario;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static com.onefbusa.op.scenario.RemoveLeaseScenario.RemoveLeaseByAgencyName;

public class RenewLeaseScenario {
    // go to lease detail page and then update term and conclude it
    public static void  Renew_Lease_without_input() throws Exception {
        AgentDashboardPage.leases_tab.click();
        AgentYourLeasesPage.leaseTable_viewBtn1.click();
        LeaseDetailPage.updateTerm_btn.click();
        UpdateLeaseTermPage.renewLease_btn.click();
    }

    public static void main(String[] args) {
        try {
            openBrowser("firefox");
            navigateTo(General.SITE_URL);
            Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
            Renew_Lease_without_input();
            closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
