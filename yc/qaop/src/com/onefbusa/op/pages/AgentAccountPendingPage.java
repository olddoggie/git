package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class AgentAccountPendingPage extends HomePage {
    // web elements in this page
    public static MyWebElement agentAccountPending_title = myWebelement("jQuerySelector:h3:contains('ACCOUNT PENDING')", "AgentAccountPendingPage_Title");

    // page title
    public static String pageTitle = "ACCOUNT PENDING";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static AgentAccountPendingPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new AgentAccountPendingPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        agentAccountPending_title.verifyElementPresent();
        verifyPageTitle(pageTitle);
    }

}
