package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class ChangeDemoPage extends AffinityBasePage {
	public MyWebElement address1EditBox = myWebelement("address1");
	public MyWebElement address2EditBox = myWebelement("address2");
	public MyWebElement cityEditBox = myWebelement("city");
	public MyWebElement stateDropDown = myWebelement("state");
	public MyWebElement zip1EditBox = myWebelement("zip1");
	public MyWebElement zip2EditBox = myWebelement("zip2");
	public MyWebElement primaryEmailEditBox = myWebelement("primaryEmail");
	public MyWebElement alternateEmailEditBox = myWebelement("alternateEmail");
	public MyWebElement emailOptOutCheckBox = myWebelement("emailOptOut");
	public MyWebElement gradDate1Radio = myWebelement("gradDate1");
	public MyWebElement gradDate2Radio = myWebelement("gradDate2");
	public MyWebElement gradDate3Radio = myWebelement("gradDate3");	
	public MyWebElement gradMonthEditBox = myWebelement("gradMonth");
	public MyWebElement gradYearEditBox = myWebelement("gradYear");
	public MyWebElement updateButton = myWebelement("update");
	public MyWebElement terminateButton = myWebelement("terminate");
	public MyWebElement clearButton = myWebelement("clear");
	public MyWebElement doneButton = myWebelement("done");
	
	public ChangeDemoPage(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - Change Demographics";
		updateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle(pageTitle);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
	
	// public class ChangeDemoForm
	// {
	// String address1 = "address_org";
	// String primaryEmail = "email_org@yahoo.com";
	// }

}
