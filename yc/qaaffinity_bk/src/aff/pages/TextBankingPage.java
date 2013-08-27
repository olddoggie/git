package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class TextBankingPage extends AffinityBasePage {	
	public MyWebElement okButton = myWebelement("tb_svc_ok");

	
	public TextBankingPage(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - Text Banking Services";
		okButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
