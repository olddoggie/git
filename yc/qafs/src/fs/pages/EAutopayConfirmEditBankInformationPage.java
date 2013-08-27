package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.FrameUtilities;
import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayConfirmEditBankInformationPage extends FsBasePage {

	public static MyWebElement checkingAccountInformation = myWebelement("className:editinfo confirmuri", "checkingAccountInformation");
	public static MyWebElement editButton = myWebelement("ap_editbank_info", "editButton");
	public static MyWebElement continueButton = myWebelement("ap_editbank_continue", "continueButton");



	public static EAutopayConfirmEditBankInformationPage load() {
		pageTitle = "eAutopay Confirm Edit Bank Information";
		editButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayConfirmEditBankInformationPage();
	}

}
