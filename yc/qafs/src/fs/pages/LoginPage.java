package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class LoginPage extends FsBasePage {

	public static MyWebElement userNameEditBox =  myWebelement("name:userName", "userNameEditBox");
	public static  MyWebElement passwordEditBox =  myWebelement("name:password", "passwordEditBox");
	public static  MyWebElement submitButton =  myWebelement("cssSelector:button[title='Log In']", "submitButton");	
	//public MyWebElement submitButton =  myWebelement("xPath://button[@title='Log In']", "submitButton");		
	public static  MyWebElement registerLink =  myWebelement("linkText:Register", "registerLink");
	public static  MyWebElement forgotLink =  myWebelement("linkText:Forgot", "forgotLink");	
	
	public static LoginPage load() {
		pageTitle = "1FBUSA Online Services";
		submitButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new LoginPage();
	}

		
	public static  void login(String userName, String password) {
		userNameEditBox.clearSet(userName);
		passwordEditBox.clearSet(password);
		//submitButton.submit();
		submitButton.click();
	}

}
