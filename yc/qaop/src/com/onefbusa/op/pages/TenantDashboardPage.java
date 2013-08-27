package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class TenantDashboardPage extends OPBasePage {
    // web elements in this page
    public static MyWebElement tenantWelcome_title =  myWebelement("jQuerySelector:h3:contains('Welcome back')", "TenantDashboardPage_Title");
    public static MyWebElement briefcase_icon =  myWebelement("cssSelector:i.icon-briefcase", "BriefCase_Icon");
    public static MyWebElement home_icon =  myWebelement("cssSelector:i.icon-home", "Home_Icon");

    // page title
    public static String pageTitle = "Dashboard - Tenant";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static TenantDashboardPage load() {
        //verifyPageTitle(pageTitle);
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new TenantDashboardPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready (String tenantName) {
        verifyPageTitle(pageTitle);
        tenantWelcome_title =  myWebelement("jQuerySelector:h3:contains('Welcome back, " + tenantName +"!')", "TenantDashboardPage_Title");
        tenantWelcome_title.verifyElementPresent();
    }

}
