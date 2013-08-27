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
import static com.onefbusa.op.pages.OPBasePage.*;

public class AddFloorsUnitsForBuildings_PropertyDetailPage {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // pre step to create a clean property
        CreatePropertyScenario.Create_Basic_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo);
        PropertyDetailPage.load();
        // add building in detail page
        PropertyDetailPage.addBuildings_iconBtn.click();
        PropertyDetailPage.buildingName_textBox.input(TestData.Property_Building1_Name);
        PropertyDetailPage.buildingType_selectBox.selectByVisibleText(TestData.Property_Building1_Type);
        PropertyDetailPage.saveBuilding_iconBtn.click();
        Thread.sleep(500);
        // add floor and unit in detail page
        PropertyDetailPage.selectFloor_selectBox1.selectByVisibleText(TestData.Property_Floor);
        PropertyDetailPage.inputUnits_textBox1.input(TestData.Property_Units);
        PropertyDetailPage.addUnit_btn1.click();
        Thread.sleep(500); //wait till floor unit added into Blocks
    }

    @Test(groups = { "BAT" }, description = ("test elements on Property Detail page after building,floor,unit added and UNOCCUPIED in List View"))
    public void CP_222_8_10() throws Exception{
        verifyMultipleElementsPresent(PropertyDetailPage.addUnitsFloorsForBuildingsTable1_Elements);
        PropertyDetailPage.selectFloor_selectBox1.verifyFirstSelectedOption("Select...");
        PropertyDetailPage.selectFloor_selectBox1.verifySelectDropDownListSize(101);
        PropertyDetailPage.buildingNameInUnitBlocks_title1.verifyEquals(TestData.Property_Building1_Name + " - " + TestData.Property_Building1_Type);
        PropertyDetailPage.floorNameInUnitBlocks_title1.verifyEquals("FLOOR " +TestData.Property_Floor);
        PropertyDetailPage.unitNameInUnitBlocks_list1.verifyContains("Unit " +TestData.Property_Units_Display,"UNOCCUPIED");
        verifyStatusColor(PropertyDetailPage.unitNameInUnitBlocks_list1,"UNOCCUPIED");
        PropertyDetailPage.addLease_btn1.verifyElementPresent();
        PropertyDetailPage.removeUnitFromUnitBlocks_btn1.verifyElementPresent();
    }

    @Test(groups = { "BAT" }, dependsOnMethods = {"CP_222_8_10"},
            description = ("test elements on Property Detail page after building,floor,unit added and OCCUPIED"))
    public void CP_222_8_1() throws Exception{
        // case CP_222_8_1 need to verify an occupied unit, so the precondition should be add a lease first
        // add a lease to occupy the unit
        PropertyDetailPage.addLease_btn1.click();
        Thread.sleep(500); //wait till floor unit added into Blocks
        TestData.AddLease_LeaseInfo[1] = "2014-09-01";
        NewLease_LeaseInfoPage.AddNewLease_LeaseInfo(TestData.AddLease_LeaseInfo);
        NewLease_TenantInfoPage.AddNewLease_TenantInfo(TestData.AddLease_TenantInfo);
        NewLease_SaveLeasePage.saveViewLease_btn.click();
        LeaseDetailPage.load();
        LeaseDetailPage.properties_tab.click();
        AgentYourPropertiesPage.load();
        AgentYourPropertiesPage.propertiesTable_viewBtn1.click();
        PropertyDetailPage.load();
        verifyMultipleElementsPresent(PropertyDetailPage.addUnitsFloorsForBuildingsTable1_Elements_afterUnitOccupied);
        PropertyDetailPage.selectFloor_selectBox1.verifyFirstSelectedOption("Select...");
        PropertyDetailPage.selectFloor_selectBox1.verifySelectDropDownListSize(101);
        PropertyDetailPage.buildingNameInUnitBlocks_title1.verifyEquals(TestData.Property_Building1_Name + " - " + TestData.Property_Building1_Type);
        PropertyDetailPage.floorNameInUnitBlocks_title1.verifyEquals("FLOOR " + TestData.Property_Floor);
        TestData.UnitListView_PropertyDetailPage[5] =TestData.AddLease_LeaseInfo[1];
        PropertyDetailPage.unitNameInUnitBlocks_list1.verifyContainsIgnoreSpaces(TestData.UnitListView_PropertyDetailPage);
        verifyStatusColor(PropertyDetailPage.unitNameInUnitBlocks_list1,"OCCUPIED");
        PropertyDetailPage.addLease_btn1.verifyElementNotPresent();
        PropertyDetailPage.viewLease_btn1.verifyElementPresent();
        PropertyDetailPage.removeBuildingFromUnitBlocks_btn1.verifyEquals("true","disabled");
        PropertyDetailPage.removeFloorFromUnitBlocks_btn1.verifyEquals("true","disabled");
        PropertyDetailPage.removeUnitFromUnitBlocks_btn1.verifyEquals("true","disabled");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}