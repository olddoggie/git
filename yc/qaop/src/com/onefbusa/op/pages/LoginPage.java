package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class LoginPage extends HomePage {
    // web elements in this page
    public static MyWebElement loginEmail_label =  myWebelement("jQuerySelector:label:contains('Email:')", "LoginEmail_Label");
    public static MyWebElement loginPassword_label =  myWebelement("jQuerySelector:label:contains('Password:')", "LoginPassword_Label");
    public static MyWebElement forgetPassword_link = myWebelement("linkText:FORGOT PASSWORD","ForgetPassword_link");
    public static MyWebElement login_btn =  myWebelement("jQuerySelector:button:contains('LOGIN')", "Login_Button");

    // page title
    public static String pageTitle = "Login";

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static LoginPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new LoginPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        verifyAllElementsPresent(new LoginPage()); // every element in this page is available to view when page initialized
//      verifyElementPresent(loginEmail_label);
//      verifyElementPresent(loginPassword_label);
//      verifyElementPresent(login_btn);
//      verifyElementPresent(forgetPassword_link);
    }
}
