package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class SearchPage extends HomePage {
    // web elements in this page
    public static MyWebElement search_title =  myWebelement("jQuerySelector:h2:contains('PROPERTY SEARCH')", "SearchPage_Title");

    // page title
    public static String pageTitle = "Search Page";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static SearchPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new SearchPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        search_title.verifyElementPresent();
    }

}
