package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;


public class NonMonCardActivationAdditionScriptPage extends AffinityBasePage{
	public MyWebElement backButton = myWebelement("backToPre", "backButton");
	
	public NonMonCardActivationAdditionScriptPage(WebDriver driver) {
		super(driver);
		backButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Card Activation");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}	

	
}
