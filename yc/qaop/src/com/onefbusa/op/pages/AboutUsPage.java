package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class AboutUsPage extends HomePage {
    // web elements in this page
    public static MyWebElement aboutUs_title =  myWebelement("jQuerySelector:h2:contains('ABOUT')", "AboutUsPage_Title");

    // page title
    public static String pageTitle = "About Us";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static AboutUsPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new AboutUsPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        aboutUs_title.verifyElementPresent();
    }

}
