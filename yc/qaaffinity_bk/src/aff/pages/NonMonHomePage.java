package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class NonMonHomePage extends AffinityBasePage {
	public MyWebElement accountSecurityLink = myWebelement("accountSecurityLink");	
	public MyWebElement changCmDemoLink = myWebelement("chngDemoLink");		
	public MyWebElement cmWebAccessLink = myWebelement("webAccessLink");	
	public MyWebElement overLimitServiceLink = myWebelement("overLimitService");	
	public MyWebElement cliLink = myWebelement("cliLink");
	
	public MyWebElement p2dLink = myWebelement("p2dLink");	
	public MyWebElement requestAdjustmentLink = myWebelement("name:requestAdjustmentLink","requestAdjustmentLink");	
	public MyWebElement stmtServiceLink = myWebelement("stmtServiceLink");	
	public MyWebElement termsLink = myWebelement("termAPRLink");	
	public MyWebElement textBankingServiceLink = myWebelement("textBankingServiceLink");	
	public MyWebElement cardActivationLink = myWebelement("name:cardActLink","cardActivationLink");
	
	public NonMonHomePage(WebDriver driver) {
		super(driver);
		termsLink.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}

}
