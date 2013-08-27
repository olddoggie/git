package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class MyPersonalInformationHomePage extends FsBasePage {

	public static MyWebElement selectThisAccountLink =  myWebelement("name:&lid=updateAccount", "selectThisAccountLink");
	
	public static MyPersonalInformationHomePage load() {
		pageTitle = "My Personal Information Home";
		selectThisAccountLink.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MyPersonalInformationHomePage();
	}


}
