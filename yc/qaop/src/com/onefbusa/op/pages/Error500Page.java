package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class Error500Page extends OPBasePage {
    // web element in this page
    public static MyWebElement error500_title =  myWebelement("jQuerySelector:h3:contains('500 Internal Server Error')", "Error500Page_title");
    public static MyWebElement goHomePage_link =  myWebelement("linkText:home page", "GoBackToHomePage_link");

    // page title
    public static String pageTitle = "Error 500 Page";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static Error500Page load() {
        //verifyPageTitle(pageTitle);
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new Error500Page();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready() {
        verifyPageTitle(pageTitle);
        error500_title.verifyElementPresent();
        verifyPageContains("An error has occurred. Please try again later.");
    }

}
