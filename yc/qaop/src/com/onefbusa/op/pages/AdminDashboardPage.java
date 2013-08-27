package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class AdminDashboardPage extends OPBasePage {
    // web elements in this page
    public static MyWebElement adminDashboard_title =  myWebelement("jQuerySelector:h3:contains('DASHBOARD - PENDING APPROVALS')", "AdminDashboardPage_Title");

    public static MyWebElement briefcase_icon =  myWebelement("cssSelector:i.icon-briefcase", "BriefCase_Icon");
    public static MyWebElement home_icon =  myWebelement("cssSelector:i.icon-home", "Home_Icon");

    public static MyWebElement pendingAgent_tableTitle=myWebelement("jQuerySelector:p.title:contains('Account SignUp - Most Recent')","pendingAgent_tableTitle");
    public static MyWebElement tenantInfo_tableTitle=myWebelement("jQuerySelector:p.title:contains('Tenant Info Revisions - Most Recent')","tenantInfo_tableTitle");
    public static MyWebElement refundDisputes_tableTitle=myWebelement("jQuerySelector:p.title:contains('Refund Disputes')","refundDisputes_tableTitle");
    public static MyWebElement pendingPayables_tableTitle=myWebelement("jQuerySelector:p.title:contains('Pending Payables')","pendingPayables_tableTitle");

    public static MyWebElement navBar_dashboard=myWebelement("jQuerySelector:ul.nav a:contains('DASHBOARD')","navBar_dashboard");
    public static MyWebElement navBar_newAccount=myWebelement("jQuerySelector:ul.nav a:contains('NEW ACCOUNT')","navBar_newAccount");
    public static MyWebElement navBar_history=myWebelement("jQuerySelector:ul.nav a:contains('HISTORY')","navBar_history");
    public static MyWebElement navBar_leases=myWebelement("jQuerySelector:ul.nav a:contains('LEASES')","navBar_leases");
    public static MyWebElement navBar_account=myWebelement("jQuerySelector:ul.nav a:contains('ACCOUNT')","navBar_account");  

    public static MyWebElement topPending_Agent=myWebelement("jQuerySelector:div.inner-content tr td","topPending_Agent",0);
    public static MyWebElement topPending_Agent_viewBtn=myWebelement("jQuerySelector:table.table-hover tr td + td button","topPending_Agent",0);

    // page title
    public static String pageTitle = "DASHBOARD - PENDING APPROVALS";

    // define some web elements group used in test cases, which will make test case as simple as possible
        // original displayed elements in the page
    public static MyWebElement [] originalDisplayedElements = { landingPage_title,pendingAgent_tableTitle,tenantInfo_tableTitle,refundDisputes_tableTitle,pendingPayables_tableTitle,
    	signOut_link,navBar_dashboard,navBar_newAccount,navBar_history,navBar_leases,navBar_account,aboutUs_footlink, contactUs_footlink, faq_footlink, signUp_footlink, privacy_footlink,terms_footlink, fcra__footlink } ;

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static AdminDashboardPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new AdminDashboardPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () { // no need arg: adminName here because the admin page title always "DASHBOARD - PENDING APPROVALS"
        verifyPageTitle(pageTitle);
        adminDashboard_title.verifyElementPresent();
    }

}
