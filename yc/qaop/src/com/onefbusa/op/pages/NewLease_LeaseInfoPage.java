package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class NewLease_LeaseInfoPage extends PropertyDetailPage {
    // web elements in this page
    public static MyWebElement newLease_leaseInfo_title =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - LEASE INFO -')", "NewLease_LeaseInfoPage_Title");
    public static MyWebElement newLease_leaseInfo_subTitle =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - LEASE INFO -') + p", "NewLease_LeaseInfoPage_SubTitle");

    public static MyWebElement listLeaseInfo_icon =  myWebelement("jQuerySelector:i.icon-list:visible", "ListLeaseInfo_Icon");
    public static MyWebElement saveContinue_btn =  myWebelement("jQuerySelector:button:contains('SAVE & CONTINUE')", "SaveContinue_Button");
    public static MyWebElement leaseInfo_tableTitle =  myWebelement("jQuerySelector:div.row-fluid p:contains('LEASE INFO')", "LeaseInfo_TableTitle");
    public static MyWebElement startDate_label = myWebelement("jQuerySelector:label:contains('START DATE: *')", "StartDate*_Label");
    public static MyWebElement endDate_label = myWebelement("jQuerySelector:label:contains('END DATE: *')", "EndDate*_label");
    public static MyWebElement rentAmount_label = myWebelement("jQuerySelector:label:contains('RENT AMOUNT: *')", "RentAmount*_Label");
    public static MyWebElement paymentPeriod_label = myWebelement("jQuerySelector:label:contains('PAYMENT PERIOD: *')", "PaymentPeriod*_Label");
    public static MyWebElement securityAmount_label = myWebelement("jQuerySelector:label:contains('SECURITY AMOUNT: *')", "SecurityAmount_Label");
//    bankName_label, interest_label, adminFee_label, paymentType_label, returnProcess_label can IndexOutOfBoundsException extended from PropertyCreatePage

    public static MyWebElement startDate_textBox =  myWebelement("leaseForm.startDate", "StartDate_TextBox");
    public static MyWebElement endDate_textBox =  myWebelement("leaseForm.endDate", "EndDate_TextBox");
    public static MyWebElement rentAmount_textBox =  myWebelement("leaseForm.rent", "RentAmount_TextBox");
    public static MyWebElement paymentPeriod_selectBox =  myWebelement("leaseForm.paymentPeriodTypeId", "PaymentPeriod_SelectBox");
    public static MyWebElement securityAmount_textBox =  myWebelement("leaseForm.securityAmount", "SecurityAmount_TextBox");
    public static MyWebElement datePicker_box = myWebelement("ui-datepicker-div","DatePicker_TableBox");
    public static MyWebElement previousDate_btn = myWebelement("cssSelector:a.ui-datepicker-prev","PreviousDate_Button");
    public static MyWebElement nextDate_btn = myWebelement("cssSelector:a.ui-datepicker-next","NextDate_Button");
    public static MyWebElement selectMonth_selectBox = myWebelement("cssSelector:select.ui-datepicker-month","SelectMonth_SelectBox");
    public static MyWebElement selectYear_selectBox = myWebelement("cssSelector:select.ui-datepicker-year","SelectYear_SelectBox");
    public static MyWebElement selectToday_tableBox = myWebelement("jQuerySelector:table.ui-datepicker-calendar td.ui-datepicker-today","SelectToday_TableBox");
    public static MyWebElement selectFirstDay_tableBox = myWebelement("jQuerySelector:table.ui-datepicker-calendar td[data-event=click]","SelectFirstDay_TableBox");

    public static MyWebElement startDate_calendarImage = myWebelement("cssSelector:img.ui-datepicker-trigger","StartDate_CalendarImage",0);
    public static MyWebElement endDate_calendarImage = myWebelement("cssSelector:img.ui-datepicker-trigger","EndDate_CalendarImage",1);
    public static MyWebElement bankName_label = myWebelement("jQuerySelector:label:contains('BANK NAME:')", "BankName_Label");
    public static MyWebElement interest_label = myWebelement("jQuerySelector:label:contains('INTEREST:')", "Interest_Label");
    public static MyWebElement adminFee_label = myWebelement("jQuerySelector:label:contains('ADMIN FEE:')", "adminFee_Label");
    public static MyWebElement paymentType_label = myWebelement("jQuerySelector:label:contains('PAYMENT TYPE:')", "PaymentType_Label");

    public static MyWebElement bankName_displayValue = myWebelement("jQuerySelector:label:contains('BANK NAME:') + div", "BankName_DisplayValue");
    public static MyWebElement interest_displayValue = myWebelement("jQuerySelector:label:contains('INTEREST:') + div", "Interest_DisplayValue");
    public static MyWebElement adminFee_displayValue = myWebelement("jQuerySelector:label:contains('ADMIN FEE:') + div", "AdminFee_DisplayValue");
    public static MyWebElement paymentType_displayValue = myWebelement("jQuerySelector:label:contains('PAYMENT TYPE:') + div", "PaymentType_DisplayValue");

    // page title
    public static String pageTitle = "NEW LEASE - LEASE INFO";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  all the label part originally displayed in new lease info table (add new lease step1 )
    public static MyWebElement[] newLeaseInfoElements = {listLeaseInfo_icon, leaseInfo_tableTitle, saveContinue_btn,startDate_label, startDate_calendarImage, endDate_label,
            endDate_calendarImage, rentAmount_label, paymentPeriod_label, securityAmount_label, bankName_label, interest_label, adminFee_label,
            paymentType_label,returnProcess_label, saveContinue_btn};
        //  all the text boxes,select boxes,data picker elements in new lease info table (add new lease step1 )
    public static MyWebElement[] newLeaseInfoEditableObjects = {startDate_textBox, endDate_textBox, rentAmount_textBox, paymentPeriod_selectBox, securityAmount_textBox};
        //  all the display values about the lease info(bank,interest,admin fee,etc.) in lease info table, which is fetched from database
    public static MyWebElement[] newLeaseInfo_displayValues = {bankName_displayValue, interest_displayValue, adminFee_displayValue, paymentType_displayValue, returnProcess_displayValue};

    // methods, used to add new lease in step1, submit all the lease related info
    public static void AddNewLease_LeaseInfo(String... str) throws Exception {
        if (str[0].equals("TODAY")) {
            NewLease_LeaseInfoPage.startDate_calendarImage.click();
            NewLease_LeaseInfoPage.selectToday_tableBox.click();
            Thread.sleep(500);  // wait for the datePicker
        } else {
            NewLease_LeaseInfoPage.startDate_textBox.input(str[0]);
        }
        NewLease_LeaseInfoPage.endDate_textBox.input(str[1]);
        NewLease_LeaseInfoPage.rentAmount_textBox.input(str[2]);
        NewLease_LeaseInfoPage.paymentPeriod_selectBox.selectByVisibleText(str[3]);
        NewLease_LeaseInfoPage.securityAmount_textBox.input(str[4]);
        NewLease_LeaseInfoPage.saveContinue_btn.click();
        Thread.sleep(1500);   // waiting for page switch
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static NewLease_LeaseInfoPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new NewLease_LeaseInfoPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready (String newLeaseName) {
        verifyPageTitle(pageTitle);
        newLease_leaseInfo_title =  myWebelement("jQuerySelector:h3:contains('NEW LEASE - LEASE INFO - " + newLeaseName +"')", "NewLease_LeaseInfoPage_Title");
        newLease_leaseInfo_title.verifyElementPresent();
        newLease_leaseInfo_subTitle.verifyContains("Please enter the lease information.");
    }

}
