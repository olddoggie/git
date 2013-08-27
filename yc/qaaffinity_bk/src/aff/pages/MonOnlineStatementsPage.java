package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class MonOnlineStatementsPage extends AffinityBasePage {

	public MyWebElement scriptTable = myWebelement("column_right");
	
	public MonOnlineStatementsPage(WebDriver driver) {
		super(driver);
		//switchToFrame(CONTENT_FRAME);
		scriptTable.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Online Statements");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}


}
