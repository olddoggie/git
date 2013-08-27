package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class MyPersonalInformationThankYouPage extends FsBasePage {

	public static MyWebElement youRequestHasScript = myWebelement("className:thx", "youRequestHasScript");
	
	public static MyPersonalInformationThankYouPage load() {
		pageTitle = "My Personal Information Thanks You";
		youRequestHasScript.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MyPersonalInformationThankYouPage();
	}


}
