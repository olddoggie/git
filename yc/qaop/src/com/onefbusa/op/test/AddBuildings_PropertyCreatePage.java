package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import com.onefbusa.op.scenario.RemovePropertyScenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class AddBuildings_PropertyCreatePage {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        // pre step to remove any existed property in order to prevent any conflicts
        RemovePropertyScenario.RemovePropertyByAgencyName(TestData.AgencyName);
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // create property and add building
        AgentDashboardPage.addNewProperty_btn.click();
        PropertyCreatePage.Input_Basic_PropertyInfo(TestData.Create_Basic_PropertyInfo);
        PropertyCreatePage.addBuildings_iconBtn.click();
    }

    @Test(groups = { "BAT" }, description = ("test elements on Property Create page after add a building and arrow-down displayed"))
    public void CP_221_2_1() throws Exception{
        PropertyCreatePage.addBuildings_title.verifyContainsIgnoreSpaces("Add Buildings","* Denotes a required field");
        PropertyCreatePage.buildingName_label.verifyContainsIgnoreSpaces("BUILDING NAME: *");
        PropertyCreatePage.buildingType_label.verifyContainsIgnoreSpaces("BUILDING TYPE: *");
        verifyMultipleElementsPresent(PropertyCreatePage.addBuildingsTableElements);
        PropertyCreatePage.buildingName_textBox.input(TestData.Property_Building1_Name);
        PropertyCreatePage.buildingType_selectBox.verifyFirstSelectedOption("Select...");
        PropertyCreatePage.buildingType_selectBox.selectByVisibleText(TestData.Property_Building1_Type);
        PropertyCreatePage.saveBuilding_iconBtn.click();
        PropertyCreatePage.editBuilding_iconBtn.verifyElementPresent();
        PropertyCreatePage.addFloorsUnitsForBuildings_Btn.verifyElementPresent();
        PropertyCreatePage.arrowDownForBuildings_Icon.verifyElementPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}