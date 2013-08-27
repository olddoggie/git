package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class FCRAPage extends HomePage {
    // web element in this page
    public static MyWebElement fcra_title =  myWebelement("jQuerySelector:h2:contains('FCRA')", "FCRAPage_Title");

    // page title
    public static String pageTitle = "FCRA";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static FCRAPage load() {
        //verifyPageTitle(pageTitle);
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new FCRAPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        fcra_title.verifyElementPresent();
    }

}
