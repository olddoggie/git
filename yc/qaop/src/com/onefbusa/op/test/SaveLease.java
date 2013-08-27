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

public class SaveLease {

    @BeforeMethod (alwaysRun = true)
    @Parameters({"browser"})
    public void setupBeforeClass(String browser) throws Exception {
        openBrowser(browser);
        navigateTo(General.SITE_URL);
        HomePage.load();
        Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
        AgentDashboardPage.load();
        // make a clean property for lease adding
        CreatePropertyScenario.Create_Full_PropertyInfo(TestData.AgencyName, TestData.Create_Basic_PropertyInfo, TestData.AddBuildingFloorUnit);
        PropertyDetailPage.load();
        PropertyDetailPage.addLease_btn1.click();
        NewLease_LeaseInfoPage.load();
        NewLease_LeaseInfoPage.AddNewLease_LeaseInfo(TestData.AddLease_LeaseInfo);
        NewLease_TenantInfoPage.load();
        NewLease_TenantInfoPage.AddNewLease_TenantInfo(TestData.AddLease_TenantInfo);
    }

    @Test(groups = { "BAT" }, description = ("test elements on add lease step3 -- Save Lease page"))
    public void CP_222_3_1_1 () throws Exception{
        NewLease_SaveLeasePage.load().ready();
        verifyMultipleElementsPresent(NewLease_SaveLeasePage.newLease_propertyInfoElements);
        verifyMultipleElementsPresent(NewLease_SaveLeasePage.newLeaseInfoElements);
        verifyMultipleElementsPresent(NewLease_SaveLeasePage.newLease_tenantInfoElements);
        NewLease_SaveLeasePage.saveAddAnotherLease_btn.verifyElementPresent();
        NewLease_SaveLeasePage.saveViewLease_btn.verifyElementPresent();
        verifyMultipleElementsContains(NewLease_SaveLeasePage.newLease_propertyInfoDisplayValues,TestData.SaveLease_PropertyDisplayValue);
        verifyMultipleElementsContains(NewLease_SaveLeasePage.newLease_unitLeaseInfoDisplayValues,TestData.SaveLease_UnitLeaseDisplayValue);
        verifyMultipleElementsContains(NewLease_SaveLeasePage.newLease_tenantInfoDisplayValues,TestData.SaveLease_TenantDisplayValue);
        verifyStatusColor(NewLease_SaveLeasePage.leaseStatus_displayValue,"ACTIVE");
    }

    @Test(groups = { "BAT" }, description = ("test agent Save and View a new lease, system goes to Lease Detail page"))
    public void CP_222_3_1_3 () throws Exception{
        NewLease_SaveLeasePage.load();
        NewLease_SaveLeasePage.saveViewLease_btn.click();
        LeaseDetailPage.load().ready("ACTIVE");
        verifyMultipleElementsContainsIgnoreSpace(LeaseDetailPage.propertyInfoTable_displayValues,TestData.LeaseDetail_PropertyDisplayValue);
        verifyMultipleElementsContainsIgnoreSpace(LeaseDetailPage.unitLeaseInfoTable_displayValues,TestData.SaveLease_UnitLeaseDisplayValue);
        verifyMultipleElementsContainsIgnoreSpace(LeaseDetailPage.tenantInfoTable_displayValues,TestData.SaveLease_TenantDisplayValue);
        verifyStatusColor(LeaseDetailPage.leaseStatus_displayValue,"ACTIVE");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}