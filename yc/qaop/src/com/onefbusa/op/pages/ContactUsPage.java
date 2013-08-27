package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class ContactUsPage extends HomePage {
    // web elements in this page
    public static MyWebElement contactUs_mailto =  myWebelement("linkText:mail to OP ADMIN for inquire", "ContactUs_Mailto");

    // page title
    public static String pageTitle = "Contact Us";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static ContactUsPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new ContactUsPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        contactUs_mailto.verifyElementPresent();
    }

}
