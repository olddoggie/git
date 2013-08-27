package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class AddFloorsUnitsForBuildings_PropertyCreatePage {

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
        PropertyCreatePage.load();
        PropertyCreatePage.Input_Basic_PropertyInfo(TestData.Create_Basic_PropertyInfo);
        PropertyCreatePage.addBuildings_iconBtn.click();
        PropertyCreatePage.buildingName_textBox.input(TestData.Property_Building1_Name);
        PropertyCreatePage.buildingType_selectBox.selectByVisibleText(TestData.Property_Building1_Type);
        PropertyCreatePage.saveBuilding_iconBtn.click();
        Thread.sleep(500);  // wait for the add floor unit tool bar display and then click..
        PropertyCreatePage.addFloorsUnitsForBuildings_Btn.click();
    }

    @Test(groups = { "BAT" }, description = ("test elements on Property Create page after add building, floor and unit"))
    public void CP_221_3_1() throws Exception{
        verifyMultipleElementsPresent(PropertyCreatePage.addUnitsFloorsForBuildingsTableElements_beforeAddFloorUnits);
        PropertyCreatePage.selectBuilding_selectBox.verifySelectDropDownListSize(2);
        PropertyCreatePage.selectBuilding_selectBox.verifyFirstSelectedOption(TestData.Property_Building1_Name);
        String[] buildings = {TestData.Property_Building1_Name,"ALL BUILDINGS"};
        PropertyCreatePage.selectBuilding_selectBox.verifyAllOptions(buildings);
        PropertyCreatePage.selectFloor_selectBox.verifyFirstSelectedOption("Select...");
        PropertyCreatePage.selectFloor_selectBox.verifySelectDropDownListSize(101);
        PropertyCreatePage.buildingNameInUnitBlocks_title1.verifyContainsIgnoreSpaces(TestData.Property_Building1_Name + " - " + TestData.Property_Building1_Type);
    }

    @Test(groups = { "BAT" },
            description = ("test Show Your Properties page after agent create property in Property Create page with building, floor and unit added"))
    public void CP_221_3_10() throws Exception{
        // PropertyCreatePage.selectBuilding_selectBox.selectByVisibleText(TestData.Property_Building1_Name);  selected by default
        PropertyCreatePage.selectFloor_selectBox.selectByVisibleText(TestData.Property_Floor);
        PropertyCreatePage.inputUnits_textBox.input(TestData.Property_Units);
        PropertyCreatePage.addUnit_btn.click();
        PropertyCreatePage.floorNameInUnitBlocks_title1.verifyEquals("FLOOR " + TestData.Property_Floor);
        PropertyCreatePage.unitNameInUnitBlocks_alertBox1.verifyContains(TestData.Property_Units, "UNOCCUPIED");
        verifyStatusColor(PropertyCreatePage.unitNameInUnitBlocks_alertBox1, "UNOCCUPIED");
        PropertyCreatePage.removeFloorFromUnitBlocks_btn1.verifyElementPresent();
        PropertyCreatePage.removeUnitFromUnitBlocks_btn1.verifyElementPresent();
        PropertyCreatePage.saveProperties_btn.click();
        PropertyCreatePage.properties_tab.click();
        String [] display_items1 = {TestData.Property_Name + " - " + TestData.Property_Address1 + ", " + TestData.Property_City + ", " + TestData.Property_State
                + " " + TestData.Property_Zip, "1 FLOORS / 1 UNITS", "0 PENDING"};
        verifyMultipleElementsContainsIgnoreSpace(AgentYourPropertiesPage.propertiesTable_items_row1,display_items1);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}