package com.onefbusa.op.scenario;

import com.onefbusa.op.util.Account;
import frameLib.MyWebElement;
import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static com.onefbusa.op.scenario.RemoveAgencyScenario.RemoveAgencyByName;
import static com.onefbusa.op.scenario.RemoveAgentScenario.RemoveAgent;
import static frameLib.PrintTestCases.print;

public class AccountSignUpScenario {
    // remove and make a clean agent sign up, then admin will approve this new account
    // the reason why we need remove the test agency also, is that current in CAT1, if we register agent with totally same agency name,
    // then the system still recognize it as 2 different business name, and we yet do not have op admin to correct the info in CAT1..
    public static void  AgentAccountSignUp(String agent_name,String agency_name, String admin_name, String admin_passwd, String...input) throws Exception {
        // remove agent
        RemoveAgent(agent_name);
        // remove agency
        RemoveAgencyByName(agency_name);
        // add clean agent, combined with a clean agency
        HomePage.requestAccount_link.click();
        AccountSignUpPage.AgentSignUp(input);
        Thread.sleep(500);
        // after sign up, op admin go and approve
        Login_Succeed(admin_name,admin_passwd);
        AdminDashboardPage.load().topPending_Agent_viewBtn.click();
        ShowAgentAccountSignUpPage.pendingAgent_approve.click();
        AdminDashboardPage.load().signOut_link.click();
    }

    public static void main(String[] args) {
        try {
            openBrowser("firefox");
            navigateTo(General.SITE_URL);
            AgentAccountSignUp(TestData.Login_AgentEmail,TestData.AgencyName,TestData.Login_AdminEmail,TestData.Login_AdminPassword,
                    TestData.LoginAgentMandatoryInput);
            closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
