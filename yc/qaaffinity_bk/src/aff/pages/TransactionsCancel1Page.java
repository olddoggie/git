package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class TransactionsCancel1Page extends AffinityBasePage {	
	public MyWebElement transferChngRsn = myWebelement("transferChngRsn");
	public MyWebElement expirationDateMonth = myWebelement("expirationMonth");
	public MyWebElement expirationDateYear = myWebelement("expirationYear");
	public MyWebElement scriptTable = myWebelement("column_right");
	public MyWebElement nextButton = myWebelement("next");
	public MyWebElement backButton = myWebelement("Back");

	public TransactionsCancel1Page(WebDriver driver) {
		super(driver);
		//Cancel Funds Transfer Transactions
		pageTitle = "Affinity - Transactions";
		nextButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
