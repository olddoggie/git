package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class AgentDashboardPage extends OPBasePage {
    // web elements in this page
    public static MyWebElement agentWelcome_title =  myWebelement("jQuerySelector:h3:contains('Welcome back')", "AgentDashboardPage_Title");
    public static MyWebElement agentWelcome_subTitle =  myWebelement("cssSelector:span.sub-title", "AgentDashboardPage_SubTitle");

    public static MyWebElement briefcase_icon =  myWebelement("cssSelector:i.icon-briefcase", "BriefCase_Icon");
    public static MyWebElement home_icon =  myWebelement("cssSelector:i.icon-home", "Home_Icon");
    public static MyWebElement leaseEndingSoon_tableTitle = myWebelement("cssSelector:p.title", "LeaseEndingSoon_TableTitle",0);
    public static MyWebElement properties_tableTitle = myWebelement("cssSelector:p.title", "Properties_TableTitle",1);

    public static MyWebElement leaseEndingSoon_innerItem_1 =  myWebelement("jQuerySelector:div.inner-content tbody:eq(0) tr:eq(0) td", "LeaseEndingSoon_InnerItem_1st");
    public static MyWebElement leaseEndingSoon_endingDate_1 = myWebelement("jQuerySelector:div.inner-content tbody:eq(0) tr:eq(0) td:eq(1):visible");
    public static MyWebElement properties_innerItem_1 = myWebelement("jQuerySelector:div.inner-content tbody", "Properties_InnerItem_1st",1,"cssSelector:tr td");
    public static MyWebElement properties_addNewLeaseBtn_1 = myWebelement("jQuerySelector:button.addNewLease");
//    public static  String[] sub_elements = {"cssSelector:tr","cssSelector:td"};
//    public static int[] sub_indexes = {1,1} ; // tr = 1 means row 2
//    public static MyWebElement properties_addNewLeaseBtn_2 = myWebelement("cssSelector:div.inner-content tbody", "Properties_AddNewLeaseBtn_2nd",1,sub_elements,sub_indexes);

    public static MyWebElement leaseEndingSoon_viewXofY = myWebelement("cssSelector:div.link-outer", "LeaseEndingSoon_View_xOf_Y",0);
    public static MyWebElement properties_viewXofY = myWebelement("cssSelector:div.link-outer", "Properties_View_xOf_Y",1);
    public static MyWebElement leaseEndingSoon_viewAll_link = myWebelement("viewAllLease", "LeaseEndingSoon_ViewAll_Link");
    public static MyWebElement properties_viewAll_link = myWebelement("viewAllProperty", "Properties_ViewAll_Link");

    public static MyWebElement addNewProperty_icon = myWebelement("jQuerySelector:i.icon-plus", "AddNewProperty_Icon_+");
    public static MyWebElement addNewProperty_btn = myWebelement("addNewProperty", "AddNewProperty_Button");

    public static MyWebElement dashboard_tab = myWebelement("linkText:DASHBOARD","Dashboard_Tab");
    public static MyWebElement leases_tab = myWebelement("linkText:LEASES","Leases_Tab");
    public static MyWebElement properties_tab = myWebelement("linkText:PROPERTIES","Properties_Tab");
    public static MyWebElement yourProfile_tab = myWebelement("linkText:YOUR PROFILE","YourProfile_Tab");
    public static MyWebElement searchTenants_tab = myWebelement("linkText:SEARCH TENANTS","SearchTenants_Tab");

    // page title
    public static String pageTitle = "Dashboard - Managing Agent";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  original Displayed Elements
    public static MyWebElement [] originalDisplayedElements = { landingPage_title, agentWelcome_title, agentWelcome_subTitle, briefcase_icon,
            leaseEndingSoon_tableTitle, home_icon, properties_tableTitle, addNewProperty_icon, addNewProperty_btn, dashboard_tab, leases_tab,
            properties_tab, yourProfile_tab, searchTenants_tab, aboutUs_footlink, contactUs_footlink, faq_footlink, signUp_footlink, privacy_footlink,
            terms_footlink, fcra__footlink } ;
        //  elements in lease ending soon table
    public static MyWebElement [] leaseEndingSoonTableElements = { briefcase_icon, leaseEndingSoon_tableTitle, leaseEndingSoon_innerItem_1,
            leaseEndingSoon_endingDate_1, leaseEndingSoon_viewXofY, leaseEndingSoon_viewAll_link } ;
        //  elements in properties table
    public static MyWebElement [] propertiesTableElements = { home_icon, properties_tableTitle, properties_innerItem_1, properties_addNewLeaseBtn_1,
            properties_viewXofY, properties_viewAll_link } ;

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static AgentDashboardPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new AgentDashboardPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready (String agentName) {
        verifyPageTitle(pageTitle);
        agentWelcome_title.verifyContains("Welcome back, " + agentName + "!");
        agentWelcome_subTitle.verifyContainsIgnoreSpaces("- Resolve Your PENDING Security Deposit Refunds");
    }

}
