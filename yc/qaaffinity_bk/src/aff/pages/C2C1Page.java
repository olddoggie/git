package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class C2C1Page extends AffinityBasePage {	
	public MyWebElement lastFourDigits = myWebelement("srcAcctLastFourDigits");
	public MyWebElement expirationMonth = myWebelement("expirationMonth");
	public MyWebElement expirationYear = myWebelement("expirationYear");
	public MyWebElement nextButton = myWebelement("next");
	public MyWebElement terminateButton = myWebelement("terminate");

//	public MyWebElement column_right = myWebelement("column_right");
//	public MyWebElement column_left = myWebelement("column_left");
	
	public C2C1Page(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - C2C";
		terminateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
