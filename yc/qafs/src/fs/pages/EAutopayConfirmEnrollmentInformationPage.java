package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayConfirmEnrollmentInformationPage extends FsBasePage {
	public static MyWebElement checkingAccountInformation = myWebelement("className:editinfo confirmuri clearboth", "checkingAccountInformation");	
	public static MyWebElement bankInformation = myWebelement("className:editinfo confirmuri", "checkingAccountInformation");
	// this element contains the above two webelements.
	public static MyWebElement confirmEnfrollmentInformation = myWebelement("className:container_content", "confirmEnfrollmentInformation");
	public static MyWebElement editButton = myWebelement("ap_editEnroll_info", "editButton");
	public static MyWebElement continueButton = myWebelement("ap_editenroll_continue", "continueButton");

	
	public static EAutopayConfirmEnrollmentInformationPage load() {
		pageTitle = "eAutopay Manage Payment Accounts";
		//waitUntilPageTitlePresent(pageTitle);
		editButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayConfirmEnrollmentInformationPage();
		
	}

}
