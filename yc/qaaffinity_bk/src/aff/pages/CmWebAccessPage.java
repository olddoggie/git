package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class CmWebAccessPage extends AffinityBasePage {
	public MyWebElement okButton = myWebelement("webaccessOk");
	public MyWebElement scriptBox = myWebelement("column_right");
	public CmWebAccessPage(WebDriver driver) {
		super(driver);
		scriptBox.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - CM Web Access");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
