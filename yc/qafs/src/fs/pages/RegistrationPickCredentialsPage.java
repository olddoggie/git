package fs.pages;


import static frameLib.PrintTestCases.print;
import static fs.FsConstants.*;

import static myconstant.General.*;

import frameLib.MyWebElement;
import applications.BasePage;

public class RegistrationPickCredentialsPage extends BasePage{ 
	public static MyWebElement userNameEditBox = myWebelement("name:userName", "userNameEditBox");
	public static MyWebElement passwordEditBox = myWebelement("name:password", "passwordEditBox");
	public static MyWebElement confirmPasswordEditBox = myWebelement("name:confirmPassword", "confirmPasswordEditBox");
	public static MyWebElement emailEditBox = myWebelement("name:email", "emailEditBox");
	//<a href="#" jQuery1346172822335="3">
	public static MyWebElement basketBallPersonalImage = myWebelement("3", "basketBallPersonalImage");
	public static MyWebElement registerButton = myWebelement("cssSelector:button[title='Register']", "registerButton");	
	
	public static RegistrationPickCredentialsPage load() {
		pageTitle = "1FBUSA Online Services Registration Pick Credentials";
		waitUntilPageReady();
		registerButton.waitUntilElementFound();
		verifyPageTitle(pageTitle);
		print("On " + pageTitle + " page");
		return new RegistrationPickCredentialsPage();
	}
	
	public static void submitForm(String userName,String email )
	{
		userNameEditBox.clearSet(userName);
		passwordEditBox.clearSet(FS_PASSWORD);
		confirmPasswordEditBox.clearSet(FS_PASSWORD);
		emailEditBox.clearSet(email);
		basketBallPersonalImage.click();
		registerButton.click();
	}
	
}
