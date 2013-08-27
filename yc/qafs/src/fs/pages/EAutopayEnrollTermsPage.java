package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;

//The page title is "Enrollment Terms" instead of "eAutopay Enrollment Terms" upon editing bank
public class EAutopayEnrollTermsPage extends FsBasePage {
	
	public static MyWebElement agreeTermsCheckBox = myWebelement("enroll_ap_agree", "agreeTermsCheckBox");
	public static MyWebElement cancelButton = myWebelement("btn_enroll_cancel", "cancelButton");
	public static MyWebElement acceptButton = myWebelement("btn_enroll_continue", "acceptButton");
	
	public static MyWebElement termsText= myWebelement("className:tc_wrap_agreetext", "termsText");
	public static MyWebElement termsOtherText = myWebelement("className:indent", "termsOtherText");



	public static EAutopayEnrollTermsPage load() {
		pageTitle = "eAutopay Enrollment Terms";
		cancelButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayEnrollTermsPage();
	}

	public static void enrollTerms(){
		agreeTermsCheckBox.check();
		acceptButton.click();
		print("Enroll term");
	}

}
