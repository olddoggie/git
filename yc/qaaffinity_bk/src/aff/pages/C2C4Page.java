package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class C2C4Page extends AffinityBasePage {	
	public MyWebElement transferAmountEditBox = myWebelement("transferAmount");
	public MyWebElement cvcCw2EditBox = myWebelement("transferAmount");	
	public MyWebElement actualFeeChrgEditBox = myWebelement("actualFee");
	public MyWebElement feeChangeReasonDropDown = myWebelement("waiverReason");
	public MyWebElement scriptTable = myWebelement("column_right");
//	public MyWebElement column_left = myWebelement("column_left");
	public MyWebElement nextButton = myWebelement("Next");
	public MyWebElement restartButton = myWebelement("restart");
	public MyWebElement terminateButton = myWebelement("terminate");
	
	public C2C4Page(WebDriver driver) {
		super(driver);
		//C2C Transfer - Required Information
		pageTitle = "Affinity - C2C";
		terminateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
