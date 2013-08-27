package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;

public class AffNavigationPage extends AffinityBasePage{
	public static MyWebElement errorMessage = myWebelement("error_display","errorMessage");
	public static MyWebElement searchLink = myWebelement("xpath://a[@title='Ctrl+Alt+s']","searchLink");	
	public static MyWebElement condensedTab = myWebelement("condensedTab","condensedTab");
	public static MyWebElement criticalTab = myWebelement("criticalTab","criticalTab");	
	public static MyWebElement expandedTab = myWebelement("expandedTab","expandedTab");	
	public static MyWebElement transactionSubTab = myWebelement("transactionSubTab","transactionSubTab");	
	public static MyWebElement autopaySubTab = myWebelement("autopaySubTab","autopaySubTab");
	public static MyWebElement c2cTab = myWebelement("C2CSubTab","c2cTab");
	
	public static MyWebElement notesLink = myWebelement("xpath://a[@title='Ctrl+Alt+n']","notesLink");
	public static MyWebElement resultLink = myWebelement("xpath://a[@title='Ctrl+Alt+r']","resultLink");	
	public static MyWebElement nonmonLink = myWebelement("xpath://a[@title='Ctrl+Alt+o']","nonmonLink");	
	public static MyWebElement monLink = myWebelement("xpath://a[@title='Ctrl+Alt+m']","monLink");
	public static MyWebElement paymentLink = myWebelement("xpath://a[@title='Ctrl+Alt+p']","paymentLink");	

	
	public static AffNavigationPage load() {
		nonmonLink.waitUntilElementPresentInFrame(CONTENT_FRAME);
		//errorMessage.waitUntilElementFound();
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new AffNavigationPage();
	}
}
