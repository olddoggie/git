package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayConfirmPaymentPage extends FsBasePage {
	
	public static MyWebElement cancelButton = myWebelement("confirm_ap_cancel", "cancelButton");
	public static MyWebElement editPaymentButton = myWebelement("ap_edit_pmt", "editPaymentButton");
	public static MyWebElement authorizePaymentButton = myWebelement("btn_auth_pmt", "authorizePaymentButton");


	public static EAutopayConfirmPaymentPage load() {
		pageTitle = "eAutopay Confirm Payment Information";
		authorizePaymentButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayConfirmPaymentPage();
	}


}
