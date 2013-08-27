package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class TextBankingPage extends AffinityBasePage {	
	public static MyWebElement okButton = myWebelement("tb_svc_ok");

	
	public static TextBankingPage load() {
		pageTitle = "Affinity - Text Banking Services";
		okButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new TextBankingPage();
	}
}
