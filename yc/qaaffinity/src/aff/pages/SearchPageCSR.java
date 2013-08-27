package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class SearchPageCSR extends AffinityBasePage {
	protected String searchPageTitle = "Affinity Search";

	public static MyWebElement accountIdEditBox = myWebelement("accountID", "accountIdEditBox");
	public static MyWebElement searchByAcctIdButton = myWebelement("searchByAcctID", "searchByAcctIdButton");
	public static MyWebElement accountNumEditBox = myWebelement("acctNum", "accountNumEditBox");
	public static MyWebElement searchByAcctNumberButton = myWebelement("submitButtonName", "searchByAcctNumberButton");
	public static MyWebElement autoPullOptionCheckbox = myWebelement("autoPullOption", "autoPullOptionCheckbox");
	
	public static MyWebElement inboundRadio = myWebelement("radioINB");
	public static MyWebElement transferRadio = myWebelement("radioTRN");
	public static MyWebElement outboundRadio = myWebelement("radioOUT");
	public static MyWebElement correspondenceRadio = myWebelement("radioCOR");
	public static MyWebElement webRadio = myWebelement("radioWEB");
	public static MyWebElement researchRadioButton = myWebelement("radioRES", "researchRadioButton");
	public static MyWebElement otherRadio = myWebelement("radioOTH");
	
	public static MyWebElement customerDisc = myWebelement("customerDisc");
	public static MyWebElement vruInfo = myWebelement("vruInfo");
	public static MyWebElement search_34_info = myWebelement("search_34_info");
	public static MyWebElement last4OfCard = myWebelement("last4OfCard");
	public static MyWebElement last4OfSSN = myWebelement("last4OfSSN");
	public static MyWebElement monthOfBirth = myWebelement("monthOfBirth");
	public static MyWebElement dayOfBirth = myWebelement("dayOfBirth");
	public static MyWebElement yearOfBirth = myWebelement("yearOfBirth");
	public static MyWebElement billingZip = myWebelement("billingZip");
	public static MyWebElement searchBy3Of4Fields = myWebelement("searchBy3Of4Fields");
	public static MyWebElement clearbutton = myWebelement("clearbutton");
	public static MyWebElement pullFromFdrButton = myWebelement("pullFromFDR");
	public static MyWebElement searchErr = myWebelement("searchErr");
	public static MyWebElement main_search = myWebelement("main_search");
	public static MyWebElement mainframe_search = myWebelement("mainframe_search");
	public static MyWebElement formgroup = myWebelement("formgroup");
		
	public static SearchPageCSR load() {
		searchByAcctIdButton.waitUntilElementPresent();
		accountNumEditBox.waitUntilElementEnabled();
		//verifyPageTitle(searchPageTitle);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new SearchPageCSR();
	}
	
	public static void searchByAcctNumber(String accountNumber) {
		accountNumEditBox.sendKeys(accountNumber);
		searchByAcctNumberButton.waitUntilElementEnabled().click();
		
	}

	public static void searchByAcctID(String accountID) {
		accountIdEditBox.sendKeys(accountID);
		searchByAcctIdButton.waitUntilElementEnabled().click();
	}

	public static void uncheckAutoPullOn() {
		autoPullOptionCheckbox.waitUntilElementChecked().uncheck();
	}

	public static MyWebElement getChannelRadio(String channel) {
		return myWebelement(channel);
	}
}

