package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class PrivacyPolicyPage extends HomePage {
    // web elements in this page
    public static MyWebElement privacy_title =  myWebelement("jQuerySelector:h2:contains('PRIVACY POLICY')", "PrivacyPolicyPage_Title");

    // page title
    public static String pageTitle = "Privacy Policy";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static PrivacyPolicyPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new PrivacyPolicyPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        privacy_title.verifyElementPresent();
    }

}
