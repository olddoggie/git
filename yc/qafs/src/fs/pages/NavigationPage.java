package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class NavigationPage extends FsBasePage {
	public static MyWebElement accountsLink = myWebelement("name:&lid=account", "accountsLink");
	public static MyWebElement overviewSubLink = myWebelement("name:&lid=accountSummary", "overviewLink");
	public static MyWebElement activitySubLink = myWebelement("name:&lid=accountTransactions", "activityLink");
	public static MyWebElement statementsSubLink = myWebelement("name:&lid=Statements", "statementsLink");
	
	public static MyWebElement eAutopayLink = myWebelement("name:&lid=eautopay", "eAutopayLink");
	public static MyWebElement homeSubLink = myWebelement("name:&lid=epayHome", "homeSubLink");
	public static MyWebElement makePaymentSubLink = myWebelement("name:&lid=makePayment", "makePaymentSubLink");
	public static MyWebElement managetPaymentAccountsSubLink = myWebelement("name:&lid=paymentAccounts", "managetPaymentAccountsSubLink");
	
	public static MyWebElement mailLink = myWebelement("name:&lid=mail", "mailLink");
	public static MyWebElement inBoxSubLink = myWebelement("name:&lid=mailInbox", "inBoxSubLink");
	public static MyWebElement sendMessageSubLink = myWebelement("name:&lid=mailSendMessage", "sendMessageSubLink");
	
	public static MyWebElement serviceLink = myWebelement("name:&lid=customerService", "serviceLink");
	
	public static MyWebElement textBankingLink = myWebelement("name:&lid=textBanking", "textBankingLink");
	public static MyWebElement mobilePaySubLink = myWebelement("cssSelector:button[title='MOBILEPAY']", "mobilePaySubLink");	
	public static MyWebElement freeTextMessageSubLink = myWebelement("cssSelector:button[title='FREE TEXT MESSAGE TERMS AND CONDITIONS']", "freeTextMessageSubLink");	
	
	public static MyWebElement legalDisclosuresLink = myWebelement("name:&lid=legalDisclosures", "legalDisclosuresLink");
	
	public static MyWebElement logOutLink = myWebelement("linkText:Log Out", "logOutLink");	
	//public static MyWebElement makePayment = myWebelement("name:&lid=makePayment", "&lid=makePayment");
	
	public static MyWebElement sidebarDiv = myWebelement("sidebar", "sidebarDiv");


	public static NavigationPage load() {
		pageTitle = driver.getTitle();
		print("On " + pageTitle + " page");
		return new NavigationPage();
	}

		


}
