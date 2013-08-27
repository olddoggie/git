package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class AccountOnlineStatementsPage extends FsBasePage {
	public static MyWebElement makeAPaymentWithEautopayImageLink = myWebelement("name:&lid=makePayment", "makeAPaymentWithEautopayImageLink");
	public static MyWebElement ViewStatement1Link = myWebelement("linkText:View Statement", "ViewStatement1Link", 0);
	
	public static AccountOnlineStatementsPage load() {
		pageTitle = "Account Online Statements";
		makeAPaymentWithEautopayImageLink.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new AccountOnlineStatementsPage();
	}

}
