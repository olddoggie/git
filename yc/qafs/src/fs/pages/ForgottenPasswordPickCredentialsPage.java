package fs.pages;


import static frameLib.PrintTestCases.print;

import static myconstant.General.*;

import frameLib.FrameUtilities;
import frameLib.MyWebElement;
import applications.BasePage;


public class ForgottenPasswordPickCredentialsPage extends BasePage{ 
	public static MyWebElement newPasswordEditBox = myWebelement("name:password", "newPasswordEditBox");
	public static MyWebElement confirmPasswordEditBox = myWebelement("name:confirmPassword", "confirmPasswordEditBox");
	//<a href="#" jQuery1346172822335="3">
	public static MyWebElement basketBallPersonalImage = myWebelement("3", "basketBallPersonalImage");
	// type = submit
	public static MyWebElement continueButton = myWebelement("cssSelector:button[title='Continue']", "continueButton");	
	
	public static ForgottenPasswordPickCredentialsPage load() {
		pageTitle = "Forgotten Password / Forgotten Personal Image";
		waitUntilPageReady();
		newPasswordEditBox.waitUntilElementPresent();
		verifyPageTitle(pageTitle);
		print("On " + pageTitle + " page");
		return new ForgottenPasswordPickCredentialsPage();
	}
	
	public static void submitForm(String password)
	{
		newPasswordEditBox.clearSet(password);
		confirmPasswordEditBox.clearSet(password);
		//FrameUtilities.mySleep(3);
		basketBallPersonalImage.click();
		continueButton.click();
	}
	
}
