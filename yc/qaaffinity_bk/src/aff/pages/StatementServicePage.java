package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class StatementServicePage extends AffinityBasePage {
	public MyWebElement updateinfoButton = myWebelement("updateinfoBtn");
	public MyWebElement terminateButton = myWebelement("terminateBtn");
	
//	public MyWebElement paperlessCheckboxCheckedHidden = myWebelement("paperlessCheckboxCheckedHidden");
//	public MyWebElement scriptNameHidden = myWebelement("scriptNameHidden");
//	public MyWebElement paperlessOptOutDisabledHidden = myWebelement("paperlessOptOutDisabledHidden");
//	public MyWebElement onlineEmailOptOutDisabledHidden = myWebelement("onlineEmailOptOutDisabledHidden");
//	public MyWebElement contactEmailOptOutDisabledHidden = myWebelement("contactEmailOptOutDisabledHidden");
//	public MyWebElement paperlessOptOutCheckedHidden = myWebelement("paperlessOptOutCheckedHidden");
//	public MyWebElement onlineEmailOptOutCheckedHidden = myWebelement("onlineEmailOptOutCheckedHidden");
//	public MyWebElement contactEmailOptOutCheckedHidden = myWebelement("contactEmailOptOutCheckedHidden");
//	public MyWebElement FE_main_content_scrollarea = myWebelement("FE_main_content_scrollarea");
//	public MyWebElement column_left = myWebelement("column_left");
//	public MyWebElement stop_paperless = myWebelement("stop_paperless");
//	public MyWebElement onlineEmailOptOut = myWebelement("onlineEmailOptOut");
//	public MyWebElement primaryEmail = myWebelement("primaryEmail");
//	public MyWebElement alternativeEmail = myWebelement("alternativeEmail");
//	public MyWebElement contactEmailOptOut = myWebelement("contactEmailOptOut");
//	public MyWebElement column_right = myWebelement("column_right");
//	public MyWebElement btn_float = myWebelement("btn_float");

	
	
	public StatementServicePage(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - Statement Service";
		terminateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle(pageTitle);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
	


}
