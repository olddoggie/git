package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class TransactionsCancel2Page extends AffinityBasePage {	
	public MyWebElement scriptTable = myWebelement("column_left");
	public MyWebElement okButton = myWebelement("ok");


	public TransactionsCancel2Page(WebDriver driver) {
		super(driver);
		//Confirm Cancel Funds Transfer Transactions
		pageTitle = "Affinity - Transactions";
		okButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
