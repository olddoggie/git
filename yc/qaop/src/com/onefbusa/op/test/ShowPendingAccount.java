package com.onefbusa.op.test;

import com.onefbusa.op.pages.AccountSignUpPage;
import com.onefbusa.op.pages.AgentAccountPendingPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.RemoveAgentScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;

public class ShowPendingAccount {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
    }

    @Test(groups = { "BAT" }, description = ("test after agent sign up successfully, page goes to account pending page"))
    public void CP_151_1_1() throws Exception{
        // delete the test data after sign up pass..
        RemoveAgentScenario.RemoveAgent(TestData.AgentSignUp_Email);
        HomePage.requestAccount_link.click();
        AccountSignUpPage.load();
        AccountSignUpPage.AgentSignUp(TestData.AgentSignUpMandatoryInput);
        AgentAccountPendingPage.load().ready();
        verifyPageContains("Pending Activation, we will be in touch within 48hrs.As well, feel free to reach out to us at 1-800-500-2929.");
    }

    @Test(groups = { "CAT1" },description = ("user could have no access to account pending page without signed up successfully first"))
    public void CP_151_1_2() throws Exception{
        navigateTo(General.AgentSignUpSucceed_URL);
        AccountSignUpPage.load().ready();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
