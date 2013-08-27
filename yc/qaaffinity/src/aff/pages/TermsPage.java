package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class TermsPage extends AffinityBasePage {	
	public static MyWebElement currentTermsScript = myWebelement("currterms_id");
	public static MyWebElement currentTermsHead = myWebelement("currterms_head");
	public static MyWebElement currentTerms = myWebelement("currerms_data");
	public static MyWebElement aprTerms = myWebelement("aprterms_wrap");
	public static MyWebElement fees = myWebelement("fees_1_data");
	public static MyWebElement cashAdvanceFeeButton = myWebelement("btn_script_cashadvfee");
	public static MyWebElement scriptButton = myWebelement("btn_script_currterms");
	public static MyWebElement termsScriptCode = myWebelement("xpath://div[@class='SCRIPT_bg']/p[@class='code']", "termsScriptCode");
	public static MyWebElement termsScriptSay = myWebelement("xpath://div[@class='SCRIPT_bg']/p[@class='say']", "termsScriptSay");
//	public static MyWebElement scriptButton = myWebelement("btn_script_currterms");

	
	public static TermsPage load() {
		pageTitle = "Affinity - Terms";
		scriptButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new TermsPage();
	}
}
