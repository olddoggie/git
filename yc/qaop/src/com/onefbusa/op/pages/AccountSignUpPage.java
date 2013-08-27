package com.onefbusa.op.pages;

import frameLib.MyWebElement;

import static frameLib.PrintTestCases.print;

public class AccountSignUpPage extends HomePage {
    // web elements in this page
    public static MyWebElement accountSignUp_title = myWebelement("jQuerySelector:h3:contains('ACCOUNT SIGN UP')", "AccountSignUpPage_Title");
    public static MyWebElement businessName_title = myWebelement("cssSelector:p.title", "BusinessName_Title",0);
    public static MyWebElement employeeName_title = myWebelement("cssSelector:p.title", "EmployeeName_Title",1);

    public static MyWebElement home_icon =  myWebelement("cssSelector:i.icon-home", "Home_Icon");
    public static MyWebElement user_icon =  myWebelement("cssSelector:i.icon-user", "User_Icon");

    public static MyWebElement businessName_label = myWebelement("jQuerySelector:label:contains('BUSINESS NAME:*')", "BusinessName*_Label");
    public static MyWebElement officeMail_label = myWebelement("jQuerySelector:label:contains('OFFICE EMAIL:')", "OfficeEmail_Label");
    public static MyWebElement officePhone_label = myWebelement("jQuerySelector:label:contains('OFFICE PHONE:*')", "OfficePhone*_Label");
    public static MyWebElement fein_label = myWebelement("jQuerySelector:label:contains('FEIN:')", "FEIN_Label");
    public static MyWebElement address1_label = myWebelement("jQuerySelector:label:contains('ADDRESS 1:*')", "Address1*_Label");
    public static MyWebElement address2_label = myWebelement("jQuerySelector:label:contains('ADDRESS 2:')", "Address2_Label");
    public static MyWebElement city_label = myWebelement("jQuerySelector:label:contains('CITY:*')", "City*_Label");
    public static MyWebElement state_label = myWebelement("jQuerySelector:label:contains('STATE:*')", "State*_Label");
    public static MyWebElement zipCode_label = myWebelement("jQuerySelector:label:contains('ZIP CODE:*')", "ZipCode*_Label");
    public static MyWebElement jobTitle_label = myWebelement("jQuerySelector:label:contains('JOB TITLE:')", "JobTitle_Label");
    public static MyWebElement firstName_label = myWebelement("jQuerySelector:label:contains('FIRST NAME:*')", "FirstName*_Label");
    public static MyWebElement lastName_label = myWebelement("jQuerySelector:label:contains('LAST NAME:*')", "LastName*_Label");
    public static MyWebElement email_label = myWebelement("jQuerySelector:label:contains('EMAIL:*')", "EmployeeEmail*_Label");
    public static MyWebElement mainPhone_label = myWebelement("jQuerySelector:label:contains('MAIN PHONE:*')", "MainPhone*_Label");
    public static MyWebElement cellPhone_label = myWebelement("jQuerySelector:label:contains('CELL PHONE:')", "CellPhone_Label");
    public static MyWebElement password_label = myWebelement("jQuerySelector:label:contains('PASSWORD:*')", "Password*_Label");
    public static MyWebElement confirmPassword_label = myWebelement("jQuerySelector:label:contains('CONFIRM')", "Confirm_Password*_Label");

    public static MyWebElement businessName_textbox = myWebelement("cssSelector:input[name=businessName][placeholder=Business\\ Name]", "BusinessName_InputTextBox");
    public static MyWebElement officeMail_textbox = myWebelement("cssSelector:input[name=officeEmail][placeholder=Office\\ Email]", "OfficeEmail_InputTextBox");
    public static MyWebElement officePhone_textbox = myWebelement("cssSelector:input[name=officePhone][placeholder=Office\\ Phone]", "OfficePhone_InputTextBox");
    public static MyWebElement fein_textbox = myWebelement("cssSelector:input[name=fein][placeholder=FEIN]", "FEIN_InputTextBox");
    public static MyWebElement address1_textbox = myWebelement("cssSelector:input[name=address1][placeholder=Address\\ 1]", "Address1_InputTextBox");
    public static MyWebElement address2_textbox = myWebelement("cssSelector:input[name=address2][placeholder=Address\\ 2]", "Address2_InputTextBox");
    public static MyWebElement city_textbox = myWebelement("cssSelector:input[name=city][placeholder=City]", "City_InputTextBox");
    public static MyWebElement state_selectbox = myWebelement("state", "State_SelectBox");
    public static MyWebElement zip5_textbox = myWebelement("cssSelector:input[name=zip5][placeholder=ZIP\\ 5]", "Zip5_InputTextBox");
    public static MyWebElement zip4_textbox = myWebelement("cssSelector:input[name=zip4][placeholder=ZIP\\ 4]", "Zip4_InputTextBox");
    public static MyWebElement jobTitle_textbox = myWebelement("cssSelector:input[name=title][placeholder=Job\\ Title]", "JobTitle_InputTextBox");
    public static MyWebElement firstName_textbox = myWebelement("cssSelector:input[name=firstName][placeholder=First\\ Name]", "FirstName_InputTextBox");
    public static MyWebElement lastName_textbox = myWebelement("cssSelector:input[name=lastName][placeholder=Last\\ Name]", "LastName_InputTextBox");
    public static MyWebElement email_textbox = myWebelement("cssSelector:input[name=email][placeholder=Email]", "EmployeeEmail_InputTextBox");
    public static MyWebElement mainPhone_textbox = myWebelement("cssSelector:input[name=mainPhone][placeholder=Main\\ Phone]", "MainPhone_InputTextBox");
    public static MyWebElement cellPhone_textbox = myWebelement("cssSelector:input[name=cellPhone][placeholder=Cell\\ Phone]", "CellPhone_InputTextBox");
    public static MyWebElement password_textbox = myWebelement("cssSelector:input[name=password][placeholder=Password]", "Password_InputTextBox");
    public static MyWebElement confirmPassword_textbox = myWebelement("cssSelector:input[name=confirmPassword]", "ConfirmPassword_InputTextBox");

