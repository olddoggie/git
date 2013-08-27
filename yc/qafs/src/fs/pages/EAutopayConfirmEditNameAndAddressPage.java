package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.FrameUtilities;
import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayConfirmEditNameAndAddressPage extends FsBasePage {

	public static MyWebElement nameAndAddressOnCheck = myWebelement("className:editinfo confirmuri clearboth", "nameAndAddressOnCheck");
	public static MyWebElement editButton = myWebelement("ap_editpersonal_info", "editButton");
	public static MyWebElement continueButton = myWebelement("ap_editperson_continue", "continueButton");



	public static EAutopayConfirmEditNameAndAddressPage load() {
		pageTitle = "eAutopay Confirm Edit Name(s) and Address";
		editButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayConfirmEditNameAndAddressPage();
	}

}
