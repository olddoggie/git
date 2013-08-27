package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class AccountStatementsPage extends FsBasePage {
	public static MyWebElement enrollNowForEAutopayLink = myWebelement("name:&lid=epayEnroll", "enrollNowForEAutopayLink");
	
	public static AccountStatementsPage load() {
		pageTitle = "eAutopay Home Not Enrolled";
		enrollNowForEAutopayLink.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new AccountStatementsPage();
	}

}
