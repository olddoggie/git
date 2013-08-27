package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class NewLease_SaveLeasePage extends NewLease_TenantInfoPage {
    // web elements in this page
    public static MyWebElement saveLease_leaseInfo_title =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - LEASE INFO - SAVE LEASE')", "SaveLease_LeaseInfoPage_Title");
    public static MyWebElement saveLease_leaseInfo_subTitle =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - LEASE INFO - SAVE LEASE') + p", "SaveLease_LeaseInfoPage_SubTitle");
    public static MyWebElement unitLeaseInfo_tableTitle =  myWebelement("jQuerySelector:div.row-fluid p:contains(' UNIT & LEASE INFO'):visible", "UnitLeaseInfo_TableTitle");
    public static MyWebElement propertyInfo_title =  myWebelement("jQuerySelector:div.row-fluid p:contains('PROPERTY INFO'):visible", "PropertyInfo_TableTitle",0);

    public static MyWebElement unit_label = myWebelement("jQuerySelector:label:contains('UNIT #:')", "Unit#_Label");
    public static MyWebElement floor_label = myWebelement("jQuerySelector:label:contains('FLOOR #:')", "Floor_Label");
    public static MyWebElement buildingName_label = myWebelement("jQuerySelector:label:contains('BUILDING NAME:')", "BuildingName_Label");
    public static MyWebElement buildingType_label = myWebelement("jQuerySelector:label:contains('BUILDING TYPE:')", "BuildingType_Label");
    public static MyWebElement leaseStatus_label = myWebelement("jQuerySelector:label:contains('STATUS:')", "LeaseStatus_Label");
    public static MyWebElement startDate_label = myWebelement("jQuerySelector:label:contains('START DATE:')", "StartDate*_Label");
    public static MyWebElement endDate_label = myWebelement("jQuerySelector:label:contains('END DATE:')", "EndDate*_label");
    public static MyWebElement rentAmount_label = myWebelement("jQuerySelector:label:contains('RENT AMOUNT:')", "RentAmount*_Label");
    public static MyWebElement paymentPeriod_label = myWebelement("jQuerySelector:label:contains('PAYMENT PERIOD:')", "PaymentPeriod*_Label");
    public static MyWebElement securityAmount_label = myWebelement("jQuerySelector:label:contains('SECURITY AMOUNT:')", "SecurityAmount_Label");

    public static MyWebElement tenantEmail_label = myWebelement("jQuerySelector:label:contains('EMAIL:'):visible","TenantEmail_Label");
    public static MyWebElement tenantFirstName_label = myWebelement("jQuerySelector:label:contains('FIRST NAME:'):visible","TenantFirstName_Label");
    public static MyWebElement tenantLastName_label = myWebelement("jQuerySelector:label:contains('LAST NAME:'):visible","TenantLastName_Label");
    public static MyWebElement tenantDOB_label = myWebelement("jQuerySelector:label:contains('DOB:'):visible","TenantDOB_Label");
    public static MyWebElement tenantPhoneOne_label = myWebelement("jQuerySelector:label:contains('PHONE #:'):visible","TenantPhoneOne_Label");
    public static MyWebElement tenantState_label = myWebelement("jQuerySelector:label:contains('STATE OF ID:'):visible","TenantStateOfID_Label");
    public static MyWebElement tenantSSN_label = myWebelement("jQuerySelector:label:contains('LAST 4 DIGITS SSN:'):visible","TenantLast4SSN_Label");

    public static MyWebElement ownedBy_label = myWebelement("jQuerySelector:label:contains('OWNED BY:')", "OwnedBy_Label");
    public static MyWebElement managedBy_label = myWebelement("jQuerySelector:label:contains('MANAGED BY:')", "ManagedBy_label");
    public static MyWebElement propertyName_label = myWebelement("jQuerySelector:label:contains('PROPERTY NAME:')", "PropertyName_Label");
    public static MyWebElement propertyAddress1_label = myWebelement("jQuerySelector:label:contains('ADDRESS 1:')", "PropertyAddress1_Label");
    public static MyWebElement propertyCity_label = myWebelement("jQuerySelector:label:contains('CITY:')", "PropertyCity*_Label");
    public static MyWebElement propertyStateZip_label = myWebelement("jQuerySelector:label:contains('STATE/ZIP:')", "PropertyState/Zip_Label");

    public static MyWebElement unit_displayValue = myWebelement("jQuerySelector:label:contains('UNIT #:') + div", "Unit#_DisplayValue");
    public static MyWebElement floor_displayValue = myWebelement("jQuerySelector:label:contains('FLOOR #:') + div", "Floor_DisplayValue");
    public static MyWebElement buildingType_displayValue = myWebelement("jQuerySelector:label:contains('BUILDING TYPE:') + div", "BuildingType_DisplayValue");
    public static MyWebElement startDate_displayValue = myWebelement("jQuerySelector:label:contains('START DATE:') + div", "StartDate_DisplayValue");
    public static MyWebElement endDate_displayValue = myWebelement("jQuerySelector:label:contains('END DATE:') + div", "EndDate_DisplayValue");
    public static MyWebElement rentAmount_displayValue = myWebelement("jQuerySelector:label:contains('RENT AMOUNT:') + div", "RentAmount__DisplayValue");
    public static MyWebElement paymentPeriod_displayValue = myWebelement("jQuerySelector:label:contains('PAYMENT PERIOD:') + div", "PaymentPeriod_DisplayValue");
    public static MyWebElement securityAmount_displayValue = myWebelement("jQuerySelector:label:contains('SECURITY AMOUNT:') + div", "SecurityAmount_DisplayValue");

    public static MyWebElement leaseStatus_displayValue = myWebelement("jQuerySelector:label:contains('STATUS:') + div span", "LeaseStatus_DisplayValue");
    public static MyWebElement tenantEmail_displayValue = myWebelement("jQuerySelector:label:contains('EMAIL:') + div","TenantEmail*_DisplayValue");
    public static MyWebElement tenantTitle_displayValue = myWebelement("jQuerySelector:label:contains('TITLE:') + div","TenantTitle:_DisplayValue");
    public static MyWebElement tenantFirstName_displayValue = myWebelement("jQuerySelector:label:contains('FIRST NAME:') + div","TenantFirstName*_DisplayValue");
    public static MyWebElement tenantMiddleInitial_displayValue = myWebelement("jQuerySelector:label:contains('MIDDLE INITIAL:') + div","TenantMiddleInitial_DisplayValue");
    public static MyWebElement tenantLastName_displayValue = myWebelement("jQuerySelector:label:contains('LAST NAME:') + div","TenantLastName*_DisplayValue");
    public static MyWebElement tenantSuffix_displayValue = myWebelement("jQuerySelector:label:contains('SUFFIX:') + div","TenantSuffix_DisplayValue");
    public static MyWebElement tenantDOB_displayValue = myWebelement("jQuerySelector:label:contains('DOB:') + div","TenantDOB*_DisplayValue");
    public static MyWebElement tenantPhoneOne_displayValue = myWebelement("jQuerySelector:label:contains('PHONE #:') + div","TenantPhoneOne*_DisplayValue");
    public static MyWebElement tenantState_displayValue = myWebelement("jQuerySelector:label:contains('STATE OF ID:') + div","TenantStateOfID*_DisplayValue");
    public static MyWebElement tenantSSN_displayValue = myWebelement("jQuerySelector:label:contains('LAST 4 DIGITS SSN:') + div","TenantLast4SSN*_DisplayValue");
    public static MyWebElement tenantNonResident_displayValue = myWebelement("jQuerySelector:label:contains('NON-RESIDENT:') + div","TenantNonResident_DisplayValue");

    public static MyWebElement saveAddAnotherLease_btn =  myWebelement("jQuerySelector:button:contains('SAVE & ADD ANOTHER LEASE')", "SaveAddAnotherLease_Button");
    public static MyWebElement saveViewLease_btn =  myWebelement("jQuerySelector:button:contains('SAVE & VIEW LEASE')", "SaveViewLease_Button");
    public static MyWebElement propertyCity_displayValue = myWebelement("jQuerySelector:label:contains('CITY:') + div", "PropertyCity_DisplayValue");
    public static MyWebElement propertyStateZip_displayValue = myWebelement("jQuerySelector:label:contains('STATE/ZIP:') + div", "PropertyState_DisplayValue");

    // page title
    public static String pageTitle = "NEW LEASE - LEASE INFO - SAVE LEASE";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  all the label part in new lease - save lease page's property info table (add lease step3 )
    public static MyWebElement [] newLease_propertyInfoElements = {home_icon, propertyInfo_title, ownedBy_label, managedBy_label, propertyName_label,
            propertyAddress1_label, propertyAddress2_label,propertyCity_label, propertyStateZip_label, propertyTaxLot_label};
        //  all the label part in new lease -save lease page's unit lease info table (add lease step3)
    public static MyWebElement[] newLeaseInfoElements = {listLeaseInfo_icon, leaseInfo_tableTitle,  startDate_label, endDate_label, rentAmount_label,
            paymentPeriod_label, securityAmount_label, leaseStatus_label};
        //  all the label part in new lease -save lease page's tenant info table (add lease step3)
    public static MyWebElement[] newLease_tenantInfoElements = {userTenantInfo_icon, tenantTab_link1, tenantInfo_tableTitle, tenantEmail_label,
            tenantTitle_label, tenantFirstName_label, tenantMiddleInitial_label, tenantLastName_label, tenantSuffix_label, tenantDOB_label,
            tenantPhoneOne_label, tenantState_label, tenantSSN_label, tenantNonResident_label};
        //  all the display values in property info table in new lease - save lease page (add lease step3), data fetched from property detail
    public static MyWebElement[] newLease_propertyInfoDisplayValues = {ownedBy_displayValue, managedBy_displayValue, propertyName_displayValue, propertyAddress1_displayValue,
            propertyAddress2_displayValue, propertyCity_displayValue, propertyStateZip_displayValue, propertyTaxLot_displayValue};
        //  all the display values in unit lease table in new lease - save lease page (add lease step3), data fetched from add lease step1
    public static MyWebElement[] newLease_unitLeaseInfoDisplayValues = {unit_displayValue,floor_displayValue, buildingName_displayValue, buildingType_displayValue,
            startDate_displayValue, endDate_displayValue, rentAmount_displayValue, paymentPeriod_displayValue, securityAmount_displayValue, leaseStatus_displayValue};
        //  all the display values in tenant info table in new lease - save lease page (add lease step3), data fetched from add lease step2
    public static MyWebElement[] newLease_tenantInfoDisplayValues = {tenantEmail_displayValue, tenantTitle_displayValue, tenantFirstName_displayValue, tenantMiddleInitial_displayValue,
            tenantLastName_displayValue, tenantSuffix_displayValue, tenantDOB_displayValue, tenantPhoneOne_displayValue, tenantState_displayValue, tenantSSN_displayValue, tenantNonResident_displayValue};

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static NewLease_SaveLeasePage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new NewLease_SaveLeasePage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        saveLease_leaseInfo_title.verifyElementPresent();
        saveLease_leaseInfo_subTitle.verifyContains("Please confirm all information is correct. If there is an error, you can navigate back via the breadcrumbs at the top of this page.");
    }

}
