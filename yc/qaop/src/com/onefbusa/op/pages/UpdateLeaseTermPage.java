package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class UpdateLeaseTermPage extends LeaseDetailPage {
    // web elements in this page
    public static MyWebElement updateTerm_title =  myWebelement("jQuerySelector:h3:contains('UPDATE LEASE TERM - ')", "UpdateLeaseTermPage_Title");

    public static MyWebElement concludeLease_btn =  myWebelement("jQuerySelector:button:contains('CONCLUDE LEASE')", "ConcludeLease_Button");
    public static MyWebElement setMonthToMonth_btn =  myWebelement("jQuerySelector:button:contains('SET MONTH-TO-MONTH')", "SetMonthToMonth_Button");
    public static MyWebElement renewLease_btn =  myWebelement("jQuerySelector:button:contains('RENEW LEASE')", "RenewLease_Button");

    public static MyWebElement extendLeaseTerm_tableTitle = myWebelement("cssSelector:p.title", "ExtendLeaseTerm_TableTitle");
    public static MyWebElement currentTerm_label = myWebelement("jQuerySelector:label:contains('CURRENT TERM:')","CurrentTerm_Label");
    public static MyWebElement termStartDate_label = myWebelement("jQuerySelector:label:contains('CURRENT TERM') ~ div:contains('START')","TermStartDate_Label");
    public static MyWebElement termEndDate_label = myWebelement("jQuerySelector:label:contains('CURRENT TERM') ~ div:contains('END')","TermEndDate_Label");
    public static MyWebElement termNewEndDate_label = myWebelement("jQuerySelector:label[for=newEndDate]","TermNewEndDate*_Label");
    public static MyWebElement termNewEndDate_textBox = myWebelement("newEndDate","TermNewEndDate_TextBox");
    public static MyWebElement termNewEndDatePicker_img = myWebelement("jQuerySelector:input#newEndDate + img","TermNewEndDatePicker_Image");
    public static MyWebElement termNewRentAmount_label = myWebelement("jQuerySelector:label[for=inputNewRentAmount]","TermNewRentAmount*_Label");
    public static MyWebElement termNewRentAmount_$ = myWebelement("cssSelector:span.add-on","TermNewRentAmount_$");
    public static MyWebElement termNewRentAmount_textBox = myWebelement("inputNewRentAmount","TermNewRentAmount_TextBox");
    public static MyWebElement saveUpdateTerm_btn = myWebelement("save","SaveUpdateTerm_Button");

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  the the label part listed in extend lease term page table box
    public static MyWebElement[] extendLeaseTerm_tableElements = {currentTerm_label, termStartDate_label, termEndDate_label, termNewEndDate_label,
            termNewEndDate_textBox, termNewEndDatePicker_img, termNewRentAmount_label, termNewRentAmount_$, termNewRentAmount_textBox, saveUpdateTerm_btn};

    // page title
    public static String pageTitle = "UPDATE LEASE TERM";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static UpdateLeaseTermPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new UpdateLeaseTermPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready (String leaseName) {
        verifyPageTitle(pageTitle);
        updateTerm_title.verifyContainsIgnoreSpaces("UPDATE LEASE TERM -" + leaseName);
    }

}
