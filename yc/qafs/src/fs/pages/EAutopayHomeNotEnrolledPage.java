package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayHomeNotEnrolledPage extends FsBasePage {	
	
	// for some unknown reason, enrollNowForEAutopayLink doesn't work. So enrollNowForEAutopayImg is used as a workaround.
	public static MyWebElement enrollNowForEAutopayImg =  myWebelement("xpath://img[@title='ENROLL NOW for eAutopay!']", "enrollNowForEAutopayImg");	
//	public MyWebElement enrollNowForEAutopayLink =  myWebelement("name:&lid=epayEnroll", "enrollNowForEAutopayLink");	
	
	public static EAutopayHomeNotEnrolledPage load() {
		pageTitle = "eAutopay Home Not Enrolled";
		enrollNowForEAutopayImg.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayHomeNotEnrolledPage();
	}

}
