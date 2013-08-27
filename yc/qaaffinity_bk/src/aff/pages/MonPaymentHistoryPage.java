package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class MonPaymentHistoryPage extends AffinityBasePage {

	public MyWebElement pamentHistoryTable = myWebelement("pamentHistoryTable");
	
	public MonPaymentHistoryPage(WebDriver driver) {
		super(driver);
		//switchToFrame(CONTENT_FRAME);
		pamentHistoryTable.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Payment History");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}


}
