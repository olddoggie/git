package com.onefbusa.op.test;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import com.onefbusa.op.scenario.AddLeaseScenario;
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

public class SaveAndAddAnotherLease {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // pre condition: make a clean property with a existed lease
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        AddLeaseScenario.Add_EndingSoon_Lease(TestData.AgencyName, TestData.Property_Name, TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
        LeaseDetailPage.viewProperty_btn.click();
        PropertyDetailPage.load();
        // because already have an lease saved, if wanna add another lease, need add a new floor and unit
        PropertyDetailPage.selectFloor_selectBox1.selectByVisibleText(TestData.Lease_Floor);
        PropertyDetailPage.inputUnits_textBox1.input(TestData.Lease_Units);
        PropertyDetailPage.addUnit_btn1.click();
        Thread.sleep(500);
        // click the newly added unit
        PropertyDetailPage.addLease_btn2.click();
        NewLease_LeaseInfoPage.load();
        TestData.AddLease_LeaseInfo[1] = "2014-09-01";
        NewLease_LeaseInfoPage.AddNewLease_LeaseInfo(TestData.AddLease_LeaseInfo);
        NewLease_TenantInfoPage.load();
        NewLease_TenantInfoPage.AddNewLease_TenantInfo(TestData.AddLease_TenantInfo);
    }

    @Test(groups = { "BAT" }, description = ("test agent Add Another Lease based on an existing property"))
    public void CP_222_3_2_1 () throws Exception{
        NewLease_SaveLeasePage.load();
        NewLease_SaveLeasePage.saveAddAnotherLease_btn.click();
        PropertyDetailPage.load().ready(TestData.Property_Name);
        verifyMultipleElementsContainsIgnoreSpace(PropertyDetailPage.propertyDetails_displayValues2,TestData.View_Full_PropertyInfo2);
        // if a lease is created then the unit becomes occupied with the status part showing Tenant's name
        PropertyDetailPage.unitNameInUnitBlocks_status2.verifyContains(TestData.Tenant1_First_Name + " " + TestData.Tenant1_Last_Name);
        verifyStatusColor(PropertyDetailPage.unitNameInUnitBlocks_list2,"OCCUPIED");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}