package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class MonDeclineAuthorizationsPage extends AffinityBasePage {

	public MyWebElement refreshButton = myWebelement("refreshButton");
	
	public MonDeclineAuthorizationsPage(WebDriver driver) {
		super(driver);
		//switchToFrame(CONTENT_FRAME);
		refreshButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Declined Authorizations");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}


}
