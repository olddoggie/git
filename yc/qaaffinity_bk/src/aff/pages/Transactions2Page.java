package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class Transactions2Page extends AffinityBasePage {	
	public MyWebElement topRightTable = myWebelement("column_right");
	public MyWebElement topLeftTable = myWebelement("column_left");
	public MyWebElement actualFeeChrg = myWebelement("feeCharged");
	public MyWebElement feeChngRsn = myWebelement("feeChangeReason");
	public MyWebElement transferAmount = myWebelement("amount");
	public MyWebElement transferChngRsn = myWebelement("transferChangeReason");
	public MyWebElement expirationDateMonth = myWebelement("sourceExpireDateMonth");
	public MyWebElement expirationDateYear = myWebelement("sourceExpireDateYear");

	public MyWebElement backButton = myWebelement("back");
	public MyWebElement updateButton = myWebelement("update");
	public MyWebElement cancelButton = myWebelement("cancel");
	public MyWebElement resetButton = myWebelement("reset");

	public Transactions2Page(WebDriver driver) {
		super(driver);
		//View Funds Transfer Detail
		pageTitle = "Affinity - Transactions";
		updateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
