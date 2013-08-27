package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class C2C2Page extends AffinityBasePage {	
	public MyWebElement cardTypeDropDown = myWebelement("cardType");
	public MyWebElement nameOnCardEditBox = myWebelement("nameOnCard");
	public MyWebElement callerIsOwnerRadioBox = myWebelement("caller");
	public MyWebElement ownerOnPhoneRadioBox = myWebelement("cssSelector:input[value='callerOnPhone']");
	public MyWebElement scriptTable = myWebelement("column_right");
	public MyWebElement nextButton = myWebelement("next");
	public MyWebElement restartButton = myWebelement("restart");
	public MyWebElement terminateButton = myWebelement("terminate");

	
	public C2C2Page(WebDriver driver) {
		super(driver);
		//Enroll Source Account
		pageTitle = "Affinity - C2C";
		terminateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
