package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import frameLib.*;

public class Transactions1Page extends AffinityBasePage {	
	public MyWebElement destinationAccountEditBox = myWebelement("destCardLast4Digits");
	public MyWebElement paymentTypeEditBox = myWebelement("transactionMethod");
	public MyWebElement sourceAccountEditBox = myWebelement("sourceCardLast4Digits");
	public MyWebElement statusEditBox = myWebelement("status");
	public MyWebElement filterListButton = myWebelement("filterList");
	public MyWebElement transactionTable= myWebelement("SortTable");
	public MyWebElement firstPendTransactionLink = myWebelement("xpath://table[@id='SortTable']//tbody//tr[contains(.,'x5677')]");
	//// contains is not supported by css3. 
	//// use xpath or write a customized method to find the object by tag, then the object's innerText to find the wanted object
//	public MyWebElement firstPendTransactionLink11 = myWebelement("xpath://tr[contains(.,'x5677')]");

	
	public Transactions1Page(WebDriver driver) {
		super(driver);
		//View / Edit Funds Transfers
		pageTitle = "Affinity - Transactions";
		filterListButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
	
	// This is the pend transaction for affinity BAT
	public void clickPend5677Transaction()
	{
		transactionTable.findElement(By.tagName("tr"), "^Pend.*?x5677.*").click();
		print("  Click FirstPend Transaction");
	}	
	
	
}
