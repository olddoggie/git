package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class MailInboxPage extends FsBasePage {
	public static MyWebElement testMessageLink = myWebelement("partialLinkText:Re: ", "testMessageLink");
	public static MyWebElement inboxTable = myWebelement("className:inbox", "inboxTable");
	
	public static MailInboxPage load() {
		pageTitle = "Mail Inbox";
		inboxTable.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MailInboxPage();
	}

}
