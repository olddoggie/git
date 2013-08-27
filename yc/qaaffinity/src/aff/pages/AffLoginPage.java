package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import applications.BasePage;

import frameLib.*;


public class AffLoginPage extends AffinityBasePage {
	public static MyWebElement userNameEditBox = myWebelement("username");
	public static MyWebElement passwordEditBox = myWebelement("password");
	public static MyWebElement fdrAgentCodeRadio1 = myWebelement("radio1");
	public static MyWebElement fdrAgentCodeRadio2 = myWebelement("radio2");
	public static MyWebElement loginButton = myWebelement("loginButton");	

	public static AffLoginPage load() {
		pageTitle = "Affinity Login";	
		userNameEditBox.waitUntilElementPresentInWindow(pageTitle);
		print("On " + pageTitle + " page");
		return new AffLoginPage();
	}

	public static void loginAff(String userName, String password, int agent) {
		userNameEditBox.sendKeys(userName);
		passwordEditBox.sendKeys(password);
		if(agent == 1)
		{
			fdrAgentCodeRadio1.click();
		}
		else
		{
			fdrAgentCodeRadio2.click();			
		}		
		loginButton.click();
	}	
	
	
	public static void loginAff(String userName, String password) {
		userNameEditBox.sendKeys(userName);
		passwordEditBox.sendKeys(password);
		fdrAgentCodeRadio1.click();
		loginButton.click();
		
		
	}

}
