package aff.pages;

import static frameLib.PrintTestCases.print;
import static frameLib.TestFrameLib.*;
import org.openqa.selenium.WebDriver;
import applications.BasePage;

import frameLib.*;


public class NonMonCardActionPopUpPage extends AffinityBasePage {
	private String nonMonHomePageTitle = "Affinity - Nonmon Home Page";
	private String cardActionPageTitle = "Affinity - Card Activation";
	public MyWebElement yesButton = myWebelement("cssSelector:BUTTON[title=Yes]","yesButton");
	public MyWebElement noButton = myWebelement("cssSelector:BUTTON[title=No]","noButton");	

	public NonMonCardActionPopUpPage(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - Popup Page";
		waitUntilWindowPresent(pageTitle);
		print("On " + pageTitle + " page");
	}

	
	public void clickNoButton() {
		noButton.click();
		waitUntilWindowPresent(cardActionPageTitle);
	}		
	
	public void clickYesButton() {
		yesButton.click();
		waitUntilWindowPresent(nonMonHomePageTitle);
	}	


}
