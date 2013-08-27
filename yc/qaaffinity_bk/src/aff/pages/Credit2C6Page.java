package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class Credit2C6Page extends AffinityBasePage {	
	public MyWebElement scriptTable = myWebelement("column_left");
//	public MyWebElement column_right = myWebelement("column_right");
	public MyWebElement okButton = myWebelement("ok");
	
	public Credit2C6Page(WebDriver driver) {
		super(driver);
		//Transfer Successful
		pageTitle = "Affinity - C2C";
		okButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
