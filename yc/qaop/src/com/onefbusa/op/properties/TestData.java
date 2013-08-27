package com.onefbusa.op.properties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class TestData {
    private static ResourceBundle rb = ResourceBundle.getBundle("com.onefbusa.op.properties.test_data");
    // log in data
    public static final String Login_AgentEmail = rb.getString("login_email_agent");
    public static final String Login_AgentFirstName = rb.getString("firstName_agent");
    public static final String Login_AgentPassword = rb.getString("login_password_agent");
    public static final String Login_AgentOfficePhone = rb.getString("managedByBusiness_phone");
    public static final String Login_AgentAddress1 = rb.getString("address1_agent");
    public static final String Login_AgentCity = rb.getString("city_agent");
    public static final String Login_AgentState = rb.getString("state_agent");
    public static final String Login_AgentZip5 = rb.getString("zip5_agent");
    public static final String Login_AgentZip4 = rb.getString("zip4_agent");
    public static final String Login_AgentLastName = rb.getString("lastName_agent");
    public static final String Login_AgentMainPhone = rb.getString("mainPhone_agent");
    public static final String Login_AgentConfirmPassword = rb.getString("confirmPassword_agent");

    public static final String Login_TenantEmail = rb.getString("login_email_tenant");
    public static final String Login_TenantFirstName = rb.getString("firstName_tenant");
    public static final String Login_TenantPassword = rb.getString("login_password_tenant");

    public static final String Login_AdminEmail = rb.getString("login_email_admin");
    public static final String Login_AdminFirstName = rb.getString("firstName_admin");
    public static final String Login_AdminPassword = rb.getString("login_password_admin");

    // sign up data
    public static final String AgentSignUp_BusinessName = rb.getString("agentSignUp_businessName");
    public static final String AgentSignUp_OfficeEmail = rb.getString("agentSignUp_officeEmail");
    public static final String AgentSignUp_OfficePhone = rb.getString("agentSignUp_officePhone");
    public static final String AgentSignUp_FEIN = rb.getString("agentSignUp_fein");
    public static final String AgentSignUp_Address1 = rb.getString("agentSignUp_address1");
    public static final String AgentSignUp_Address2 = rb.getString("agentSignUp_address2");
    public static final String AgentSignUp_City = rb.getString("agentSignUp_city");
    public static final String AgentSignUp_State = rb.getString("agentSignUp_state");
    public static final String AgentSignUp_State_Short = rb.getString("agentSignUp_state_short");
    public static final String AgentSignUp_Zip5 = rb.getString("agentSignUp_zip5");
    public static final String AgentSignUp_Zip4 = rb.getString("agentSignUp_zip4");
    public static final String AgentSignUp_JobTitle = rb.getString("agentSignUp_jobTitle");
    public static final String AgentSignUp_Email = rb.getString("agentSignUp_email");
    public static final String AgentSignUp_FirstName = rb.getString("agentSignUp_firstName");
    public static final String AgentSignUp_LastName = rb.getString("agentSignUp_lastName");
    public static final String AgentSignUp_MainPhone = rb.getString("agentSignUp_mainPhone");
    public static final String AgentSignUp_CellPhone = rb.getString("agentSignUp_cellPhone");
    public static final String AgentSignUp_Password = rb.getString("agentSignUp_password");
    public static final String AgentSignUp_ConfirmPassword = rb.getString("agentSignUp_confirmPassword");
    // list all mandatory inputs above when sign up a new agent
    public static final String[] AgentSignUpMandatoryInput = {AgentSignUp_BusinessName, "", AgentSignUp_OfficePhone, "", AgentSignUp_Address1,
            "", AgentSignUp_City, AgentSignUp_State, AgentSignUp_Zip5, AgentSignUp_Zip4, "", AgentSignUp_FirstName,
            AgentSignUp_LastName, AgentSignUp_Email, AgentSignUp_MainPhone, "", AgentSignUp_Password, AgentSignUp_ConfirmPassword};

    // agency data
    public static final String AgencyName = rb.getString("ownedByBusiness_name");
    public static final String AgencyAddress = rb.getString("businessAddress1_name");
    public static final String OwnedByAgency = rb.getString("ownedByBusiness_name") + " - " + rb.getString("businessAddress1_name");
    public static final String ManagedByAgency = rb.getString("managedByBusiness_name") + " - " + rb.getString("businessAddress1_name");
    public static final String PropertyName = rb.getString("property_name");

    // sign up a login agent
    public static final String[] LoginAgentMandatoryInput = {AgencyName, "", Login_AgentOfficePhone, "", Login_AgentAddress1,
            "", Login_AgentCity, Login_AgentState, Login_AgentZip5, Login_AgentZip4, "", Login_AgentFirstName,
            Login_AgentLastName, Login_AgentEmail, Login_AgentMainPhone, "", Login_AgentPassword, Login_AgentConfirmPassword};

    // create property data, there should only be 1 building in CAT1
    public static final String Property_Name = rb.getString("property_name");
    public static final String Property_Address1 = rb.getString("property_address1");
    public static final String Property_Address2 = rb.getString("property_address2");
    public static final String Property_City = rb.getString("property_city");
    public static final String Property_State = rb.getString("property_state");
    public static final String Property_State_abbreviation = rb.getString("property_state_abbreviation");
    public static final String Property_Zip = rb.getString("property_zip5");
    public static final String Property_TaxLot = rb.getString("property_taxLot");
    public static final String Property_Building1_Name = rb.getString("property_building1_name");
    public static final String Property_Building1_Type = rb.getString("property_building1_type");
    public static final String Property_Floor = rb.getString("property_floor");
    // if unit name is a, and its on 3rd floor, then in system, the display unit name should be 3a
    public static final String Property_Units = rb.getString("property_units");
    public static final String Property_Units_Display = rb.getString("property_floor") + rb.getString("property_units");
    public static final String Property_BankName = rb.getString("property_bankName");
    public static final String Property_Interest = rb.getString("property_interest");
    public static final String Property_Admin_Fee = rb.getString("property_admin_fee");
    public static final String Property_Payment_Type = rb.getString("property_payment_type");
    public static final String Property_Return_Process = rb.getString("property_returns_process");
    // list all the basic inputs for property creation (basic means create property without adding building name)
    public static final String[] Create_Basic_PropertyInfo = {OwnedByAgency, ManagedByAgency, Property_Name, Property_Address1, Property_Address2,
            Property_City, Property_State, Property_Zip, Property_TaxLot, Property_BankName, Property_Interest, Property_Admin_Fee, Property_Payment_Type,
            Property_Return_Process};

    // list all the full inputs for property creation, add building name twice 'cause need verify both in addBuilding table and UnitBlocks
    public static final String[] AddBuildingFloorUnit = {Property_Building1_Name, Property_Building1_Type, Property_Floor, Property_Units};

    // list all the data in Property Details Page
    public static final String[] View_Full_PropertyInfo = {AgencyName, AgencyName, Property_Name, Property_Address1, Property_Address2,
            Property_City, Property_State + ", " + Property_Zip, Property_TaxLot, Property_BankName, Property_Interest, Property_Admin_Fee, Property_Payment_Type,
            Property_Return_Process, Property_Building1_Name, Property_Building1_Type, Property_Building1_Name, Property_Floor, Property_Units};

    // add a new lease after property created, step 1  # if no available units in Property, then just add a new unit first, or user can specify that in test case
    public static String Lease_Start_Date = GetLease_Start_Date(rb.getString("lease_start_date"));
    public static final String Lease_End_Date = rb.getString("lease_end_date");
    public static final String Lease_Rent_Amount = rb.getString("lease_rent_amount");
    public static final String Lease_Rent_Amount_Display = "$" + rb.getString("lease_rent_amount_display");
    public static final String Lease_Payment_Period = rb.getString("lease_payment_period");
    public static final String Lease_Security_Amount = rb.getString("lease_security_amount");
    public static final String Lease_Security_Amount_Display = "$" + rb.getString("lease_security_amount_display");
    public static final String Lease_Floor = rb.getString("lease_floor");
    public static final String Lease_Units = rb.getString("lease_units");
    public static final String Lease_Units_Display = rb.getString("lease_floor") + rb.getString("lease_units");

    // add lease step 1
    public static final String leaseName =  TestData.Property_Address1 + ", " + TestData.Property_City + ", "
            + TestData.Property_State_abbreviation + " " + TestData.Property_Zip + " -  FLOOR "
            + TestData.Property_Floor + " UNIT " + TestData.Property_Units_Display;
    public static final String[] AddLease_LeaseInfo = {"TODAY", Lease_End_Date, Lease_Rent_Amount, Lease_Payment_Period,Lease_Security_Amount};
    public static final String[] AddLease_DepositDisplayValue = {Property_BankName, Property_Interest, Property_Admin_Fee, Property_Payment_Type, Property_Return_Process};

    // add a new lease step2, input 1st tenant info   # if you wanna input a new tenant when add lease, then specify a new name in test case should be better
    public static final String Tenant1_Email = rb.getString("tenant1_email");
    public static final String Tenant1_Title = rb.getString("tenant1_title");
    public static final String Tenant1_First_Name = rb.getString("tenant1_first_name");
    public static final String Tenant1_Middle_Name = rb.getString("tenant1_middle_name");
    public static final String Tenant1_Last_Name = rb.getString("tenant1_last_name");
    public static final String Tenant1_Suffix = rb.getString("tenant1_suffix");
    public static final String Tenant1_DOB = rb.getString("tenant1_DOB");
    public static final String Tenant1_Phone_One = rb.getString("tenant1_phone_one");
    public static final String Tenant1_State = rb.getString("tenant1_state");
    public static final String Tenant1_Last4_SSN = rb.getString("tenant1_last4_SSN");
    public static final String Tenant1_NonResidence = rb.getString("tenant1_nonResidence");
    public static final String Tenant_Name = Tenant1_First_Name + " " + Tenant1_Last_Name;

    public static final String LeaseName_UpdateTermPage = Property_Address1 + " - Floor "
            + Property_Floor + "Unit " + Property_Units_Display + " - " + Tenant_Name;

    // add lease step2
    public static final String[] AddLease_TenantInfo = {Tenant1_Email,Tenant1_Title, Tenant1_First_Name, Tenant1_Middle_Name, Tenant1_Last_Name,Tenant1_Suffix,
            Tenant1_DOB, Tenant1_Phone_One, Tenant1_State, Tenant1_Last4_SSN, "YES"};

    //  display value in save lease page (add lease step3)
    public static final String[] SaveLease_PropertyDisplayValue = {AgencyName, AgencyName, PropertyName, Property_Address1, Property_Address2,
            Property_City, Property_State + ", " + Property_Zip, Property_TaxLot};
    public static final String[] LeaseDetail_PropertyDisplayValue = {AgencyName, AgencyName,Property_Building1_Type, PropertyName, Property_Address1, Property_Address2,
            Property_City, Property_State + ", " + Property_Zip, Property_TaxLot};
    public static final String[] SaveLease_UnitLeaseDisplayValue = {Property_Units_Display, Property_Floor + "RD FLOOR" , Property_Building1_Name, Property_Building1_Type,
            Lease_Start_Date, Lease_End_Date, Lease_Rent_Amount_Display, Lease_Payment_Period, Lease_Security_Amount_Display, "ACTIVE"};
    public static final String[] SaveLease_TenantDisplayValue = AddLease_TenantInfo;

    // save another lease's TestData, only different with the lease's unit and floor info
    public static final String[] SaveAnotherLease_UnitLeaseDisplayValue = {Lease_Units_Display, Lease_Floor + "RD FLOOR" , Property_Building1_Name, Property_Building1_Type,
            Lease_Start_Date, Lease_End_Date, Lease_Rent_Amount_Display, Lease_Payment_Period, Lease_Security_Amount_Display, "ACTIVE"};

    // list all the data in Property Details Page  for another lease, only difference is floor and unit
    public static final String[] View_Full_PropertyInfo2 = {AgencyName, AgencyName, Property_Name, Property_Address1, Property_Address2,
            Property_City, Property_State + ", " + Property_Zip, Property_TaxLot, Property_BankName, Property_Interest, Property_Admin_Fee, Property_Payment_Type,
            Property_Return_Process, Property_Building1_Name, Property_Building1_Type, Property_Building1_Name, Lease_Floor, Lease_Units};

    // view property details info after add another lease. page goes to property detail,
    // only different from View_Full_PropertyInfo's part is: floor and unit
    public static final String[] View_Full_PropertyInfo_after_another_lease = {AgencyName, AgencyName, PropertyName, Property_Address1, Property_Address2,
            Property_City, Property_State + ", " + Property_Zip, Property_TaxLot, Property_BankName, Property_Interest, Property_Admin_Fee, Property_Payment_Type,
            Property_Return_Process, Property_Building1_Name, Property_Building1_Name, Property_Building1_Type, Lease_Floor, Lease_Units_Display};

    // display value in agent dashboard
    public static final String[] LeaseEndingSoonTable_in_agent_dashboard = { Property_Name + ",", Property_Address1 +
            ",", "UNIT ", Property_Units_Display};

    // display value in Agent Your Lease page
    public static final String[] LeaseInfoTable_in_your_leases_active =
            {Property_Name + Property_Address1 + Property_City + "," + Property_State + Property_Zip + "-0000" + "Unit " + Property_Units_Display + " - Floor " + Property_Floor,
                    Tenant1_First_Name + Tenant1_Last_Name + Tenant1_Email + Tenant1_DOB + Tenant1_Phone_One, "Start:" + Lease_Start_Date + "End:" + Lease_End_Date ,
                    Lease_Rent_Amount_Display + Lease_Payment_Period, Lease_Security_Amount_Display, "ACTIVE"};

    // display unit list value in property detail page
    public static final String[] UnitListView_PropertyDetailPage = { "Unit", Property_Units_Display, Tenant1_First_Name + " " + Tenant1_Last_Name, Lease_Start_Date
            , "-", Lease_End_Date, Lease_Rent_Amount_Display, "VIEW"};

    // Refund Lease
    public static final String SecurityDepositRefundAmount = rb.getString("securityDepositRefundAmount");
    public static final String SecurityRefundComment = rb.getString("securityRefundComment");
    public static final String AmountHeld_row1 = rb.getString("amountHeld_row1");
    public static final String AmountHeld_row2 = rb.getString("amountHeld_row2");
    public static final String AmountHeld_row3 = rb.getString("amountHeld_row3");
    public static final String RefundReason_row1 = rb.getString("refundReason_row1");
    public static final String RefundReason_row2 = rb.getString("refundReason_row2");
    public static final String RefundReason_row3 = rb.getString("refundReason_row3");
    public static final String DateRefund = rb.getString("dateRefund");

    public static final String[] RefundLease_Inputs = {SecurityDepositRefundAmount, SecurityRefundComment, AmountHeld_row1, AmountHeld_row2,
            AmountHeld_row3, RefundReason_row1,RefundReason_row2,RefundReason_row3, DateRefund};

    public static final String[]  visibleOptionsInSelectBox = {"Select...","Damage", "Fees for late payment of rent",
            "Other/No Fault", "Other/Uncovered cost to Landlord", "Security Deposit Inadequate, Landlord pursuing tenant for additional funds",
            "Unpaid Bills left by Tenant (e.g. utility, etc.)","Unpaid Landlord Fees (e.g. move out fee, lease breakage fee, etc.)","Unpaid Rent"};


    // Get the current date
    public static String GetNowDate(){
        String temp_str="";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        temp_str=sdf.format(dt);
        return temp_str;
    }
    public static final String GetLease_Start_Date(String date){
        if (date.equalsIgnoreCase("TODAY")) {
            Lease_Start_Date = GetNowDate();
        }  else {
            Lease_Start_Date = date;
        }
        return Lease_Start_Date;
    }

}
