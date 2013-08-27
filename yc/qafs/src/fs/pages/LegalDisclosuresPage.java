package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class LegalDisclosuresPage extends FsBasePage {
	public static  MyWebElement creditCardAccountBillingRightsStatementLink =  myWebelement("linkText:View Document", "creditCardAccountBillingRightsStatementLink",0);
	public static  MyWebElement firstFinancialBankUSAPrivacyNoticeLink =  myWebelement("linkText:View Document", "firstFinancialBankUSAPrivacyNoticeLink", 1);	
	
	
	public static LegalDisclosuresPage load() {
		pageTitle = "Legal Disclosures";
		creditCardAccountBillingRightsStatementLink.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new LegalDisclosuresPage();
	}


}
