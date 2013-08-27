package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.Login_Succeed;
import static com.onefbusa.op.pages.OPBasePage.verifyMultipleElementsPresent;

public class DeleteFloors_PropertyDetailPage {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // pre step to create a clean property
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        PropertyDetailPage.load();
    }

    @Test(groups = { "BAT" }, description = ("test confirm box when agent's gonna Remove Floor in Property Detail page"))
    public void CP_222_10_1() throws Exception{
        PropertyDetailPage.removeFloorFromUnitBlocks_btn1.click();
        verifyMultipleElementsPresent(PropertyDetailPage.deleteConfirmElements);
        PropertyDetailPage.deleteConfirmPopUpBox_content.verifyContainsIgnoreSpaces("Are you sure you want to delete this floor?");
    }

    @Test(groups = { "BAT" }, dependsOnMethods = {"CP_222_10_1"},
            description = ("test Remove Floor in Property Detail page"))
    public void CP_222_10_2() throws Exception{
        PropertyDetailPage.removeFloorFromUnitBlocks_btn1.click();
        PropertyDetailPage.deleteConfirmPopUpBox_no.waitUntilElementPresent().click();
        Thread.sleep(500);   // wait for pop up box disappear
        PropertyDetailPage.floorNameInUnitBlocks_title1.verifyElementPresent();
        PropertyDetailPage.removeFloorFromUnitBlocks_btn1.click();
        PropertyDetailPage.deleteConfirmPopUpBox_yes.waitUntilElementPresent().click();
        PropertyDetailPage.floorNameInUnitBlocks_title1.waitUntilElementDisappearByTime(20).verifyElementNotPresent(); // wait at most 20s until system detect floor has been deleted
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}