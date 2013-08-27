package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class CustomerServiceChangePasswordPage extends FsBasePage {
	public static MyWebElement userNameEditBox = myWebelement("name:userName", "userNameEditBox");
	public static MyWebElement oldPasswordEditBox = myWebelement("name:oldPassword", "passwordEditBox");
	public static MyWebElement newPasswordEditBox = myWebelement("name:password", "newPasswordEditBox");
	public static MyWebElement confirmPasswordEditBox = myWebelement("name:confirmPassword", "confirmPasswordEditBox");
	public static MyWebElement changePasswordButton = myWebelement("name:&lid=changePasswordSubmit", "changePasswordButton");
	

	public static CustomerServiceChangePasswordPage load() {
		pageTitle = "Customer Service Change Password";
		changePasswordButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new CustomerServiceChangePasswordPage();
	}

	public static void submitForm(String userName, String oldPassword, String newPassword){
		userNameEditBox.clearSet(userName);
		oldPasswordEditBox.clearSet(oldPassword);
		newPasswordEditBox.clearSet(newPassword);
		confirmPasswordEditBox.clearSet(newPassword);
		changePasswordButton.click();
		
	}
}
