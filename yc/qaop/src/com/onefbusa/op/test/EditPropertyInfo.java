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

public class EditPropertyInfo {

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
        PropertyDetailPage.editProperty_btn.click();
    }

    @Test(groups = { "BAT" }, description = ("test elements of Property Info table after click edit button on Property Detail page"))
    public void CP_222_4_1() throws Exception{
        verifyMultipleElementsPresent(PropertyDetailPage.propertyInfoTable_editableObjects);
        PropertyDetailPage.propertyState_selectBox.verifyFirstSelectedOption(TestData.Property_State);
        String[] display_value = {TestData.AgencyName, TestData.AgencyName, TestData.PropertyName, TestData.Property_Address1, TestData.Property_Address2,
                TestData.Property_City, TestData.Property_State, TestData.Property_Zip, TestData.Property_TaxLot};
        verifyMultipleElementsContainsIgnoreSpace(PropertyDetailPage.propertyInfoTable_editableObjects,display_value);
        PropertyDetailPage.saveProperty_btn.verifyElementPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}