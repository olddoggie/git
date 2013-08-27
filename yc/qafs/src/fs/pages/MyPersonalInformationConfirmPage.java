package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class MyPersonalInformationConfirmPage extends FsBasePage {

	public static MyWebElement backButton = myWebelement("Back", "backButton");
	public static MyWebElement confirmButton = myWebelement("name:&lid=updatePersonalInfo02", "confirm");
	public static MyWebElement addressPhoneEmailBlock = myWebelement("classNname:editinfo confirmuri", "confirm");
	
	public static MyPersonalInformationConfirmPage load() {
		pageTitle = "My Personal Information Confirm";
		confirmButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MyPersonalInformationConfirmPage();
	}


}
