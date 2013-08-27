package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class Error404Page extends OPBasePage {
    // web element in this page
    public static MyWebElement error404_title =  myWebelement("jQuerySelector:h3:contains('404 Not Found')", "Error404Page_title");
    public static MyWebElement goHomePage_link =  myWebelement("linkText:home page", "GoBackToHomePage_link");

    // page title
    public static String pageTitle = "Error 404 Page";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static Error404Page load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new Error404Page();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready() {
        waitUntilPageReady();
        verifyPageTitle(pageTitle);
        error404_title.verifyElementPresent();
        verifyPageContains("The requested URL is not found on this server.");
    }

}
