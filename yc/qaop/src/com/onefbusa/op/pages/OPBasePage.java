package com.onefbusa.op.pages;

import applications.BasePage;
import frameLib.MyWebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;

import java.lang.reflect.Field;

import static frameLib.MyVerifyAssert.verifyContains;
import static frameLib.MyVerifyAssert.verifyNotContains;
import static frameLib.PrintTestCases.print;


public class OPBasePage extends BasePage {
    // extract all the public elements like landing page title, sign link, foot links, etc.
    public static MyWebElement landingPage_title = myWebelement("jQuerySelector:h1:contains('Open Property')", "LandingPage_title");
    public static MyWebElement signOut_table = myWebelement("id:loginDiv", "SignOut_Table");
    public static MyWebElement signOut_link = myWebelement("jQuerySelector:button:contains('Sign Out')", "SignOut_Link");
    public static MyWebElement signOutYes_link = myWebelement("jQuerySelector:span:contains('YES')", "SignOut_CLickYes_Button");
    public static MyWebElement signOutNo_link = myWebelement("jQuerySelector:span:contains('NO')", "SignOut_ClickNO_Button");
    public static MyWebElement aboutUs_footlink = myWebelement("linkText:ABOUT US","About_Us_FootLink");
    public static MyWebElement contactUs_footlink = myWebelement("linkText:CONTACT US","Contact_Us_FootLink");
    public static MyWebElement faq_footlink = myWebelement("linkText:FAQ","FAQ_FootLink");
    public static MyWebElement signUp_footlink = myWebelement("linkText:SIGN UP","SignUp_FootLink");
    public static MyWebElement privacy_footlink = myWebelement("linkText:PRIVACY POLICY","Privacy_FootLink");
    public static MyWebElement terms_footlink = myWebelement("linkText:TERMS & CONDITIONS","Terms&&Conditions_FootLink");
    public static MyWebElement fcra__footlink = myWebelement("linkText:FCRA","FCRA__FootLink");

    // methods, used to log into system successfully
    public static void Login_Succeed(String username, String password) throws Exception {
        HomePage.login_username.input(username);
        HomePage.login_password.input(password);
        HomePage.login_btn.click();
        signOut_link.verifyElementPresent();
    }

    // methods, used to log in by using a wrong password, and check the login fail table displayed
    public static void Login_Fail(String username, String password) throws Exception {
        HomePage.login_username.input(username);
        HomePage.login_password.input(password);
        HomePage.login_btn.click();
        HomePage.loginFail_table.verifyElementPresent();
    }

    // methods, used to sign out successfully by click link then Yes button
    public static void SignOut_Succeed() throws Exception {
        signOut_link.click();
        //signOutYes_link.click();
        signOut_link.verifyElementNotPresent();
    }

    // methods, used to sign out, but somehow click No button when prompt box display to hold the log in status
    public static void SignOut_Fail() throws Exception {
        signOut_link.click();
        signOutNo_link.click();
        signOut_link.verifyElementPresent();
    }

//  below methods are used to verify/assert check point, some of which are extended from qa frame work's basePage, easy to realize the usage fron method name

    public static void verifyPageTitle(String pageTitle) {
        print(" Verify pageTitle is: " + pageTitle);
        if (!pageTitle.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not " + pageTitle);
        }
    }

    public static void verifyHttpProtocol(String pageTitle) {
        print(" Now switch to Http Protocol , verify Http Protocol page is forbidden ");
        if ( pageTitle.equals(driver.getTitle())) {
            print(" HTTP Protocol Page <a href=" + driver.getCurrentUrl() + ">" + driver.getCurrentUrl() + "</a> is not forbidden !");
            throw new IllegalStateException("HTTP Protocol is not forbidden !");
        }
    }

    public static void verifyPageContains(String... str) {
        for (String s : str) {
            verifyContains(" Verify " + getPageTitle() + " Page contains: " + s, getBody()
                    .replaceAll("\r|\n", " "), s);
        }
    }

    public static void verifyPageNotContains(String... str) {
        for (String s : str) {
            verifyNotContains(" Verify " + getPageTitle() + " Page contains: " + s, getBody()
                    .replaceAll("\r|\n", " "), s);
        }
    }

    public static void verifyMultipleElementsContains(MyWebElement[] myWebElements,String[] str) {
        if (str.length < myWebElements.length) {
            for (int i = str.length; i < myWebElements.length; i ++ ) {
                str[i] = str[i-1];
            }
        }
        for ( int j = 0; j < myWebElements.length; j++ ) {
            myWebElements[j].verifyContains(str[j]);
        }
    }

