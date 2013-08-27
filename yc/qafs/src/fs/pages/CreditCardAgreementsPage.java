package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class CreditCardAgreementsPage extends FsBasePage {
	public static  MyWebElement viewCreditCardAgreementsOffedToThePublicLink =  myWebelement("linkText:View Credit Card Agreements offered to the public", "viewCreditCardAgreementsOffedToThePublicLink");
	
	public static CreditCardAgreementsPage load() {
		pageTitle = "Credit Card Agreements";
		viewCreditCardAgreementsOffedToThePublicLink.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new CreditCardAgreementsPage();
	}


}
