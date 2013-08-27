package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class CondensedNotePage extends AffinityBasePage {
	public MyWebElement sourceDropdown = myWebelement("name:source");	
	public MyWebElement searchNoteButton = myWebelement("searchNoteButton");	
	public MyWebElement notesDataTable = myWebelement("notesDataTable");	
	
	public CondensedNotePage(WebDriver driver) {
		super(driver);
		searchNoteButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Condensed Notes");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}

	public void SearchNote(String source) {
		notesDataTable.waitUntilElementPresentInFrame(CONTENT_FRAME).click();
		sourceDropdown.selectByVisibleText(source);
		searchNoteButton.click();
	}	
	
	public void SearchNote(SearchForm condensedNoteForm) {
		notesDataTable.waitUntilElementPresentInFrame(CONTENT_FRAME).click();
		sourceDropdown.selectByVisibleText(condensedNoteForm.source);
		searchNoteButton.click();
	}
	
		
	public class SearchForm
	{
		@SuppressWarnings("unused")
		private String type = "ALL";
		private String source = "ALL";

		public SearchForm()
		{
		}
		
		public SearchForm setSource(String source) {
			this.source = source;
			return this;
		}

		public SearchForm setType(String type) {
			this.type = type;
			return this;
		}	
		
	}
}
