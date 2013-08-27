package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class TermsPage extends FsBasePage {
	
	public static TermsPage load() {
		pageTitle = "1FBUSA Online Services Terms";
		waitUntilContentPresent("IMPORTANT: THIS AGREEMENT ('AGREEMENT') SETS FORTH THE TERMS");
		print("On " + pageTitle + " page");
		return new TermsPage();
	}


}
