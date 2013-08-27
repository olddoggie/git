package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class FootLinks extends FsBasePage {
	// foot links
	public static MyWebElement footDiv = myWebelement("cssSelector:div.footlink", "footDiv");
	public static MyWebElement homeLink = myWebelement("linkText:Home", "homeLink");
	public static MyWebElement careersLink = myWebelement("linkText:Careers", "careersLink");	
	public static MyWebElement termsLink = myWebelement("linkText:Terms", "termsLink");		
	public static MyWebElement privacyandSecurityCenterLink = myWebelement("linkText:Privacy and Security Center", "privacyandSecurityCenterLink");
	public static MyWebElement frequentlyAskedQuestionsLink = myWebelement("linkText:Frequently Asked Questions", "frequentlyAskedQuestionsLink");	
	public static MyWebElement creditCardAgreementsLink = myWebelement("linkText:Credit Card Agreements", "creditCardAgreementsLink");	
	public static MyWebElement contactUsLink = myWebelement("linkText:Contact Us", "contactUsLink");

//	public static FootLinks load() {
////		pageTitle = driver.getTitle();
////		print("On " + pageTitle + " page");
//		return new FootLinks();
//	}

		


}
