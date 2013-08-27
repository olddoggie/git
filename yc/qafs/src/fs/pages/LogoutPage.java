package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class LogoutPage extends FsBasePage {
	public static  MyWebElement takeSurveyButton =  myWebelement("name:survey", "takeSurveyButton");	
	
	public static LogoutPage load() {
		pageTitle = "Logout";
		waitUntilPageReady();
		takeSurveyButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new LogoutPage();
	}


}
