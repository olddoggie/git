package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class FrequentlyAskedQuestionsPage extends FsBasePage {
	
	public static FrequentlyAskedQuestionsPage load() {
		pageTitle = "Frequently Asked Questions (FAQs)";
		waitUntilContentPresent("1FBUSA Frequently Asked Questions");
		print("On " + pageTitle + " page");
		return new FrequentlyAskedQuestionsPage();
	}


}
