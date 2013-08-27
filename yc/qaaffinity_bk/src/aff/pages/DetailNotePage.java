package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class DetailNotePage extends AffinityBasePage {
	public MyWebElement detailTable = myWebelement("test");
	public DetailNotePage(WebDriver driver) {
		super(driver);
		detailTable.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Detail Notes");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}


}
