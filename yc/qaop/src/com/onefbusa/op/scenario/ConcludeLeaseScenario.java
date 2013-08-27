package com.onefbusa.op.scenario;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static com.onefbusa.op.scenario.RemoveLeaseScenario.RemoveLeaseByAgencyName;

public class ConcludeLeaseScenario {
    // go to lease detail page and then update term and conclude it
    public static void  Conclude_Lease(int num) throws Exception {
        AgentDashboardPage.leases_tab.click();
        // decide which lease you wanna conclude,number 1(first added lease) or number 2 (the another added lease )
        // this part need be enhance in future CAT, should be click the View button by  Property Name, Tenant Name,etc.
        if( num == 1) {
            AgentYourLeasesPage.leaseTable_viewBtn1.click();
        } else {
            AgentYourLeasesPage.leaseTable_viewBtn2.click();
        }
        LeaseDetailPage.updateTerm_btn.click();
        UpdateLeaseTermPage.concludeLease_btn.click();
    }

    public static void main(String[] args) {
        try {
            openBrowser("firefox");
            navigateTo(General.SITE_URL);
            Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
            Conclude_Lease(2);
            closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
