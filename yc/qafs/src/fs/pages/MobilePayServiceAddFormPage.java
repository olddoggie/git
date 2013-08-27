package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class MobilePayServiceAddFormPage extends FsBasePage {

	public static MyWebElement phoneAreaCodeEditBox = myWebelement("name:cellPhoneNumberA", "phoneAreaCodeEditBox");
	public static MyWebElement phonePrefixEditBox = myWebelement("name:cellPhoneNumberB", "phonePrefixEditBox");
	public static MyWebElement phoneSuffixEditBox = myWebelement("name:cellPhoneNumberC", "phoneSuffixEditBox");
	public static MyWebElement pinEditBox = myWebelement("name:smsPinNumber", "pinEditBox");
	public static MyWebElement confirmPinEditBox = myWebelement("name:confirmSmsPinNumber", "confirmPinEditBox");
	public static MyWebElement carrierEditBox = myWebelement("name:carrierId", "carrierEditBox");
	//public static MyWebElement mobilepayDefaultCheckingAccountId = myWebelement("name:mobilepayDefaultCheckingAccountId", "mobilepayDefaultCheckingAccountId");
	public static MyWebElement termsCheckBox = myWebelement("name:termsConditionsAccepted", "termsConditionsAccepted");
	public static MyWebElement addButton = myWebelement("btn_tb_add", "addButton");
	public static MyWebElement cancelButton = myWebelement("btn_tb_cancel", "cancelButton");

	
	
	public static MobilePayServiceAddFormPage load() {
		pageTitle = "MobilePay Service Add Form";
		addButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MobilePayServiceAddFormPage();
	}

		
	public static  void submitForm() {
		phoneAreaCodeEditBox.clearSet("712");
		phonePrefixEditBox.clearSet("203");
		phoneSuffixEditBox.clearSet("9553");
		pinEditBox.clearSet("123456");
		confirmPinEditBox.clearSet("123456");
		carrierEditBox.selectByVisibleText("Sprint");
		termsCheckBox.check();
		addButton.waitUntilElementEnabled().click();
	}

}
