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

public class AddBuildings_PropertyDetailPage {

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
    }

    @Test(groups = { "BAT" },
            description = ("add a building on Property Detail page, verify the elements after clicking edit button of add building table"))
    public void CP_222_2_1() throws Exception{
        PropertyDetailPage.addBuildings_iconBtn.click();
        // test if you input invalid data..
        PropertyDetailPage.buildingName_textBox.input("");
        PropertyDetailPage.saveBuilding_iconBtn.click();
        PropertyDetailPage.buildingName_noInput_errorLabel.verifyElementPresent();
        PropertyDetailPage.buildingName_textBox.input(TestData.Property_Building1_Name);
        PropertyDetailPage.saveBuilding_iconBtn.click();
        PropertyDetailPage.buildingType_noInput_errorLabel.verifyElementPresent();
        PropertyDetailPage.buildingType_selectBox.selectByVisibleText(TestData.Property_Building1_Type);
        PropertyDetailPage.saveBuilding_iconBtn.click();
        // test if you input valid data in detail page
        PropertyDetailPage.editBuilding_iconBtn.verifyElementPresent();
        PropertyDetailPage.buildingName_displayValue.verifyEquals(TestData.Property_Building1_Name);
        PropertyDetailPage.buildingType_displayValue.verifyEquals(TestData.Property_Building1_Type);
        PropertyDetailPage.buildingNameInUnitBlocks_title1.verifyEquals(TestData.Property_Building1_Name + " - " + TestData.Property_Building1_Type);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}