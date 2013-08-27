package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class ForgetPasswordPage extends HomePage {
    // web element in this page
    public static MyWebElement forgetPassword_title =  myWebelement("jQuerySelector:h2:contains('FORGOT PASSWORD?')", "ForgetPasswordPage_Title");

    // page title
    public static String pageTitle = "Forgot Password";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static ForgetPasswordPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new ForgetPasswordPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        forgetPassword_title.verifyElementPresent();
    }

}
