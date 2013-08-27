package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class CliPage extends AffinityBasePage {
	public MyWebElement okButton = myWebelement("ok");
	public MyWebElement scriptBox = myWebelement("column_right");
	public CliPage(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - Credit Line Increase";
		scriptBox.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle(pageTitle);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}

}
