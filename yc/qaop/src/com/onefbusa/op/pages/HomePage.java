package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class HomePage extends OPBasePage {
    // web elements in this page
    public static MyWebElement welcome_title = myWebelement("jQuerySelector:h1:contains('Welcome to OpenProperty')", "WelcomePage_Title");
    public static MyWebElement propertySearch_title = myWebelement("jQuerySelector:h2:contains('Property Search')", "PropertySearch_Title");
    public static MyWebElement tenantSearch_title = myWebelement("jQuerySelector:h2:contains('Tenant Search')", "TenantSearch_Title");

    public static MyWebElement requestAccount_link = myWebelement("linkText:Request Account", "RequestAccount_Link");
    public static MyWebElement requestAccount_btn = myWebelement("linkText:REQUEST ACCOUNT", "RequestAccount_Button");
    public static MyWebElement propertySearch_btn = myWebelement("jQuerySelector:a:contains('Property Search')", "PropertySearch_Button");
    public static MyWebElement tenantSearch_btn = myWebelement("jQuerySelector:a:contains('Tenant Search')", "TenantSearch_Button");
    public static MyWebElement pendingAccount_link = myWebelement("linkText:pending account here", "PendingAccount_Link");
    public static MyWebElement clickHere_pendingAccount_link = myWebelement("linkText:Click here", "ClickHere_PendingAccount_Link");

    public static MyWebElement login_username = myWebelement("name:j_username", "Login_Username_TextBox");
    public static MyWebElement login_password = myWebelement("name:j_password", "Login_Password_TextBox");
    public static MyWebElement login_btn = myWebelement("cssSelector:input[value=LOGIN]", "Login_Button");
    public static MyWebElement login_table = myWebelement("id:loginDiv", "Login_Table");
    public static MyWebElement loginFail_table = myWebelement("id:loginFailDiv", "LoginFail_Table");
    public static MyWebElement forgetPassword_link = myWebelement("linkText:FORGOT PASSWORD?","ForgetPassword_link");
    public static MyWebElement tryLoginAgain_link = myWebelement("linkText:Try to login again?","TryToLoginAgain_link");
    public static MyWebElement forgotYourPassword_link = myWebelement("linkText:Forgot your password?","ForgotYourPassword_link");

    public static MyWebElement about_tab = myWebelement("linkText:ABOUT","About_Tab");
    public static MyWebElement contact_tab = myWebelement("linkText:CONTACT","Contact_Tab");
    public static MyWebElement faq_tab = myWebelement("linkText:FAQ","FAQ_Tab");
    public static MyWebElement search_tab = myWebelement("linkText:SEARCH","Search_Tab");

    // page title
    public static String pageTitle = "Welcome to OpenProperty";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  original displayed elements in this page
    public static MyWebElement [] originalDisplayedElements = { landingPage_title, welcome_title, propertySearch_title, tenantSearch_title,requestAccount_link,
            requestAccount_btn,pendingAccount_link, pendingAccount_link, clickHere_pendingAccount_link, login_table, login_username,login_password,
            login_btn,forgetPassword_link, propertySearch_btn,tenantSearch_btn, about_tab,contact_tab,faq_tab,search_tab,aboutUs_footlink,
            contactUs_footlink,faq_footlink, signUp_footlink, privacy_footlink, terms_footlink, fcra__footlink } ;

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static HomePage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new HomePage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        landingPage_title.verifyElementPresent();
        welcome_title.verifyElementPresent();
    }

}