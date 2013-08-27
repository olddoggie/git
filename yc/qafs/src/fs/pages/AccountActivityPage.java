package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class AccountActivityPage extends FsBasePage {
	public static MyWebElement viewOnlineStatementsButton =  myWebelement("name:os", "viewOnlineStatementsButton");	
	public static MyWebElement accountNote =  myWebelement("cssSelector:div.acct_note_wrap", "accountNote");
	public static MyWebElement accountTransaction =  myWebelement("cssSelector:div.transactions", "accountNote");	
	
	public static AccountActivityPage load() {
		pageTitle = "Account Activity";
		viewOnlineStatementsButton.waitUntilElementPresent();
		accountNote.waitUntilElementFound();
		print("On " + pageTitle + " page");
		return new AccountActivityPage();
	}


}
