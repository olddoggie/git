package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.FrameUtilities;
import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayEditBankInformationPage extends FsBasePage {
	
	public static MyWebElement newAbaRoutingNumberEditBox = myWebelement("name:newAbaRoutingNumber", "newAbaRoutingNumberEditBox");
	public static MyWebElement newCheckingNumberEditBox = myWebelement("name:newCheckingAccountNumber", "newCheckingNumberEditBox");
	public static MyWebElement confirmCheckingNumberEditBox = myWebelement("name:confirmCheckingAccountNumber", "confirmCheckingNumberEditBox");
	public static MyWebElement editButton = myWebelement("name:ap_editbank_edit", "editButton");
	public static MyWebElement doNotEditButton = myWebelement("xpath://button[@title='Do Not Edit']", "dnNotEditButton");



	public static EAutopayEditBankInformationPage load() {
		pageTitle = "eAutopay Edit Bank Information";
		editButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayEditBankInformationPage();
	}

	public static void editBankInfo(String abaNumber, String checkingAccountNumber){
		newAbaRoutingNumberEditBox.clearSet(abaNumber);
		newCheckingNumberEditBox.clearSet(checkingAccountNumber);
		confirmCheckingNumberEditBox.clearSet(checkingAccountNumber);
		editButton.click();
	}

}
