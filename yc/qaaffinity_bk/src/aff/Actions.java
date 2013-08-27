package aff;

import org.openqa.selenium.WebDriver;

import aff.pages.CriticalNotePage;
import aff.pages.LandingPage;
import aff.pages.LoginPage;
import aff.pages.MainPage;
import aff.pages.NonMonHomePage;
import aff.pages.SearchPage;
import static frameLib.PrintTestCases.*;

public class Actions {
////common function	
	public static void fromSearchPage2AccountSecurityHomePage(WebDriver driver, String accountNumber, String channel)
	{
		funcPrintOn("Advance from SearchPage to AccountSecurityHomePage");
		SearchPage searchPage = new SearchPage(driver);
		searchPage.getChannelRadio(channel).click();
		searchPage.searchByAcctNumber(accountNumber);
		new MainPage(driver).getReady();
		//new MainPage(driver).getReady();
		new CriticalNotePage(driver).nonmonLink.click();
		new NonMonHomePage(driver).accountSecurityLink.click();
		funcPrintOff();
	}


}
