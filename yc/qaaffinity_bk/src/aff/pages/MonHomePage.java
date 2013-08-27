package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class MonHomePage  extends AffinityBasePage {
	public MyWebElement cycle2DateLink = myWebelement("ctdLink");
	public MyWebElement declinedAuthorizationLink = myWebelement("dclnLink");
	public MyWebElement onlineStatementLink = myWebelement("onlineStmtLink");
	public MyWebElement paymentHistoryLink = myWebelement("paymentLink");
	public MyWebElement statementHistoryLink = myWebelement("statementLink");	
		
	public MonHomePage (WebDriver driver) {
		super(driver);
		onlineStatementLink.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle("Affinity - Mon Home Page");
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}

}
