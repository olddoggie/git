package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class ShowAgentAccountSignUpPage extends OPBasePage {
	// web elements in this page
    public static MyWebElement accountSignUp_title = myWebelement("jQuerySelector:h3:contains('ACCOUNT SIGNUP')", "AccountSignUpPage_Title");
    public static MyWebElement pendingAgent_dismiss = myWebelement("jQuerySelector:button#dismiss", "pendingAgent_dismiss");
    public static MyWebElement pendingAgent_approve = myWebelement("jQuerySelector:button#approve", "pendingAgent_approve");
    
    public static MyWebElement agentInfo_tableTitle=myWebelement("jQuerySelector:p.title:contains('BUSINESS INFORMATION')","agentInfo_tableTitle");
    public static MyWebElement employeeInfo_tableTitle=myWebelement("jQuerySelector:p.title:contains('EMPLOYEE INFORMATION')","employeeInfo_tableTitle");
    
    public static MyWebElement navBar_dashboard=myWebelement("jQuerySelector:ul.nav a:contains('DASHBOARD')","navBar_dashboard");
    public static MyWebElement navBar_newAccount=myWebelement("jQuerySelector:ul.nav a:contains('NEW ACCOUNT')","navBar_newAccount");
    public static MyWebElement navBar_history=myWebelement("jQuerySelector:ul.nav a:contains('HISTORY')","navBar_history");
    public static MyWebElement navBar_leases=myWebelement("jQuerySelector:ul.nav a:contains('LEASES')","navBar_leases");
    public static MyWebElement navBar_account=myWebelement("jQuerySelector:ul.nav a:contains('ACCOUNT')","navBar_account");  
    
    public static MyWebElement home_icon =  myWebelement("cssSelector:i.icon-home", "Home_Icon");
    public static MyWebElement businessName_label=myWebelement("jQuerySelector:label[for='businessName']","businessName_label");  
    public static MyWebElement businessName_value=myWebelement("jQuerySelector:label[for='businessName'] + div","businessName_value");  

    public static MyWebElement officeEmail_label=myWebelement("jQuerySelector:label[for='officeEmail']","officeEmail_label");
    public static MyWebElement officePhone_label=myWebelement("jQuerySelector:label:contains('OFFICE PHONE:')","officePhone_label");
    public static MyWebElement officePhone_value=myWebelement("jQuerySelector:label:contains('OFFICE PHONE:') + div","officePhone_value");  

    public static MyWebElement FEIN_label=myWebelement("jQuerySelector:label:contains('FEIN:')","FEIN_label");
    
    public static MyWebElement address1_label=myWebelement("jQuerySelector:label:contains('ADDRESS 1:')","address1_label");
    public static MyWebElement address1_value=myWebelement("jQuerySelector:label:contains('ADDRESS 1:') + div","address1_value");

    public static MyWebElement address2_label=myWebelement("jQuerySelector:label:contains('ADDRESS 2:')","address2_label");
    
    public static MyWebElement city_label=myWebelement("jQuerySelector:label:contains('CITY:')","city_label");  
    public static MyWebElement city_value=myWebelement("jQuerySelector:label:contains('CITY:') + div","city_value");  

    public static MyWebElement state_label=myWebelement("jQuerySelector:label:contains('STATE:')","state_label");  
    public static MyWebElement state_value=myWebelement("jQuerySelector:label:contains('STATE:') + div","state_value");  

    public static MyWebElement zipCode_label=myWebelement("jQuerySelector:label:contains('ZIP CODE:')","zipCode_label");
    public static MyWebElement zipCode_value=myWebelement("jQuerySelector:label:contains('ZIP CODE:') + div","zipCode_value");

    public static MyWebElement user_icon =  myWebelement("cssSelector:i.icon-user", "User_Icon");
    public static MyWebElement jobTitle_label=myWebelement("jQuerySelector:label:contains('JOB TITLE:')","jobTitle_label");  
    
    public static MyWebElement firstName_label=myWebelement("jQuerySelector:label:contains('FIRST NAME:')","firstName_label");  
    public static MyWebElement firstName_value=myWebelement("jQuerySelector:label:contains('FIRST NAME:') + div","firstName_value");  

    public static MyWebElement lastName_label=myWebelement("jQuerySelector:label:contains('LAST NAME:')","lastName_label");  
    public static MyWebElement lastName_value=myWebelement("jQuerySelector:label:contains('LAST NAME:') +div","lastName_value");  

    public static MyWebElement email_label=myWebelement("jQuerySelector:label:contains('EMAIL:')","email_label",1);  
    public static MyWebElement email_value=myWebelement("jQuerySelector:label:contains('EMAIL:') + div","email_value",1);  

    public static MyWebElement password_label=myWebelement("jQuerySelector:label:contains('PASSWORD:')","password_label");  
    
    public static MyWebElement mainPhone_label=myWebelement("jQuerySelector:label:contains('MAIN PHONE:')","mainPhone_lable");
    public static MyWebElement mainPhone_value=myWebelement("jQuerySelector:label:contains('MAIN PHONE:') + div","mainPhone_value");  

    public static MyWebElement cellPhone_label=myWebelement("jQuerySelector:label:contains('CELL PHONE:')","cellPhone_label");  
   
    // page title
    public static String pageTitle = "ACCOUNT SIGNUP";

    // define some web elements group used in test cases, which will make test case as simple as possible
        //  all the original displayed label part in this page
    public static MyWebElement [] originalDisplayedElements = { accountSignUp_title, pendingAgent_dismiss, pendingAgent_approve, agentInfo_tableTitle, employeeInfo_tableTitle, navBar_dashboard,
    	signOut_link,navBar_newAccount, navBar_history, navBar_leases, navBar_account, aboutUs_footlink, contactUs_footlink, faq_footlink, signUp_footlink, privacy_footlink,terms_footlink, fcra__footlink,
    	home_icon,businessName_label,officeEmail_label,officePhone_label,FEIN_label,address1_label,address2_label,city_label,state_label,zipCode_label,
    	user_icon,jobTitle_label,firstName_label,lastName_label,email_label,password_label,mainPhone_label,cellPhone_label};
        //  all the display values in business info table, which should be fetched from database
    public static MyWebElement [] businessInformation_value = {businessName_value,officePhone_value,address1_value,city_value,state_value,zipCode_value };
        //  all the display values in employee info table, which should be fetched from database
    public static MyWebElement [] employeeInformation_value = {firstName_value,lastName_value,email_value,mainPhone_value };

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static ShowAgentAccountSignUpPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new ShowAgentAccountSignUpPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        accountSignUp_title.verifyElementPresent();
    }

}