    public static void verifyMultipleElementsContainsIgnoreSpace(MyWebElement[] myWebElements,String[] str) {
        if (str.length < myWebElements.length) {
            for (int i = str.length; i < myWebElements.length; i ++ ) {
                str[i] = str[i-1];
            }
        }
        for ( int j = 0; j < myWebElements.length; j++ ) {
            myWebElements[j].verifyContainsIgnoreSpaces(str[j]);
        }
    }

    public static void verifyMultipleElementsEquals(MyWebElement[] myWebElements, String[] titles,String... Attributes) {
        if (titles.length < myWebElements.length) {
            for (int i = titles.length; i < myWebElements.length; i ++ ) {
                titles[i] = titles[i-1];
                if ( Attributes!=null){
                    Attributes[i] = Attributes [i-1];
                }
            }
        }
        for ( int j = 1; j < myWebElements.length+1; j++ ) {
            if ( Attributes.length!= 0){
                myWebElements[j-1].verifyEquals(titles[j-1],Attributes[j-1]);
            } else {
                myWebElements[j-1].verifyEquals(titles[j-1]);
            }
        }
    }

    public static void verifyMultipleElementsPresent(MyWebElement[] myWebElements) {
        int count = 0;
        for (MyWebElement f : myWebElements) {
           try{
               f.verifyElementPresent();
           }   catch (Exception e)  {
                    count++;
            }
        }
        if( count !=0) {
            print( " " + count + " of " + myWebElements.length + " Elements Not Displayed!");
            throw new NotFoundException( count + " of " + myWebElements.length + " Elements Not Displayed!");
        }
    }

    public static void verifyAllElementsPresent(BasePage bp) {
        Class<? extends BasePage> c = bp.getClass();
        Field[] fArr = c.getDeclaredFields();
        for (Field f : fArr) {
            if (f.getType().getName().contains("MyWebElement")) {
                try {
                        print(" Verify whether element " + ((MyWebElement) f.get(bp)).getElementName()  + " displayed");
                        ((MyWebElement) f.get(bp)).waitUntilElementPresent();
                    } catch (Exception e) {
                    try {
                        print(" -" + ((MyWebElement) f.get(bp)).getElementName()  + "  Not Found!");
                        throw new NoSuchElementException("Element " + ((MyWebElement) f.get(bp)).getElementName() + " Not Found!");
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    public static Alert waitForAlert(int t) throws InterruptedException   // wait at most for period for the alert box to appear
    {
        Alert alert = null;
        for(int i=0; i<t; i++)
        {
            try
            {
                alert = driver.switchTo().alert();
                return alert;
            }
            catch(NoAlertPresentException e)
            {
                Thread.sleep(1000);
                continue;
            }
        }
        print(" The Alert Box is NOT Displayed!");
        return alert;
    }

    public static Boolean verifyAlertPresent() throws InterruptedException     // wait for 5 seconds to see whether AlertBox appear
    {
        Alert alert = waitForAlert(5) ;
        Boolean flag = false;
        if (alert!=null){
            print(" The Alert Box is Displayed!");
            flag =true;
        }
        return flag;
    }

    public static void alertAccept() {
        try{
            waitForAlert(5).accept();
        }
        catch (Exception e) {
            throw new NotFoundException("Alert Box Not Found or Acceptable!");
        }
    }

    public static void verifyStatusColor(MyWebElement myWebElement, String status) {
        print(" Verify the Status of " + myWebElement.getElementName() + " is correctly displayed.");
        if (status.equals("ACTIVE")) {
            myWebElement.verifyContainsAttribute("success", "class");
            // in our codes, some ACTIVE label's class attribute value is text-success
            // but iin Your Lease page's lease table, the class is ACTIVE
        } else if (status.equals("ACTIVE_YourLease")) {
            myWebElement.verifyContainsAttribute("ACTIVE", "class");
        } else if (status.equals("PENDING")) {
            myWebElement.verifyContainsAttribute("info", "class");
        } else if (status.equals("RESOLVED")) {
            myWebElement.verifyContainsAttribute("", "class");  // resolved is black, and has no class style
        }  else if (status.equals("DISPUTED")) {
            myWebElement.verifyContainsAttribute("important", "class");
        }   else if (status.equals("UNOCCUPIED")) {
            myWebElement.verifyContainsAttribute("success", "class");
        }   else if (status.equals("OCCUPIED")) {
            myWebElement.verifyContainsAttribute("error", "class");
        } else if (status.equals("LEASE ENDING SOON")) {
            myWebElement.verifyContainsAttribute("warning", "class");
        } else {
            throw new IllegalStateException("Illegal Lease Status: " + status + " !");
        }
    }

}