    public static MyWebElement businessName_errorLabel = myWebelement("cssSelector:label[for=businessName]","BusinessName_ErrorMessage");
    public static MyWebElement officeMail_errorLabel = myWebelement("cssSelector:label[for=officeMail]","OfficeMail_ErrorMessage");
    public static MyWebElement officePhone_errorLabel = myWebelement("cssSelector:label[for=officePhone]","OfficePhone_ErrorMessage");
    public static MyWebElement fein_errorLabel = myWebelement("cssSelector:label[for=fein]","FEIN_ErrorMessage");
    public static MyWebElement address1_errorLabel = myWebelement("cssSelector:label[for=address1]","Address1_ErrorMessage");
    public static MyWebElement address2_errorLabel = myWebelement("cssSelector:label[for=address2]","Address2_ErrorMessage");
    public static MyWebElement city_errorLabel = myWebelement("cssSelector:label[for=city]","City_ErrorMessage");
    public static MyWebElement zip_errorLabel = myWebelement("cssSelector:label[for=zip5]","Zip5_ErrorMessage");
    public static MyWebElement jobTitle_errorLabel = myWebelement("cssSelector:label[for=title]","JobTitle_ErrorMessage");
    public static MyWebElement firstName_errorLabel = myWebelement("cssSelector:label[for=firstName]","FirstName_ErrorMessage");
    public static MyWebElement lastName_errorLabel = myWebelement("cssSelector:label[for=lastName]","LastName_ErrorMessage");
    public static MyWebElement email_errorLabel = myWebelement("cssSelector:label[for=email]","EmployeeEmail_ErrorMessage");
    public static MyWebElement mainPhone_errorLabel = myWebelement("cssSelector:label[for=mainPhone]","mainPhone_ErrorMessage");
    public static MyWebElement cellPhone_errorLabel = myWebelement("cssSelector:label[for=cellPhone]","cellPhone_ErrorMessage");
    public static MyWebElement password_errorLabel = myWebelement("cssSelector:label[for=password]","Password_ErrorMessage");
    public static MyWebElement confirmPassword_errorLabel = myWebelement("cssSelector:label[for=confirmPassword]","ConfirmPassword_ErrorMessage");

    public static MyWebElement clickHere_link = myWebelement("linkText:Click here","ClickHere_Link");
    public static MyWebElement continue_Btn = myWebelement("continue", "Continue_Button");

    // page title
    public static String pageTitle = "ACCOUNT SIGN UP";

    // define some web elements group used in test cases, which will make test case as simple as possible
        // mandatory label parts in sign up page
    public static MyWebElement [] mandatoryElements = { businessName_label, officePhone_label, address1_label, city_label, state_label, zipCode_label,
            firstName_label, lastName_label, email_label, mainPhone_label, password_label, confirmPassword_label} ;
        // optional label parts in sign up page
    public static MyWebElement [] optionalElements = { officeMail_label, fein_label, address2_label, jobTitle_label, cellPhone_label} ;

    //page method: call AgentSignUp to finish a new agent sign up and see the pending account page
    public static void AgentSignUp (String[] str) throws Exception {
        businessName_textbox.input(str[0]);
        officeMail_textbox.input(str[1]);
        officePhone_textbox.input(str[2]);
        fein_textbox.input(str[3]);
        address1_textbox.input(str[4]);
        address2_textbox.input(str[5]);
        city_textbox.input(str[6]);
        state_selectbox.selectByVisibleText(str[7]);
        zip5_textbox.input(str[8]);
        zip4_textbox.input(str[9]);
        jobTitle_textbox.input(str[10]);
        firstName_textbox.input(str[11]);
        lastName_textbox.input(str[12]);
        email_textbox.input(str[13]);
        mainPhone_textbox.input(str[14]);
        cellPhone_textbox.input(str[15]);
        password_textbox.input(str[16]);
        confirmPassword_textbox.input(str[17]);
        continue_Btn.click();
    }

    // page load, should be written in test case for each page navigation, which will make test report easy to read
    public static AccountSignUpPage load() {
        print("\nOn <a href=" + driver.getCurrentUrl() + ">" +  pageTitle  + "</a> Page: ");
        waitUntilPageReady();
        return new AccountSignUpPage();
    }

    // usage: page.load.ready(), judge whether page has been displayed correctly, some basic info like page title will be the check point
    public static void ready () {
        verifyPageTitle(pageTitle);
        accountSignUp_title.verifyElementPresent();
    }

}