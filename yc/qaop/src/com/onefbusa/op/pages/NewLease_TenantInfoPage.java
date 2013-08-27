package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class NewLease_TenantInfoPage extends NewLease_LeaseInfoPage {
    // web elements in this page
    public static MyWebElement newLease_tenantInfo_title =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - TENANT INFO')", "NewLease_TenantInfoPage_Title");
    public static MyWebElement newLease_tenantInfo_subTitle =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - TENANT INFO') + P", "NewLease_TenantInfoPage_subTitle");
    public static MyWebElement newLease_tenantInfo_tipsTitle =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - TENANT INFO') ~ h5", "NewLease_TenantInfoPage_Title");

    public static MyWebElement userTenantInfo_icon =  myWebelement("jQuerySelector:i.icon-user:visible", "UserTenantInfo_Icon");
    public static MyWebElement tenantInfo_tableTitle =  myWebelement("jQuerySelector:div.row-fluid p:contains('TENANT INFO')", "TenantInfo_TableTitle");
    public static MyWebElement addTenant_icon =  myWebelement("jQuerySelector:i.icon-plus:visible", "AddTenant_Icon");
    public static MyWebElement addAnotherTenant_btn =  myWebelement("addTenant", "AddAnotherTenant_Button");
    public static MyWebElement removeTenant_btn = myWebelement("removeTenant", "RemoveTenant_Button");
    public static MyWebElement tenantTab_link1 = myWebelement("jQuerySelector:ul.tenantTabs a:contains('1')", "TenantTab_Link_1st");
    public static MyWebElement tenantTab_link2 = myWebelement("jQuerySelector:ul.tenantTabs a:contains('2')", "TenantTab_Link_2nd");
    public static MyWebElement tenantEmail_label = myWebelement("jQuerySelector:label:contains('EMAIL: *'):visible","TenantEmail*_Label");
    public static MyWebElement tenantTitle_label = myWebelement("jQuerySelector:label:contains('TITLE:'):visible","TenantTitle:_Label");
    public static MyWebElement tenantFirstName_label = myWebelement("jQuerySelector:label:contains('FIRST NAME: *'):visible","TenantFirstName*_Label");
    public static MyWebElement tenantMiddleInitial_label = myWebelement("jQuerySelector:label:contains('MIDDLE INITIAL:'):visible","TenantMiddleInitial_Label");
    public static MyWebElement tenantLastName_label = myWebelement("jQuerySelector:label:contains('LAST NAME: *'):visible","TenantLastName*_Label");
    public static MyWebElement tenantSuffix_label = myWebelement("jQuerySelector:label:contains('SUFFIX:'):visible","TenantSuffix_Label");
    public static MyWebElement tenantDOB_label = myWebelement("jQuerySelector:label:contains('DOB: *'):visible","TenantDOB*_Label");
    public static MyWebElement tenantPhoneOne_label = myWebelement("jQuerySelector:label:contains('PHONE #: *'):visible","TenantPhoneOne*_Label");
    public static MyWebElement tenantState_label = myWebelement("jQuerySelector:label:contains('STATE OF ID: *'):visible","TenantStateOfID*_Label");
    public static MyWebElement tenantSSN_label = myWebelement("jQuerySelector:label:contains('LAST 4 DIGITS SSN: *'):visible","TenantLast4SSN*_Label");
    public static MyWebElement tenantNonResident_label = myWebelement("jQuerySelector:label:contains('NON-RESIDENT:'):visible","TenantNonResident_Label");

    public static MyWebElement tenantEmail_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=email]", "TenantEmailName_TextBox");
    public static MyWebElement tenantTitle_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=title]", "TenantTitleName_TextBox");
    public static MyWebElement tenantFirstName_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=firstName]", "TenantFirstName_TextBox");
    public static MyWebElement tenantMiddleInitial_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=middleInitial]", "TenantMiddleInitial_TextBox");
    public static MyWebElement tenantLastName_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=lastName]", "TenantLastName_TextBox");
    public static MyWebElement tenantSuffix_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=suffix]", "TenantSuffix_TextBox");
    public static MyWebElement tenantDOB_tableBox = myWebelement("cssSelector:input[name^=tenantForms][name$=dateOfBirth]", "TenantDOB_TextBox");
    public static MyWebElement tenantDOB_calendarImage = myWebelement("cssSelector:img.ui-datepicker-trigger","TenantDOB_CalendarImage");
    public static MyWebElement tenantPhoneOne_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=phoneOne]", "TenantPhoneOne_TextBox");
    public static MyWebElement tenantState_selectBox = myWebelement("cssSelector:select[name^=tenantForms][name$=stateOfId]", "TenantStateOfID_SelectBox");
    public static MyWebElement tenantSSN_textBox = myWebelement("cssSelector:input[name^=tenantForms][name$=lastFourDigitsSSN]", "TenantSSN_TextBox");
    public static MyWebElement tenantNonResident_checkBox = myWebelement("jQuerySelector:label.checkbox:contains('Not a U.S. resident'):visible", "TenantNonResident_CheckBox");

    public static MyWebElement saveContinue_btn =  myWebelement("cssSelector:button[type=submit]", "SaveContinue_Button");

    // page title
    public static String pageTitle = "NEW LEASE - TENANT INFO";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  all the label part in tenant table in add lease step2 page
    public static MyWebElement[] newLease_tenantInfoElements = {userTenantInfo_icon, addTenant_icon, saveContinue_btn, addAnotherTenant_btn,
            tenantTab_link1, tenantInfo_tableTitle, tenantEmail_label, tenantTitle_label, tenantFirstName_label, tenantMiddleInitial_label, tenantLastName_label,
            tenantSuffix_label, tenantDOB_label, tenantDOB_calendarImage, tenantPhoneOne_label, tenantState_label, tenantSSN_label, tenantNonResident_label, removeTenant_btn};
        //  all the text boxes,select boxes, data picker, in add lease step2 page's tenant table
    public static MyWebElement[] newLease_tenantInfoEditableObjects = {tenantEmail_textBox, tenantTitle_textBox, tenantFirstName_textBox,
            tenantMiddleInitial_textBox, tenantLastName_textBox, tenantSuffix_textBox, tenantDOB_tableBox, tenantPhoneOne_textBox, tenantState_selectBox,
            tenantSSN_textBox, tenantNonResident_checkBox};

    // methods, used to add tenant into new lease , submit all the tenants related to this lease in add lease step2 page
    public static void AddNewLease_TenantInfo(String... str) throws Exception {
        NewLease_TenantInfoPage.tenantEmail_textBox.input(str[0]);
        NewLease_TenantInfoPage.tenantTitle_textBox.click();
        if (!verifyAlertPresent()) {
            NewLease_TenantInfoPage.tenantTitle_textBox.input(str[1]);
            NewLease_TenantInfoPage.tenantFirstName_textBox.input(str[2]);
            NewLease_TenantInfoPage.tenantMiddleInitial_textBox.input(str[3]);
            NewLease_TenantInfoPage.tenantLastName_textBox.input(str[4]);
            NewLease_TenantInfoPage.tenantSuffix_textBox.input(str[5]);
            NewLease_TenantInfoPage.tenantDOB_tableBox.input(str[6]);
    // no need use data picker here, since the text box allows user to inputs directly..
//            NewLease_TenantInfoPage.selectYear_selectBox.selectByVisibleText("1989");
//            NewLease_TenantInfoPage.selectMonth_selectBox.selectByVisibleText("Jul");
//            NewLease_TenantInfoPage.selectFirstDay_tableBox.click();
//            Thread.sleep(500);
            NewLease_TenantInfoPage.tenantPhoneOne_textBox.input(str[7]);
            NewLease_TenantInfoPage.tenantState_selectBox.selectByVisibleText(str[8]);
            NewLease_TenantInfoPage.tenantSSN_textBox.input(str[9]);
            if (str[10].equals("YES"))  {
                NewLease_TenantInfoPage.tenantNonResident_checkBox.check();
            }
        } else {
            print(" The Tenant has already been in database..");
            alertAccept();
        }
        NewLease_TenantInfoPage.saveContinue_btn.click();
        Thread.sleep(1500);   // waiting for page switch
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static NewLease_TenantInfoPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new NewLease_TenantInfoPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        newLease_tenantInfo_title.verifyElementPresent();
        newLease_tenantInfo_subTitle.verifyContains("Please enter the tenant's information.");
        newLease_tenantInfo_tipsTitle.verifyContains("Tenant will receive an email notifying them of signup. They will be given the opportunity to revise any information entered here.");
    }

}