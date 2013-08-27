package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class TenantVerificationPage extends HomePage {
    // web elements in this page
    public static MyWebElement tenantVerification_title =  myWebelement("jQuerySelector:h2:contains('TENANT VERIFICATION')", "TenantVerificationPage_Title");

    // page title
    public static String pageTitle = "Tenant Verification";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static TenantVerificationPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new TenantVerificationPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        tenantVerification_title.verifyElementPresent();
    }

}
