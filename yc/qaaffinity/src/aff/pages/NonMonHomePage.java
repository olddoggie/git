package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class NonMonHomePage extends AffinityBasePage {
	public static MyWebElement accountSecurityLink = myWebelement("accountSecurityLink");	
	public static MyWebElement changCmDemoLink = myWebelement("chngDemoLink");		
	public static MyWebElement cmWebAccessLink = myWebelement("webAccessLink");	
	public static MyWebElement overLimitServiceLink = myWebelement("overLimitService");	
	public static MyWebElement cliLink = myWebelement("cliLink");
	
	public static MyWebElement p2dLink = myWebelement("p2dLink");	
	public static MyWebElement requestAdjustmentLink = myWebelement("name:requestAdjustmentLink","requestAdjustmentLink");	
	public static MyWebElement stmtServiceLink = myWebelement("stmtServiceLink");	
	public static MyWebElement termsLink = myWebelement("termAPRLink");	
	public static MyWebElement textBankingServiceLink = myWebelement("textBankingServiceLink");	
	public static MyWebElement cardActivationLink = myWebelement("name:cardActLink","cardActivationLink");
	
	public static NonMonHomePage load() {
		termsLink.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new NonMonHomePage();
	}

}
