package com.onefbusa.op.scenario;

import frameLib.MyWebElement;
import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static com.onefbusa.op.scenario.RemoveAgencyScenario.RemoveAgencyByName;
import static com.onefbusa.op.scenario.RemovePropertyScenario.RemovePropertyByAgencyName;
import static frameLib.PrintTestCases.print;

public class CreatePropertyScenario {
    // make a clean property created only by property info and deposit info
    public static void  Create_Basic_PropertyInfo(String agency_name, String... inputs) throws Exception {
        // remove existing properties
        RemovePropertyByAgencyName(agency_name) ;
        // add a clean basic property combined with agency
        AgentDashboardPage.addNewProperty_btn.click();
        PropertyCreatePage.Create_Basic_Property(inputs);
    }

    // make a clean property created including property info and deposit info,and an unoccupied floor, unit pre-added
    public static void  Create_Full_PropertyInfo(String agency_name,String[] basic, String... buildingfloorunit) throws Exception {
        // remove existing properties
        RemovePropertyByAgencyName(agency_name) ;
        // add a clean full property combined with agency
        AgentDashboardPage.addNewProperty_btn.click();
        PropertyCreatePage.Create_Full_Property(basic,buildingfloorunit);
    }

    public static void main(String[] args) {
        try {
            openBrowser("firefox");
            navigateTo(General.SITE_URL);
            Login_Succeed(TestData.Login_AgentEmail, TestData.Login_AgentPassword);
//          Create_Basic_PropertyInfo(TestData.AgencyName,TestData.Create_Basic_PropertyInfo);
            Create_Full_PropertyInfo(TestData.AgencyName,TestData.Create_Basic_PropertyInfo,TestData.AddBuildingFloorUnit);
            closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
