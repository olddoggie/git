package fs.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import applications.BasePage;

import frameLib.*;

public class ForgottenPasswordHomePage extends FsBasePage {
	public static MyWebElement userNameEditBox = myWebelement("name:userName", "userNameEditBox");
	public static MyWebElement accountHolderNameEditBox = myWebelement("name:nameOnCard", "accountHolderNameEditBox");
	public static MyWebElement ssnAEditBox = myWebelement("name:ssnA", "ssnAEditBox");
	public static MyWebElement ssnBEditBox = myWebelement("name:ssnB", "ssnBEditBox");
	public static MyWebElement ssnCEditBox = myWebelement("name:ssnC", "ssnCEditBox");
	public static MyWebElement dateOfBirthMonthEditBox = myWebelement("name:dobMonth", "dateOfBirthMonthEditBox");
	public static MyWebElement dateOfBirthDayEditBox = myWebelement("name:dobDay", "dateOfBirthDayhEditBox");
	public static MyWebElement dateOfBirthYearEditBox = myWebelement("name:dobYear", "dateOfBirthYearEditBox");
	public static MyWebElement accountNumberEditBox = myWebelement("name:accountNumber", "accountNumberEditBox");
	public static MyWebElement continueButton = myWebelement("cssSelector:button[title='Continue']", "submitButton");

	public static ForgottenPasswordHomePage load() {
		pageTitle = "Forgotten Password";
		continueButton.waitUntilElementPresent();
		verifyPageTitle(pageTitle);
		print("On " + pageTitle + " page");
		return new ForgottenPasswordHomePage();
	}

//	public static void submitForm(String nameOnCard, String ssnA, String ssnB, String ssnC, String dateOfBirthMonth, String dateOfBirthDay,
//			String dateOfBirthYear, String accountNumber) {
//		accountHolderNameEditBox.sendKeys();
//		ssnAEditBox.sendKeys(ssnA);
//		ssnBEditBox.sendKeys(ssnB);
//		ssnCEditBox.sendKeys(ssnC);
//		dateOfBirthMonthEditBox.sendKeys(dateOfBirthMonth);
//		dateOfBirthDayEditBox.sendKeys(dateOfBirthDay);
//		dateOfBirthYearEditBox.sendKeys(dateOfBirthYear);
//		accountNumberEditBox.sendKeys(accountNumber);
//		continueButton.click();
//	}
	
	public static void submitForm(String userName, String[] registerInfo) {
		userNameEditBox.clearSet(userName);
		accountHolderNameEditBox.sendKeys(registerInfo[0]);
		ssnAEditBox.sendKeys(registerInfo[1]);
		ssnBEditBox.sendKeys(registerInfo[2]);
		ssnCEditBox.sendKeys(registerInfo[3]);
		dateOfBirthMonthEditBox.sendKeys(registerInfo[4]);
		dateOfBirthDayEditBox.sendKeys(registerInfo[5]);
		dateOfBirthYearEditBox.sendKeys(registerInfo[6]);
		accountNumberEditBox.sendKeys(registerInfo[7]);
		continueButton.click();
	}
}
