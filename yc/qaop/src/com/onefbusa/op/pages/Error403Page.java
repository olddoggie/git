package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class Error403Page extends OPBasePage {
    // web elements in this page
    public static MyWebElement error403_title =  myWebelement("jQuerySelector:h3:contains('403 Forbidden')", "Error403Page_title");
    public static MyWebElement goHomePage_link =  myWebelement("linkText:home page", "GoBackToHomePage_link");

    // page title
    public static String pageTitle = "Error 403";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static Error403Page load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new Error403Page();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready() {
        verifyPageTitle(pageTitle);
        error403_title.verifyElementPresent();
        verifyPageContains("You don't have permission to access the page.");
    }

}
