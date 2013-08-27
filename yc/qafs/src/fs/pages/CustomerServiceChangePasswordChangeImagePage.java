package fs.pages;


import static frameLib.PrintTestCases.print;

import static myconstant.General.*;

import frameLib.MyWebElement;
import applications.BasePage;


public class CustomerServiceChangePasswordChangeImagePage extends BasePage{ 
	public static MyWebElement basketBallPersonalImage = myWebelement("3", "basketBallPersonalImage");
	public static MyWebElement continueButton = myWebelement("cssSelector:button[title='Continue']", "continueButton");	
	
	public static CustomerServiceChangePasswordChangeImagePage load() {
		pageTitle = "Customer Service Change Password / Personal Image";
		continueButton.waitUntilElementPresent();
		verifyPageTitle(pageTitle);
		print("On " + pageTitle + " page");
		return new CustomerServiceChangePasswordChangeImagePage();
	}
	
	public static void submitForm()
	{
		basketBallPersonalImage.click();
		continueButton.click();
	}
	
}
