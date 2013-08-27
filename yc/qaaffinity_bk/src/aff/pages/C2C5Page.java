package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class C2C5Page extends AffinityBasePage {	
	public MyWebElement scriptTable = myWebelement("column_left");
//	public MyWebElement column_right = myWebelement("column_right");
	public MyWebElement addC2CButton = myWebelement("addC2C");
	public MyWebElement backButton = myWebelement("back");
	public MyWebElement restartButton = myWebelement("restart");
	public MyWebElement terminateButton = myWebelement("terminate");


	
	public C2C5Page(WebDriver driver) {
		super(driver);
		//Transfer - Confirmation
		pageTitle = "Affinity - C2C";
		terminateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
