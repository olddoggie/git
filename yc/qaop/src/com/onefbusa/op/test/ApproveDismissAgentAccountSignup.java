package com.onefbusa.op.test;

import static applications.BasePage.closeBrowser;
import static applications.BasePage.navigateTo;
import static applications.BasePage.openBrowser;
import static com.onefbusa.op.pages.OPBasePage.Login_Fail;
import static com.onefbusa.op.pages.OPBasePage.Login_Succeed;
import static com.onefbusa.op.pages.OPBasePage.verifyMultipleElementsPresent;

import com.onefbusa.op.scenario.RemoveAgentScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.onefbusa.op.pages.AccountSignUpPage;
import com.onefbusa.op.pages.AdminDashboardPage;
import com.onefbusa.op.pages.AgentAccountPendingPage;
import com.onefbusa.op.pages.AgentDashboardPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.pages.PropertyCreatePage;
import com.onefbusa.op.pages.PropertyDetailPage;
import com.onefbusa.op.pages.ShowAgentAccountSignUpPage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.util.Account;

public class ApproveDismissAgentAccountSignup {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void setupBeforeClass(String browser) throws Exception {
		openBrowser(browser);
		navigateTo(General.SITE_URL);
		HomePage.load();
        // delete the test data after sign up pass..
        RemoveAgentScenario.RemoveAgent(TestData.AgentSignUp_Email);
		HomePage.requestAccount_btn.click();
		AccountSignUpPage.load();
		AccountSignUpPage.AgentSignUp(TestData.AgentSignUpMandatoryInput);
		AgentAccountPendingPage.load().agentAccountPending_title
				.waitUntilElementPresent();
		Login_Succeed(TestData.Login_AdminEmail, TestData.Login_AdminPassword);
        AdminDashboardPage.load();
	}

	@Test(groups = { "BAT" }, description = ("OP Admin could see DISMISS button and Click it will reject the sign up"))
	public void CP_410_1_1() throws Exception {
		AdminDashboardPage.topPending_Agent_viewBtn.click();
		ShowAgentAccountSignUpPage.load().ready();
		ShowAgentAccountSignUpPage.pendingAgent_dismiss.click();
		AdminDashboardPage.load();
		String agentName = TestData.AgentSignUp_FirstName + " "
				+ TestData.AgentSignUp_LastName + "-"
				+ TestData.AgentSignUp_MainPhone;
		AdminDashboardPage.topPending_Agent
				.verifyNotContainsIgnoreSpaces(agentName);
		AdminDashboardPage.signOut_link.click();
		HomePage.load();
		Login_Fail(TestData.AgentSignUp_Email, TestData.AgentSignUp_Password);
		HomePage.loginFail_table.verifyElementPresent();
	}

	@Test(groups = { "BAT" }, description = ("OP Admin could see APPROVE button and Click it will accept the sign up"))
	public void CP_410_1_2() throws Exception {
		AdminDashboardPage.topPending_Agent_viewBtn.click();
		ShowAgentAccountSignUpPage.load().ready();
		ShowAgentAccountSignUpPage.pendingAgent_approve.click();
		AdminDashboardPage.load();
		String agentName = TestData.AgentSignUp_FirstName + " "
				+ TestData.AgentSignUp_LastName + "-"
				+ TestData.AgentSignUp_MainPhone;
		AdminDashboardPage.topPending_Agent
				.verifyNotContainsIgnoreSpaces(agentName);
		AdminDashboardPage.signOut_link.click();
		HomePage.load();
		Login_Succeed(TestData.AgentSignUp_Email, TestData.AgentSignUp_Password);
		AgentDashboardPage.load().ready(TestData.AgentSignUp_FirstName);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDownAfterClass() throws Exception {
		closeBrowser();
	}

}
