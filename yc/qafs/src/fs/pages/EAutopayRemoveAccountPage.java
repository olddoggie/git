package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayRemoveAccountPage extends FsBasePage {

	public static MyWebElement removeAccountButton = myWebelement("name:ap_submit_pmt", "removeAccountButton");
	public static MyWebElement doNotremoveButton = myWebelement("name:ap_acct_donot_rmv", "doNotremoveButton");

	public static EAutopayRemoveAccountPage load() {
		pageTitle = "eAutopay Remove Account";
		removeAccountButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayRemoveAccountPage();
	}


}
