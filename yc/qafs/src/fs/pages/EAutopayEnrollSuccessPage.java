package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayEnrollSuccessPage extends FsBasePage {
	
	public static MyWebElement makeAPaymentButton = myWebelement("name:makePayment", "makeAPaymentButton");
	public static MyWebElement accountInformationText = myWebelement("className:acct_info_disp_wrap", "accountInformationText");

	public static  EAutopayEnrollSuccessPage load() {
		pageTitle = "eAutopay Enroll Success";
		makeAPaymentButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayEnrollSuccessPage();
	}


}
