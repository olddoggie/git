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

public class ListViewBuilding {

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

    @Test(groups = { "BAT" }, description = ("test LIST VIEW/GRID VIEW filter on Property Detail page's Building_Floor_Unit block"))
    public void CP_222_15a_1() throws Exception{
        String[] content = {"LIST VIEW","GRID VIEW"};
        PropertyDetailPage.filterView_selectBox.verifyAllOptions(content);
        PropertyDetailPage.filterView_selectBox.verifyFirstSelectedOption("LIST VIEW");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}