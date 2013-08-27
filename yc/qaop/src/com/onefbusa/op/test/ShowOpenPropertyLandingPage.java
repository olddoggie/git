package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.*;
import org.testng.annotations.*;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class ShowOpenPropertyLandingPage {
    // make a clean Testing env every time script running ..
    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeSuit(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        AccountSignUpScenario.AgentAccountSignUp(TestData.Login_AgentEmail,TestData.AgencyName,TestData.Login_AdminEmail,TestData.Login_AdminPassword,
                TestData.LoginAgentMandatoryInput);
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        AddLeaseScenario.Add_EndingSoon_Lease(TestData.AgencyName, TestData.Property_Name, TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
        closeBrowser();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
    }

    @Test(groups = { "BAT" }, description = ("test landing page") )
    public void CP_100_1_1() throws Exception{
        HomePage.load().ready();  // 1. load method is used to load page; 2. ready method is to verify: pageTitle and TitleElement available
        String [] contents = {HomePage.welcome_title.getText() ,
                "Don't have an account? Request Account now!",
                "Are you a tenant? See if you have a pending account here",
                "Are you a tenant of a building? Click here to see if you have an account pending via your Landlord.",
                HomePage.propertySearch_title.getText(),HomePage.tenantSearch_title.getText() } ;
        verifyPageContains(contents);
          // not good to use verifyAll method here, because some elements' displaying need be triggered
//        verifyAllElementsPresent(new HomePage());
          // use another method: verifyOriginalElementsPresent to check all displayed elements when page initialized
        verifyMultipleElementsPresent(HomePage.originalDisplayedElements);
    }

    @Test(groups = { "CAT1" }, description = ("test navigation bar"))
    public void CP_100_1_2() throws Exception{
        HomePage.load();
        HomePage.about_tab.click();
        AboutUsPage.load().ready();
        AboutUsPage.contact_tab.click();
        ContactUsPage.load().ready();
        ContactUsPage.faq_tab.click();
        FAQPage.load().ready();
        // Search Page in CAT1 not implemented..
//        HomePage.search_tab.click();
//        SearchPage.load().ready();
    }

    @Test(groups = { "CAT1" },description = ("test foot link") )
    public void CP_100_1_3() throws Exception{
        HomePage.load();
        HomePage.aboutUs_footlink.click();
        AboutUsPage.load().ready();
        AboutUsPage.contactUs_footlink.click();
        ContactUsPage.load().ready();
        ContactUsPage.faq_footlink.click();
        FAQPage.load().ready();
        FAQPage.signUp_footlink.click();
        AccountSignUpPage.load().ready();
        verifyPageContains("Please fill out this form.");
        AccountSignUpPage.privacy_footlink.click();
        PrivacyPolicyPage.load().ready();
        PrivacyPolicyPage.terms_footlink.click();
        TermsConditionsPage.load().ready();
        TermsConditionsPage.fcra__footlink.click();
        FCRAPage.load().ready();
    }

    @Test(groups = { "CAT1" },description = ("test forget password link")  )
    public void CP_100_1_4() throws Exception{
        HomePage.load();
        HomePage.forgetPassword_link.click();
        ForgetPasswordPage.load().ready();
        verifyPageContains("Please enter your email address and follow the reset password instructions once you receive our email.");
    }

    @Test(groups = { "CAT1" },description = ("test request account link"))
    public void CP_100_1_5() throws Exception{
        HomePage.load();
        HomePage.requestAccount_link.click();
        AccountSignUpPage.load().ready();
    }

    @Test(groups = { "CAT1" },description = ("test pending account link"))
    public void CP_100_1_6() throws Exception{
        HomePage.load();
        HomePage.pendingAccount_link.click();
        TenantVerificationPage.load().ready();
        String [] contents = {"At the present time you must be invited by a participating landlord. In order to enroll, follow the signup link through the email from OpenProperty.",
                "If you haven't received an email, enter your email address to see if there is an enrollment pending."} ;
        verifyPageContains(contents);
    }

    @Test(groups = { "CAT1" },description = ("test request account link"))
    public void CP_100_1_7() throws Exception{
        HomePage.load();
        HomePage.requestAccount_btn.click();
        AccountSignUpPage.load().ready();
    }

    @Test(groups = { "CAT1" },description = ("test pending account link"))
    public void CP_100_1_8() throws Exception{
        HomePage.load();
        HomePage.clickHere_pendingAccount_link.click();
        TenantVerificationPage.load().ready();
    }

    // Property Search deScoped into CAT2 ..
//    @Test(groups = { "BAT" })
//    public void CP_100_1_9() throws Exception{
//        HomePage.load();
//        HomePage.propertySearch_btn.click();
//        SearchPage.load().ready();
//    }

    // Tenant Search deScoped into CAT2 ..
//    @Test(groups = { "BAT" })
//    public void CP_100_1_10() throws Exception{
//        HomePage.load();
//        HomePage.tenantSearch_btn.click();
//        SearchPage.load().ready();
//    }

    @Test(enabled = false)
    // not applicable as OP page is not secured anymore
    public void CP_100_1_11() throws Exception{
        HomePage.load();
        navigateTo(General.SITE_wrongURL);
        verifyHttpProtocol(HomePage.pageTitle);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
