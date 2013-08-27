package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class TermsConditionsPage extends HomePage {
    // web elements in this page
    public static MyWebElement terms_title =  myWebelement("jQuerySelector:h2:contains('TERMS & CONDITIONS')", "TermsConditionsPage_Title");

    // page title
    public static String pageTitle = "TERMS & CONDITIONS";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static TermsConditionsPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new TermsConditionsPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        terms_title.verifyElementPresent();
    }

}
