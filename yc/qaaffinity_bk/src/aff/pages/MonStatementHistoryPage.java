package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class MonStatementHistoryPage extends AffinityBasePage {

	public MyWebElement nextButton = myWebelement("rightButton");
	public MyWebElement endButton = myWebelement("rightEndButton");	
	public MonStatementHistoryPage(WebDriver driver) {
		super(driver);
		//switchToFrame(CONTENT_FRAME);
		endButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Statement History");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
