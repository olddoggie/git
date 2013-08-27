package com.onefbusa.op.test;

import com.onefbusa.op.pages.AccountSignUpPage;
import com.onefbusa.op.pages.AgentAccountPendingPage;
import com.onefbusa.op.pages.HomePage;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.RemoveAgentScenario;
import frameLib.MyWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.verifyMultipleElementsContains;
import static com.onefbusa.op.pages.OPBasePage.verifyMultipleElementsPresent;

// Feature Name: [1.5] Managing Account SignUp
// Wireframe Page : [1.5] Managing Agent SignUp

public class ManagingAgentAccountSignUp {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        HomePage.requestAccount_link.click();
        AccountSignUpPage.load();
    }

    @Test (groups = { "BAT" }, description = ("test account sign up page"))
    public void CP_150_1_1() throws Exception {
        //pre steps have been done in BeforeMethod ..
        AccountSignUpPage.home_icon.verifyElementPresent();
        AccountSignUpPage.user_icon.verifyElementPresent();
        AccountSignUpPage.businessName_title.verifyContainsIgnoreSpaces("BUSINESS INFORMATION", "* Denotes a required field");
        AccountSignUpPage.employeeName_title.verifyContainsIgnoreSpaces("EMPLOYEE INFORMATION", "* Denotes a required field");
    }

    @Test (groups = { "BAT" }, description = ("test all the mandatory elements in account sign up page with a *"))
    public void CP_150_1_2() throws Exception {
        verifyMultipleElementsPresent(AccountSignUpPage.mandatoryElements);
    }

    @Test (groups = { "BAT" }, description = ("test all the options elements in account sign up page"))
    public void CP_150_1_3() throws Exception {
        verifyMultipleElementsPresent(AccountSignUpPage.optionalElements);
    }

    @Test (groups = { "BAT" }, description = ("test agent sign up"))
    public void CP_150_1_4() throws Exception {
        // initialize the test data before sign up pass..
        RemoveAgentScenario.RemoveAgent(TestData.AgentSignUp_Email);
        // quick signUp only needs agent to input all Mandatory fields, leave the optional part as blank ""
        AccountSignUpPage.AgentSignUp(TestData.AgentSignUpMandatoryInput);
        AgentAccountPendingPage.load().ready();
    }

    @Test (groups = { "CAT1" },description = ("test if no inputs in sign up page, the error messages display correct"))
    public void CP_150_1_5() throws Exception {
       // leave all the input box empty and check the error message
        AccountSignUpPage.continue_Btn.click();
        MyWebElement[] errorMsg_labels = {AccountSignUpPage.businessName_errorLabel, AccountSignUpPage.officePhone_errorLabel, AccountSignUpPage.address1_errorLabel,
                AccountSignUpPage.city_errorLabel, AccountSignUpPage.zip_errorLabel, AccountSignUpPage.firstName_errorLabel, AccountSignUpPage.lastName_errorLabel,
                AccountSignUpPage.email_errorLabel, AccountSignUpPage.mainPhone_errorLabel, AccountSignUpPage.password_errorLabel, AccountSignUpPage.confirmPassword_errorLabel};
        String[] errorMsg_allMandatoryMissing = {"BUSINESS NAME is required", "OFFICE PHONE is required", "ADDRESS1 is required",
                "CITY is required", "ZIP is required", "FIRST NAME is required", "LAST NAME is required", "EMAIL is required",
                "MAIN PHONE is required", "PASSWORD is required", "PASSWORD is required"} ;
        verifyMultipleElementsContains(errorMsg_labels,errorMsg_allMandatoryMissing);
    }

    // DataValidation will be mainly checked by manual testing in CAT1..
//    @Test (groups = { "BAT"})
//    public void CP_150_1_6() throws Exception {
//    	String[] invalidEmails = TestData.split(";");
//    	for(String single: invalidEmails){
//            AccountSignUpPage.email_textbox.clear();
//            AccountSignUpPage.email_textbox.input(single);
//    		JavascriptExecutor js = (JavascriptExecutor) AccountSignUpPage.driver;
////	        String script = "document.getElementById('email').blur()";
//    		String script = "$('#email').blur()";
//	        js.executeScript(script);
//
//            AccountSignUpPage.emailError.verifyDisplayed(true);
////        	ManagingAgentSignupPage.emailAddress.waitUntilElementPresent();
//    	}
//    }

    @AfterMethod (alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
