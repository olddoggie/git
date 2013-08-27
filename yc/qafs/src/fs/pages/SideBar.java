package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class SideBar extends FsBasePage {
	public static MyWebElement myPersonalInfoLink =  myWebelement("linkText:My Personal Info", "myPersonalInfoLink");
	//public static MyWebElement editEmailLink=  myWebelement("edit_email", "editEmailLink");	
	public static MyWebElement editEmailLink=  myWebelement("linkText:Edit", "editEmailLink",0);	
	public static MyWebElement editPhoneLink=  myWebelement("edit_email", "editPhoneLink",1);
	// this link will be present when account is using U.S. mail
	public static MyWebElement learnMoreLink=  myWebelement("linkText:Learn More", "learnMoreLink");	
	public static SideBar load() {
//		pageTitle = "Account Overview";
//		viewOnlineStatementsButton.waitUntilElementPresent();
//		print("On " + pageTitle + " page");
		return new SideBar();
	}

}
