package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;


public class CriticalNotePage extends AffinityBasePage{
	public MyWebElement notesDataTable = myWebelement("notesDataTable");	
	public MyWebElement firstNote = myWebelement("xpath://table[@id='notesDataTable']/tbody/tr[1]", "firstNote");
	public CriticalNotePage(WebDriver driver) {
		super(driver);
		notesDataTable.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Critical Notes");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		System.out.println("aaaa");
	}
	
	
//	public MyWebElement getNotesDataTable()
//	{	return 	notesDataTable.waitUntilElementPresentInFrame(CONTENT_FRAME);
//	
//	}
}
