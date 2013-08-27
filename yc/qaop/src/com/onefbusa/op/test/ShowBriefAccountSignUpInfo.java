package com.onefbusa.op.test;

import static applications.BasePage.closeBrowser;
import static applications.BasePage.navigateTo;
import static applications.BasePage.openBrowser;
import static com.onefbusa.op.pages.OPBasePage.Login_Fail;
import static com.onefbusa.op.pages.OPBasePage.Login_Succeed;

import com.onefbusa.op.scenario.RemoveAgentScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.onefbusa.op.pages.AccountSignUpPage;
import com.onefbusa.op.pages.AdminDashboardPage;
import com.onefbusa.op.pages.AgentAccountPendingPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.util.Account;

public class ShowBriefAccountSignUpInfo {
	
	@BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
    }

    @Test(groups = { "BAT" }, description = ("test Show Brief Account SignUp Info table on admin dashboard page"))
    public void CP_400_2_1() throws Exception{
        // delete the test data after sign up pass..
        RemoveAgentScenario.RemoveAgent(TestData.AgentSignUp_Email);
        HomePage.requestAccount_btn.click();
        AccountSignUpPage.load();
        AccountSignUpPage.AgentSignUp(TestData.AgentSignUpMandatoryInput);
        AgentAccountPendingPage.load().agentAccountPending_title.waitUntilElementPresent();
        // after log in, OP Admin could see Pending SignUP records
        // admin approve the newly signed up agent
        Login_Succeed(TestData.Login_AdminEmail,TestData.Login_AdminPassword);
        AdminDashboardPage.load();
        String agentName=TestData.AgentSignUp_FirstName +" "+ TestData.AgentSignUp_LastName + "- " + TestData.AgentSignUp_MainPhone;
        String agentAddress=TestData.AgentSignUp_City+","+ TestData.AgentSignUp_State_Short;
        AdminDashboardPage.topPending_Agent.verifyContainsIgnoreSpaces(agentName,agentAddress);
        //System.out.println(AdminDashboardPage.topPending_Agent.getText().trim());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
