package com.onefbusa.op.test;

import static applications.BasePage.closeBrowser;
import static applications.BasePage.navigateTo;
import static applications.BasePage.openBrowser;
import static com.onefbusa.op.pages.OPBasePage.*;

import com.onefbusa.op.scenario.RemoveAgentScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.onefbusa.op.pages.AccountSignUpPage;
import com.onefbusa.op.pages.AdminDashboardPage;
import com.onefbusa.op.pages.AgentAccountPendingPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.pages.LeaseDetailPage;
import com.onefbusa.op.pages.ShowAgentAccountSignUpPage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.util.Account;

public class ShowDetailAccountSignUpInformation {
	
	@BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        // delete the test data after sign up pass..
        RemoveAgentScenario.RemoveAgent(TestData.AgentSignUp_Email);
    	HomePage.requestAccount_btn.click();
    	AccountSignUpPage.load();
        AccountSignUpPage.AgentSignUp(TestData.AgentSignUpMandatoryInput);
        AgentAccountPendingPage.load().agentAccountPending_title.waitUntilElementPresent();
    }

    @Test(groups = { "BAT" }, description = ("test View detail Account SignUp Info after clicking view button on admin dashboard page"))
    //OP Admin could see following sections in ACCOUNT SignUp (tittle) Page:
    public void CP_410_2_1() throws Exception{
    	 Login_Succeed(TestData.Login_AdminEmail,TestData.Login_AdminPassword);
         AdminDashboardPage.load();
         AdminDashboardPage.topPending_Agent_viewBtn.click();
         ShowAgentAccountSignUpPage.load();
         verifyMultipleElementsPresent(ShowAgentAccountSignUpPage.originalDisplayedElements);
         String[] businessInformation_value = {TestData.AgentSignUp_BusinessName,TestData.AgentSignUp_OfficePhone,TestData.AgentSignUp_Address1,TestData.AgentSignUp_City,TestData.AgentSignUp_State,TestData.AgentSignUp_Zip5};
         String[] employeeInformation_value = {TestData.AgentSignUp_FirstName,TestData.AgentSignUp_LastName,TestData.AgentSignUp_Email,TestData.AgentSignUp_MainPhone,};
         verifyMultipleElementsContainsIgnoreSpace(ShowAgentAccountSignUpPage.businessInformation_value,businessInformation_value);
         verifyMultipleElementsContainsIgnoreSpace(ShowAgentAccountSignUpPage.employeeInformation_value,employeeInformation_value);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
