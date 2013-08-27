package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.onefbusa.op.scenario.*;
import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class CreateProperty {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        // remove the properties existed in system and add a new one from this case
        RemovePropertyScenario.RemovePropertyByAgencyName(TestData.AgencyName);
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        AgentDashboardPage.addNewProperty_btn.click();
    }

    @Test(groups = { "BAT" }, description = ("test elements in Property Create page"))
    public void CP_221_1_1() throws Exception{
        PropertyCreatePage.load().ready();
        PropertyCreatePage.propertyInfo_title.verifyContainsIgnoreSpaces("PROPERTY INFO","* Denotes a required field");
        PropertyCreatePage.depositInfo_title.verifyContainsIgnoreSpaces("DEPOSIT INFO");
        PropertyCreatePage.propertyState_selectBox.verifyFirstSelectedOption("Select...");
        verifyMultipleElementsPresent(PropertyCreatePage.originalDisplayedElements);
    }

    @Test(groups = { "BAT" }, description = ("Create a basic Property without buildings added"))
    public void CP_221_1_3() throws Exception{
        PropertyCreatePage.load();
        PropertyCreatePage.Create_Basic_Property(TestData.Create_Basic_PropertyInfo);   // input basic info and then click save
        PropertyDetailPage.load().ready(TestData.PropertyName);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}