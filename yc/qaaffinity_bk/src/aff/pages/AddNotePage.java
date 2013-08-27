package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;


public class AddNotePage extends AffinityBasePage {
	public MyWebElement addNoteButton = myWebelement("addNoteButton");	
	public MyWebElement addNoteTextarea = myWebelement("add_note_textarea");		
	public MyWebElement internationalTravelCheckbox = myWebelement("international");	
	public MyWebElement criticalCheckbox = myWebelement("critical");	
	

	public AddNotePage(WebDriver driver) {
		super(driver);
		addNoteTextarea.waitUntilElementEnabledInFrame(NOTE_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}

	//1 -- regular note
	//2 -- internation note
	//3 -- critical note
	public void addNote(String note, int noteType) {
		switchToFrame(NOTE_FRAME);
		switch (noteType) {
		case 1:
			internationalTravelCheckbox.uncheck();
			criticalCheckbox.uncheck();
			break;
		case 2:
			internationalTravelCheckbox.check();
			criticalCheckbox.uncheck();
			break;
		case 3:
			internationalTravelCheckbox.uncheck();
			criticalCheckbox.check();
			break;
		default:
			break;
		}

		addNoteTextarea.clearSet(note);
		addNoteButton.click();
	}

}
