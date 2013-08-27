package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import frameLib.MyWebElement;
import org.openqa.selenium.Alert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static frameLib.PrintTestCases.print;

public class AddLease {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // make a clean property for lease adding
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName,TestData.Create_Basic_PropertyInfo,TestData.AddBuildingFloorUnit);
        PropertyDetailPage.load();
        PropertyDetailPage.addLease_btn1.click();
    }

    @Test(groups = { "BAT" }, description = ("test page title, table title, save button on add lease step1 -- Lease Info page"))
    public void CP_222_16_1 () throws Exception{
        NewLease_LeaseInfoPage.load().ready(TestData.leaseName);
        NewLease_LeaseInfoPage.leaseInfo_tableTitle.verifyContains("LEASE INFO","* Denotes a required field");
        NewLease_LeaseInfoPage.saveContinue_btn.verifyElementPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}