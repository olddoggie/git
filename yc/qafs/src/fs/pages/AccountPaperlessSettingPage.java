package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;

import frameLib.MyWebElement;
import static myconstant.General.*;

public class AccountPaperlessSettingPage extends FsBasePage {
	public static MyWebElement deliverySettingList = myWebelement("del_set_list", "deliverySettingList");
	public static MyWebElement cancelOnlineOnlyButton = myWebelement("btn_online_tc_cancel", "cancelButton");
	public static MyWebElement submitOnlineOnlyButton = myWebelement("btn_online_tc_accept", "submitButton");
	public static MyWebElement cancelUSMailButton = myWebelement("btn_del_set_usps_cancel", "cancelButton");	
	public static MyWebElement submitUSMailButton = myWebelement("btn_del_set_usps_submit", "submitButton");
	public static MyWebElement onlineTermAcceptCheckBox = myWebelement("online_tc_accept", "onlineTermAcceptCheckBox");
	public static MyWebElement reEnterEmailAddressEditBox = myWebelement("email_verify", "reEnterEmailAddressEditBox");
	
	public static AccountPaperlessSettingPage load() {
		pageTitle = "Account Paperless Setting";
		deliverySettingList.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new AccountPaperlessSettingPage();
	}
	public static void change2OnLineOnly(){
		deliverySettingList.selectByVisibleText("Online Only");
		reEnterEmailAddressEditBox.waitUntilElementPresent().clearSet(EMAIL);
		onlineTermAcceptCheckBox.check();
		submitOnlineOnlyButton.waitUntilElementEnabled().click();
		waitUntilContentPresent("Your request to receive billing statements");
	}
	
	public static void change2USMail(){
		deliverySettingList.selectByVisibleText("U.S. Mail");
		waitUntilContentPresent("My Personal Information");
		submitUSMailButton.waitUntilElementEnabled().click();	
		waitUntilContentPresent("Your request to receive billing statements");
	}

}
