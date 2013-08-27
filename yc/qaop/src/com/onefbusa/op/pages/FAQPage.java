package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class FAQPage extends HomePage {
    // web element in this page
    public static MyWebElement faq_title =  myWebelement("jQuerySelector:h2:contains('FAQ')", "FAQPage_Title");

    // page title
    public static String pageTitle = "FAQ Page";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static FAQPage load() {
        //verifyPageTitle(pageTitle);
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new FAQPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        faq_title.verifyElementPresent();
    }

}
