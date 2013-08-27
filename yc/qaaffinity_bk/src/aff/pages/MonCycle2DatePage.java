package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class MonCycle2DatePage extends AffinityBasePage {
	public MyWebElement refreshButton = myWebelement("refreshButton");

	public MonCycle2DatePage(WebDriver driver) {
		super(driver);
		//switchToFrame(CONTENT_FRAME);
		refreshButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Cycle-to-Date Transaction");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}


}
