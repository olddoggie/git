package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;


public class NonMonCardActivationPage extends AffinityBasePage{
	public MyWebElement scriptBox = myWebelement("column_right");
	public MyWebElement ssnIncorrectButton = myWebelement("SSNIncorrect");
	public MyWebElement DobIncorrectButton = myWebelement("DOBIncorrect");
	public MyWebElement bothIncorrectButton = myWebelement("bothIncorrect");
	public MyWebElement viewButton = myWebelement("view");
	public MyWebElement activateCardButton = myWebelement("activateCard");
	public MyWebElement terminateButton = myWebelement("terminate","terminateButton");
	
	public NonMonCardActivationPage(WebDriver driver) {
		super(driver);
		activateCardButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Card Activation");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}	
	
//	public MyWebElement getActivateCardButton()
//	{	return 	activateCardButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
//	
//	}
		
}
