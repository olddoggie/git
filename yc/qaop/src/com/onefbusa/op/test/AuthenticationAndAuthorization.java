package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class AuthenticationAndAuthorization {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
    }

    @Test(groups = { "CAT1" },description = ("test page access, user without log in, can access public page normally"))
    public void CP_501_1() throws Exception{
        String [] contents = {HomePage.welcome_title.getText() ,
                "Don't have an account? Request Account now!",
                "Are you a tenant? See if you have a pending account here",
                "Are you a tenant of a building? Click here to see if you have an account pending via your Landlord.",
                HomePage.propertySearch_title.getText(),HomePage.tenantSearch_title.getText() } ;
        verifyPageContains(contents);
        verifyMultipleElementsPresent(HomePage.originalDisplayedElements);
    }

    @Test(groups = { "CAT1" },description = ("test page access, user without log in, can not access roles dashboard"))
    public void CP_501_2() throws Exception{
        navigateTo(General.AgentDashboard_URL);
        LoginPage.load().ready();
        navigateTo(General.TenantDashboard_URL);
        LoginPage.load().ready();
        navigateTo(General.AdminDashboard_URL);
        LoginPage.load().ready();
    }

    @Test(groups = { "CAT1" },description = ("test page access,once log in as agent, can not access other roles"))
    public void CP_501_3() throws Exception{
        Login_Succeed(TestData.Login_AgentEmail,TestData.Login_AgentPassword);
        navigateTo(General.AdminDashboard_URL);
        Error403Page.load().ready();
        navigateTo(General.TenantDashboard_URL);
        Error403Page.load().ready();
    }

    // Tenant Page not implemented yet..
    @Test(enabled = false)
    public void CP_501_4() throws Exception{
        Login_Succeed(TestData.Login_TenantEmail,TestData.Login_TenantPassword);
        navigateTo(General.AgentDashboard_URL);
        Error403Page.load().ready();
        navigateTo(General.AdminDashboard_URL);
        Error403Page.load().ready();
    }

    @Test(groups = { "CAT1" },description = ("test page access,once log in as admin, never access to other roles"))
    public void CP_501_5() throws Exception{
        Login_Succeed(TestData.Login_AdminEmail,TestData.Login_AdminPassword);
        navigateTo(General.AgentDashboard_URL);
        Error403Page.load().ready();
        navigateTo(General.TenantDashboard_URL);
        Error403Page.load().ready();
    }

    @Test(groups = { "CAT1" },description = ("test page access,public page should be loaded with logged in status after user has logged into system"))
    public void CP_501_6() throws Exception{
        Login_Succeed(TestData.Login_AgentEmail,TestData.Login_AgentPassword);
        navigateTo(General.SITE_URL);
        HomePage.requestAccount_btn.click();
        AccountSignUpPage.load().signOut_link.verifyElementPresent();
        navigateTo(General.SITE_URL);
        HomePage.clickHere_pendingAccount_link.click();
        TenantVerificationPage.load().signOut_link.verifyElementPresent();
        navigateTo(General.ForgotPasswordPage_URL);
        ForgetPasswordPage.load().signOut_link.verifyElementPresent();
    }

    @Test(groups = { "CAT1" },description = ("test page access,public page should be loaded with log in status"))
    public void CP_501_7() throws Exception{
        Login_Succeed(TestData.Login_AgentEmail,TestData.Login_AgentPassword);
        navigateTo(General.AboutUSPage_URL);
        AboutUsPage.load().signOut_link.verifyElementPresent();
        AboutUsPage.contactUs_footlink.click();
        ContactUsPage.load().signOut_link.verifyElementPresent();
        ContactUsPage.faq_footlink.click();
        FAQPage.load().signOut_link.verifyElementPresent();
        FAQPage.signUp_footlink.click();
        AccountSignUpPage.load().signOut_link.verifyElementPresent();
        AccountSignUpPage.privacy_footlink.click();
        PrivacyPolicyPage.load().signOut_link.verifyElementPresent();
        PrivacyPolicyPage.terms_footlink.click();
        TermsConditionsPage.load().signOut_link.verifyElementPresent();
        TermsConditionsPage.fcra__footlink.click();
        FCRAPage.load().signOut_link.verifyElementPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
