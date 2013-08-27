package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class AccountOverviewPage extends FsBasePage {
	public static MyWebElement viewOnlineStatementsButton =  myWebelement("name:os", "viewOnlineStatementsButton");
	public static MyWebElement makeAPaymentWithEautopayImageLink=  myWebelement("name:&lid=makePayment", "makeAPaymentWithEautopayImageLink");

	
	// does not work on dda account
	public static AccountOverviewPage load() {
		pageTitle = "Account Overview";
		logOutLink.waitUntilElementPresent();
		//waitUntilContentPresent("Account Overview");
		//viewOnlineStatementsButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new AccountOverviewPage();
	}

}
