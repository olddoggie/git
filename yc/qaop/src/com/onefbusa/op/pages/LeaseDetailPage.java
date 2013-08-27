package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class LeaseDetailPage extends NewLease_SaveLeasePage {
    // web elements in this page
    public static MyWebElement leaseDetail_title =  myWebelement("jQuerySelector:h3:contains('LEASE DETAIL - ')", "LeaseDetailPage_Title");
    public static MyWebElement leaseDetail_statusTitle =  myWebelement("jQuerySelector:h3:contains('LEASE DETAIL - ')>span", "LeaseDetailPage_StatusTitle");

    public static MyWebElement unit_textBox = myWebelement("cssSelector:input#unitName", "Unit#_TextBox");
    public static MyWebElement floor_selectBox = myWebelement("cssSelector:select#floorNumber", "FloorSelectBox");
    public static MyWebElement buildingName_textBox = myWebelement("cssSelector:input#buildingName", "BuildingName_TextBox");
    public static MyWebElement buildingType_selectBox = myWebelement("cssSelector:select#buildingTypeId", "BuildingType_TextBox");
    public static MyWebElement rentAmount_textBox =  myWebelement("cssSelector:input#rent", "RentAmount_TextBox");
    public static MyWebElement paymentPeriod_selectBox =  myWebelement("cssSelector:select#paymentPeriodTypeId", "PaymentPeriod_SelectBox");
    public static MyWebElement securityAmount_textBox =  myWebelement("cssSelector:input#securityAmount", "SecurityAmount_TextBox");

    public static MyWebElement tenantEmail_textBox = myWebelement("jQuerySelector:input#email", "TenantEmailName_TextBox");
    public static MyWebElement tenantTitle_textBox = myWebelement("jQuerySelector:input#title", "TenantTitleName_TextBox");
    public static MyWebElement tenantFirstName_textBox = myWebelement("jQuerySelector:input#firstName", "TenantFirstName_TextBox");
    public static MyWebElement tenantMiddleInitial_textBox = myWebelement("jQuerySelector:input#middleInitial", "TenantMiddleInitial_TextBox");
    public static MyWebElement tenantLastName_textBox = myWebelement("jQuerySelector:input#lastName", "TenantLastName_TextBox");
    public static MyWebElement tenantSuffix_textBox = myWebelement("jQuerySelector:input#suffix", "TenantSuffix_TextBox");
    public static MyWebElement tenantDOB_tableBox = myWebelement("jQuerySelector:input[name=dateOfBirth]", "TenantDOB_TextBox");
    public static MyWebElement tenantPhoneOne_textBox = myWebelement("jQuerySelector:input#phoneOne", "TenantPhoneOne_TextBox");
    public static MyWebElement tenantState_selectBox = myWebelement("jQuerySelector:select#stateOfId", "TenantStateOfID_SelectBox");
    public static MyWebElement tenantSSN_textBox = myWebelement("jQuerySelector:input#lastFourDigitsSSN", "TenantSSN_TextBox");
    public static MyWebElement tenantNonResident_checkBox = myWebelement("jQuerySelector:label.checkbox input#inputNonResident", "TenantNonResident_CheckBox");
    public static MyWebElement editTenantPicker_img = myWebelement("jQuerySelector:input[name=dateOfBirth] + img","EditTenantDatePicker_Image");
    public static MyWebElement tenantEmail_textBox2 = myWebelement("jQuerySelector:input#email", "TenantEmailName_TextBox_2",1);
    public static MyWebElement tenantTitle_textBox2 = myWebelement("jQuerySelector:input#title", "TenantTitleName_TextBox_2",1);
    public static MyWebElement tenantFirstName_textBox2 = myWebelement("jQuerySelector:input#firstName", "TenantFirstName_TextBox_2",1);
    public static MyWebElement tenantMiddleInitial_textBox2 = myWebelement("jQuerySelector:input#middleInitial", "TenantMiddleInitial_TextBox_2",1);
    public static MyWebElement tenantLastName_textBox2 = myWebelement("jQuerySelector:input#lastName", "TenantLastName_TextBox_2",1);
    public static MyWebElement tenantSuffix_textBox2 = myWebelement("jQuerySelector:input#suffix", "TenantSuffix_TextBox_2",1);
    public static MyWebElement tenantDOB_tableBox2 = myWebelement("jQuerySelector:input[name=dateOfBirth]", "TenantDOB_TextBox_2",1);
    public static MyWebElement tenantPhoneOne_textBox2 = myWebelement("jQuerySelector:input#phoneOne", "TenantPhoneOne_TextBox_2",1);
    public static MyWebElement tenantState_selectBox2 = myWebelement("jQuerySelector:select#stateOfId", "TenantStateOfID_SelectBox_2",1);
    public static MyWebElement tenantSSN_textBox2 = myWebelement("jQuerySelector:input#lastFourDigitsSSN", "TenantSSN_TextBox_2",1);
    public static MyWebElement tenantNonResident_checkBox2 = myWebelement("jQuerySelector:label.checkbox input#inputNonResident", "TenantNonResident_CheckBox_2",1);
    public static MyWebElement editTenantPicker_img2 = myWebelement("jQuerySelector:input[name=dateOfBirth] + img","EditTenantDatePicker_Image_2",1);

    public static MyWebElement updateTerm_btn = myWebelement("updateTerm","UpdateTerm_Button");
    public static MyWebElement editLeaseInfo_btn = myWebelement("editLeaseBtn","EditLeaseInfo_Button");
    public static MyWebElement saveLeaseInfo_btn = myWebelement("saveLeaseBtn","SaveLeaseInfo_Button");

    public static MyWebElement leaseStatus_afterEdit_displayValue = myWebelement("jQuerySelector:label:contains('STATUS:') + div span:eq(1)","LeaseStatus_AfterEdit_DisplayValue");
    public static MyWebElement leaseStatus_resolved_displayValue = myWebelement("jQuerySelector:label:contains('STATUS:') + div b","LeaseStatus_RESOLVED_DisplayValue");
    public static MyWebElement editTenant_btn = myWebelement("editTenantBtn","EditTenant_Button");
    public static MyWebElement saveTenant_btn = myWebelement("saveTenantBtn","SaveTenant_Button");
    public static MyWebElement viewProperty_btn = myWebelement("jQuerySelector:button:contains('VIEW PROPERTY')","ViewProperty_Button");
    public static MyWebElement refundSecurityDeposit_btn = myWebelement("jQuerySelector:button:contains('REFUND SECURITY DEPOSIT')","RefundSecurityDeposit_Button");

    public static MyWebElement renewTimes_subTitle =  myWebelement("cssSelector:div.sub-title", "RenewTimes_UubTitle");
    public static MyWebElement leaseHistory_tableTitle =  myWebelement("jQuerySelector:div.row-fluid p:contains('LEASE HISTORY')", "LeaseHistory_TableTitle");
    public static MyWebElement renewDate1_displayValue =  myWebelement("jQuerySelector:dt:eq(0)", "RenewDate_DisplayValue");
    public static MyWebElement renewType1_displayValue =  myWebelement("jQuerySelector:dd:eq(0)", "RenewDate_DisplayValue");
    public static MyWebElement scoreContainerA_displayValue = myWebelement("cssSelector:div.score_A_container","ScoreContainerA_DisplayValue");
    public static MyWebElement scoreContainerB_displayValue = myWebelement("cssSelector:div.score_B_container","ScoreContainerB_DisplayValue");
    public static MyWebElement scoreContainerC_displayValue = myWebelement("cssSelector:div.score_C_container","ScoreContainerC_DisplayValue");

    public static MyWebElement startDate_label_beforeEdit = myWebelement("jQuerySelector:label:contains('START DATE:')", "StartDate_Label");
    public static MyWebElement endDate_label_beforeEdit = myWebelement("jQuerySelector:label:contains('END DATE:')", "EndDate_label");
    public static MyWebElement rentAmount_label_beforeEdit = myWebelement("jQuerySelector:label[for=inputRentAmount]", "RentAmount_Label");
    public static MyWebElement paymentPeriod_label_beforeEdit = myWebelement("jQuerySelector:label[for=inputPaymentPeriod]", "PaymentPeriod_Label");
    public static MyWebElement securityAmount_label_beforeEdit = myWebelement("jQuerySelector:label[for=inputSecurityAmount]", "SecurityAmount_Label");

    public static MyWebElement floor_displayValue = myWebelement("jQuerySelector:label[for=inputFloor] + * + div", "Floor_DisplayValue");
    public static MyWebElement startDate_displayValue = myWebelement("jQuerySelector:label:contains('START DATE:'):visible + div", "StartDate_DisplayValue");
    public static MyWebElement endDate_displayValue = myWebelement("jQuerySelector:label:contains('END DATE:'):visible + div", "EndDate_DisplayValue");
    public static MyWebElement rentAmount_displayValue = myWebelement("jQuerySelector:label[for=inputRentAmount] + * + div", "RentAmount__DisplayValue");
    public static MyWebElement paymentPeriod_displayValue = myWebelement("jQuerySelector:label[for=inputPaymentPeriod] + * + div", "PaymentPeriod_DisplayValue");
    public static MyWebElement securityAmount_displayValue = myWebelement("jQuerySelector:label[for=inputSecurityAmount] + * + div", "SecurityAmount_DisplayValue");

    public static MyWebElement tenantEmail_label = myWebelement("jQuerySelector:label:contains('EMAIL:')","TenantEmail*_Label");
    public static MyWebElement tenantFirstName_label = myWebelement("jQuerySelector:label:contains('FIRST NAME:')","TenantFirstName*_Label");
    public static MyWebElement tenantLastName_label = myWebelement("jQuerySelector:label:contains('LAST NAME:')","TenantLastName*_Label");
    public static MyWebElement tenantDOB_label = myWebelement("jQuerySelector:label:contains('DOB:')","TenantDOB*_Label");
    public static MyWebElement tenantPhoneOne_label = myWebelement("jQuerySelector:label:contains('PHONE #:')","TenantPhoneOne*_Label");
    public static MyWebElement tenantState_label = myWebelement("jQuerySelector:label:contains('STATE OF ID:')","TenantStateOfID*_Label");
    public static MyWebElement tenantSSN_label = myWebelement("jQuerySelector:label:contains('LAST 4 DIGITS SSN:')","TenantLast4SSN*_Label");

    public static MyWebElement tenantEmail_displayValue = myWebelement("jQuerySelector:label:contains('EMAIL:') + div","TenantEmail*_DisplayValue");
    public static MyWebElement tenantFirstName_displayValue = myWebelement("jQuerySelector:label:contains('FIRST NAME:') + div","TenantFirstName*_DisplayValue");
    public static MyWebElement tenantLastName_displayValue = myWebelement("jQuerySelector:label:contains('LAST NAME:') + div","TenantLastName*_DisplayValue");
    public static MyWebElement tenantDOB_displayValue = myWebelement("jQuerySelector:label:contains('DOB:') + div","TenantDOB*_DisplayValue");
    public static MyWebElement tenantPhoneOne_displayValue = myWebelement("jQuerySelector:label:contains('PHONE #:') + div","TenantPhoneOne*_DisplayValue");
    public static MyWebElement tenantState_displayValue = myWebelement("jQuerySelector:label:contains('STATE OF ID:') + div","TenantStateOfID*_DisplayValue");
    public static MyWebElement tenantSSN_displayValue = myWebelement("jQuerySelector:label:contains('LAST 4 DIGITS SSN:') + div","TenantLast4SSN*_DisplayValue");

    public static MyWebElement ownedBy_displayValue = myWebelement("jQuerySelector:label:contains('OWNED BY:') + div", "OwnedBy_DisplayValue");
    public static MyWebElement managedBy_displayValue = myWebelement("jQuerySelector:label:contains('MANAGED BY:') + div", "ManagedBy_displayValue");
    public static MyWebElement propertyName_displayValue = myWebelement("jQuerySelector:label:contains('PROPERTY NAME:') + div", "PropertyName_DisplayValue");
    public static MyWebElement propertyAddress1_displayValue = myWebelement("jQuerySelector:label:contains('ADDRESS 1:') + div", "PropertyAddress1_DisplayValue");
    public static MyWebElement propertyBuildingType_displayValue = myWebelement("jQuerySelector:label[for=inputBuildingType] + div", "BuildingType_DisplayValue");

    // page title
    public static String pageTitle = "LEASE DETAIL";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  the label part in unit lease table
    public static MyWebElement [] unitLeaseInfoTableElements =
            {listLeaseInfo_icon, unitLeaseInfo_tableTitle, unit_label, floor_label, buildingName_label, buildingType_label, startDate_label_beforeEdit,
                    endDate_label_beforeEdit, rentAmount_label_beforeEdit, paymentPeriod_label_beforeEdit, securityAmount_label_beforeEdit, leaseStatus_label,
                    updateTerm_btn, editLeaseInfo_btn} ;
        //  the label part in tenant table
    public static MyWebElement [] tenantInfoTableElements = {userTenantInfo_icon, tenantInfo_tableTitle, addAnotherTenant_btn, tenantTab_link1,
            editTenant_btn, tenantEmail_label, tenantTitle_label, tenantFirstName_label, tenantMiddleInitial_label, tenantLastName_label, tenantSuffix_label,
            tenantDOB_label, tenantPhoneOne_label, tenantState_label, tenantSSN_label, tenantNonResident_label, removeTenant_btn};
        //  the label part in property table
    public static MyWebElement [] propertyInfoTableElements = {home_icon, propertyInfo_title, ownedBy_label, managedBy_label, buildingType_label,
            propertyName_label, propertyAddress1_label, propertyAddress2_label,propertyCity_label, propertyStateZip_label, propertyTaxLot_label, viewProperty_btn};
        //  all the display values of each label in unit lease table, get the data from database
    public static MyWebElement[] unitLeaseInfoTable_displayValues = {unit_displayValue, floor_displayValue, buildingName_displayValue, buildingType_displayValue,
            startDate_displayValue, endDate_displayValue, rentAmount_displayValue, paymentPeriod_displayValue, securityAmount_displayValue, leaseStatus_displayValue};
        //  all the display values of each label in tenant table, get the data from database
    public static MyWebElement[] tenantInfoTable_displayValues = {tenantEmail_displayValue, tenantTitle_displayValue, tenantFirstName_displayValue, tenantMiddleInitial_displayValue,
            tenantLastName_displayValue, tenantSuffix_displayValue, tenantDOB_displayValue, tenantPhoneOne_displayValue, tenantState_displayValue, tenantSSN_displayValue, tenantNonResident_displayValue};
        //  all the display values of each label in property table
    public static MyWebElement[] propertyInfoTable_displayValues = {ownedBy_displayValue, managedBy_displayValue, propertyBuildingType_displayValue, propertyName_displayValue,
            propertyAddress1_displayValue,propertyAddress2_displayValue, propertyCity_displayValue, propertyStateZip_displayValue, propertyTaxLot_displayValue};
        // all the text boxes, select box, radio button, etc. which will be shown after click edit button in unit lease table
    public static MyWebElement[] unitLeaseInfoTable_editableObjects = {unit_textBox, floor_selectBox, buildingName_textBox,buildingType_selectBox, startDate_displayValue, endDate_displayValue,
            rentAmount_textBox, paymentPeriod_selectBox, securityAmount_textBox};
        // all the text boxes, select box, radio button, etc. which will be shown after click edit button in tenant table
    public static MyWebElement[] tenantInfoTable_editableObjects = {tenantEmail_textBox, tenantTitle_textBox, tenantFirstName_textBox, tenantMiddleInitial_textBox,
            tenantLastName_textBox, tenantSuffix_textBox, tenantDOB_tableBox, editTenantPicker_img, tenantPhoneOne_textBox, tenantState_selectBox, tenantSSN_textBox, tenantNonResident_checkBox};
        // all the text boxes, select box, radio button, etc. which will be shown after click add another tenant button in tenant table
    public static MyWebElement[] tenantInfoTable_editableObjects2 = {tenantEmail_textBox2, tenantTitle_textBox2, tenantFirstName_textBox2, tenantMiddleInitial_textBox2,
            tenantLastName_textBox2, tenantSuffix_textBox2, tenantDOB_tableBox2, editTenantPicker_img2, tenantPhoneOne_textBox2, tenantState_selectBox2, tenantSSN_textBox2, tenantNonResident_checkBox2};

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static LeaseDetailPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new LeaseDetailPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready (String leaseStatus) {
        verifyPageTitle(pageTitle);
        leaseDetail_title.verifyContainsIgnoreSpaces("LEASE DETAIL -" + leaseStatus);
        // verify the lease color according to its status..
        verifyStatusColor(leaseDetail_statusTitle,leaseStatus);
    }

}
