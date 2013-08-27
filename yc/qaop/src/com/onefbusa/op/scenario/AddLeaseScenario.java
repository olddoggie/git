package com.onefbusa.op.scenario;

import com.onefbusa.op.pages.*;
import com.onefbusa.op.properties.General;
import com.onefbusa.op.properties.TestData;
import static applications.BasePage.*;
import static com.onefbusa.op.pages.OPBasePage.*;
import static com.onefbusa.op.scenario.RemoveLeaseScenario.RemoveLeaseByAgencyName;

public class AddLeaseScenario {
    // make a clean normal lease added with agency's existing property(floor,unit), the lease should be ACTIVE and far from expired.
    public static void  Add_Normal_Lease(String agency_name,String property_name, String[] lease_inputs, String[] tenant_inputs) throws Exception {
        // remove the lease data from database at first
        RemoveLeaseByAgencyName(agency_name);
        // fetch the property(floor,unit which has been created in CreatePropertyScenario)
        AgentDashboardPage.properties_tab.click();
        AgentYourPropertiesPage.clickFirstViewBtn_byPropertyName(property_name);
        //'cause normally we configured Lease_Start_Date as an very forthcoming date,
        // so in this case, we need change to an far far future date
        lease_inputs[1] = "2014-09-01";
        // add a clean lease combined with agency's property
        PropertyDetailPage.addLease_btn1.click();
        NewLease_LeaseInfoPage.AddNewLease_LeaseInfo(lease_inputs);
        NewLease_TenantInfoPage.AddNewLease_TenantInfo(tenant_inputs);
        NewLease_SaveLeasePage.saveViewLease_btn.click();
    }

    // make a clean ending soon lease added with agency's existing property(floor,unit), the lease should be ACTIVE and
    // this lease shall be easily been seen at Agent dashboard..
    public static void  Add_EndingSoon_Lease(String agency_name,String property_name,String[] lease_inputs,String[]tenant_inputs) throws Exception {
        // remove the lease data from database at first
        RemoveLeaseByAgencyName(agency_name);
        // fetch the property(floor,unit which has been created in CreatePropertyScenario)
        AgentDashboardPage.properties_tab.click();
        AgentYourPropertiesPage.clickFirstViewBtn_byPropertyName(property_name);
        // add a clean lease combined with agency's property
        PropertyDetailPage.addLease_btn1.click();
        NewLease_LeaseInfoPage.AddNewLease_LeaseInfo(lease_inputs);
        NewLease_TenantInfoPage.AddNewLease_TenantInfo(tenant_inputs);
        NewLease_SaveLeasePage.saveViewLease_btn.click();
    }

    // make a another lease in Property Detail Page
    public static void  Add_Another_Lease(String property_name,String floor, String unit, String[] lease_inputs, String[] tenant_inputs) throws Exception {
        // fetch the property(floor,unit which has been created in CreatePropertyScenario)
        AgentDashboardPage.properties_tab.click();
        AgentYourPropertiesPage.clickFirstViewBtn_byPropertyName(property_name);
        // 1.adding another floor
        PropertyDetailPage.selectFloor_selectBox1.selectByVisibleText(floor);
        // 2. adding another unit
        PropertyDetailPage.inputUnits_textBox1.input(unit);
        PropertyDetailPage.addUnit_btn1.click();
        Thread.sleep(500);
        // 3. add  a new lease, now
        PropertyDetailPage.addLease_btn2.click();
        NewLease_LeaseInfoPage.AddNewLease_LeaseInfo(lease_inputs);
        NewLease_TenantInfoPage.AddNewLease_TenantInfo(tenant_inputs);
        NewLease_SaveLeasePage.saveViewLease_btn.click();
    }

    public static void main(String[] args) {
        try {
            openBrowser("firefox");
            navigateTo(General.SITE_URL);
            Login_Succeed(TestData.Login_AgentEmail,TestData.Login_AgentPassword);
//          Add_Normal_Lease(TestData.AgencyName, TestData.Login_AgentEmail, TestData.Login_AgentPassword, TestData.Property_Name,
//                    TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
            Add_EndingSoon_Lease(TestData.AgencyName,TestData.Property_Name, TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
            Add_Another_Lease(TestData.Property_Name,TestData.Lease_Floor, TestData.Lease_Units ,TestData.AddLease_LeaseInfo, TestData.AddLease_TenantInfo);
            closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
