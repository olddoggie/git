package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.CreatePropertyScenario;
import com.onefbusa.op.util.Account;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;

public class ShowPropertyDetail {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // pre step to create a clean property
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
    }

    @Test(groups = { "BAT" }, description = ("view Properties Detail page by click view button in Show Your Properties page"))
    public void CP_222_1_1() throws Exception{
        PropertyDetailPage.load().ready(TestData.PropertyName);
        PropertyDetailPage.propertyInfo_title.verifyContainsIgnoreSpaces("PROPERTY INFO");
        PropertyDetailPage.depositInfo_title.verifyContainsIgnoreSpaces("DEPOSIT INFO");
        PropertyDetailPage.addBuildings_title.verifyContainsIgnoreSpaces("Add Buildings","* Denotes a required field");
        verifyMultipleElementsPresent(PropertyDetailPage.propertyInfoTableElements_before_edit);
        verifyMultipleElementsPresent(PropertyDetailPage.depositInfoTableElements);
        verifyMultipleElementsPresent(PropertyDetailPage.addBuildingsTableElements);
        verifyMultipleElementsPresent(PropertyDetailPage.addUnitsFloorsForBuildingsTable1_Elements);
        verifyMultipleElementsContainsIgnoreSpace(PropertyDetailPage.propertyDetails_displayValues,TestData.View_Full_PropertyInfo);
        PropertyDetailPage.unitNameInUnitBlocks_status1.verifyEquals("UNOCCUPIED");
        verifyStatusColor(PropertyDetailPage.unitNameInUnitBlocks_list1,"UNOCCUPIED");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}
