package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class TermsPage extends AffinityBasePage {	
	public MyWebElement currentTermsScript = myWebelement("currterms_id");
	public MyWebElement currentTermsHead = myWebelement("currterms_head");
	public MyWebElement currentTerms = myWebelement("currerms_data");
	public MyWebElement aprTerms = myWebelement("aprterms_wrap");
	public MyWebElement fees = myWebelement("fees_1_data");
	public MyWebElement cashAdvanceFeeButton = myWebelement("btn_script_cashadvfee");
	public MyWebElement scriptButton = myWebelement("btn_script_currterms");
	public MyWebElement termsScriptCode = myWebelement("xpath://div[@class='SCRIPT_bg']/p[@class='code']", "termsScriptCode");
	public MyWebElement termsScriptSay = myWebelement("xpath://div[@class='SCRIPT_bg']/p[@class='say']", "termsScriptSay");
//	public MyWebElement scriptButton = myWebelement("btn_script_currterms");

	
	public TermsPage(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - Terms";
		scriptButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
