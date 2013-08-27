package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class ExpandededNotePage extends AffinityBasePage {
	public MyWebElement startDate = myWebelement("startDate");
	public MyWebElement endDate = myWebelement("endDate");
	public MyWebElement channel = myWebelement("channel");
	public MyWebElement source = myWebelement("source");
	public MyWebElement searchNotesButton = myWebelement("searchNoteButton");
	//public MyWebElement FE_main_content_scrollarea = myWebelement("FE_main_content_scrollarea");	

	public ExpandededNotePage(WebDriver driver) {
		super(driver);
		startDate.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Expanded Notes");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}


}
