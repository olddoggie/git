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

public class EditDepositInfo {

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
        PropertyDetailPage.editDeposit_btn.click();
    }

    @Test(groups = { "BAT" }, description = ("test elements of Deposit Info table after click edit button on Property Detail page"))
    public void CP_222_6_1() throws Exception{
        verifyMultipleElementsPresent(PropertyDetailPage.depositInfoTable_editableObjects);
        PropertyDetailPage.bankName_textBox.verifyContainsIgnoreSpaces(TestData.Property_BankName);
        PropertyDetailPage.interest_selectBox.verifyFirstSelectedOption(TestData.Property_Interest);
        PropertyDetailPage.adminFee_selectBox.verifyFirstSelectedOption(TestData.Property_Admin_Fee);
        // in test data, we choose tenantPay_radioBox as payment type
        PropertyDetailPage.paymentType_singleParty_radioBox.verifyEquals("null","checked");
        PropertyDetailPage.paymentType_tenantPay_radioBox.verifyEquals("true","checked");
        PropertyDetailPage.returnProcess_textBox.verifyContainsIgnoreSpaces(TestData.Property_Return_Process);
        PropertyDetailPage.saveDeposit_btn.verifyElementPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterClass() throws Exception{
        closeBrowser();
    }

}