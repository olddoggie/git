package aff.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import database.DatabaseBase;
import database.OdsSql;

import utilityLib.*;

import aff.Utilities;
import aff.bat.CSRTest;
import aff.pages.*;
import applications.BasePage;
import frameLib.*;
import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;
public class test {
	
	public static void  loginAffinity(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		BasePage.setWait(wait);
		
		LandingPage landingPage = new LandingPage(driver);		
		landingPage.open(CSR_URL);

		
		LoginPage  loginPage  = new LoginPage(driver);
		loginPage.loginAff(ODS_USER_NAME,ODS_PASSWORD);
	}
	

	public static void main(String[] args) 
    {  	
		OdsSql.updateMis13("4017241476060341",46, 1, "1");
		//DatabaseBase.callOracle("aaa");
		System.out.println("aaa");
		
		

//		String a =new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(new Date());
//		System.out.println(a);
//		String locator  = "cssSelector:tr:contains('x5677')";
//		String[] lArr = locator.split(":");
//		String by = lArr[0];
//		String using =locator.substring(by.length()+1);
//
//		
//		
//		WebDriver driver = new InternetExplorerDriver();
//		loginAffinity(driver);
//		new SearchPage(driver).searchByAcctID("500007");
//		//MyWebDriverLib.mySleep(5);
//		Utilities.resetAffinity();
//		loginAffinity(driver);
//		MyWebDriverLib.mySleep(5);
//		Utilities.resetAffinity();
//		
//		SearchPage searchPage = new SearchPage(driver);
//		searchPage.clickResearchRadio("csr");
//		searchPage.uncheckAutoPullOn();
//		searchPage.searchByAcctID(appConfigures.getProperty("accountID"));
//		MainPage mainPage = new MainPage(driver);
//		mainPage.getReady();
//		assertTrue("landing page is critical notes", driver.getTitle().contains("Critical Notes"));	
// 
//		
//		String regularNote = "regular" + (new Date()).toString().toUpperCase();
//		AddNotePage notePage = new AddNotePage(driver);
//		notePage.addNote(regularNote, 3);
//		
//		mainPage.getReady();
//		
//		new MainPage(driver).closeAffinityBrowser();
//		
//		CondensedNotePage condensedNotePage = new CondensedNotePage(driver);		
//		condensedNotePage.SearchNote("AP");
//		condensedNotePage.SearchNote("<ALL>");
//		condensedNotePage.getNaviSearchLink().click();
//		condensedNotePage.getNaviCriticalNoteTab().click();
//		condensedNotePage.SearchNote("AP");
		
		
		
//		CondensedNotePage.SearchForm condensedSearchForm = condensedNotePage.new SearchForm();
//		condensedSearchForm.setSource("ALL");
//		condensedNotePage.SearchNote(condensedSearchForm);
//		condensedSearchForm.rollback().setType("All");
//		condensedNotePage.SearchNote(condensedSearchForm);
		

	
//		String internationalNote = "international" + (new Date()).toString().toUpperCase();
//		//notePage = PageFactory.initElements(driver, NotePage.class);
//		notePage.AddNote(internationalNote, 2);
//		
//
//		CondensedNotePage condensedNotePage = PageFactory.initElements(driver, CondensedNotePage.class);	
//		condensedNotePage.SearchNote();
		
		
		
//		assertTrue(driver.findElement(By.tagName("body")).getText().contains("regular"));
//		assertTrue(driver.findElement(By.tagName("body")).getText().contains("critical"));
//		assertTrue(driver.findElement(By.tagName("body")).getText().contains("international"));
//		String criticalNote = "critical" + (new Date()).toString().toUpperCase();
//		notePage = PageFactory.initElements(driver, NotePage.class);
//		notePage.AddNote(criticalNote, 3);
		
		//regularNote = "regular" + (new Date()).toString().toUpperCase();
		//notePage = PageFactory.initElements(driver, NotePage.class);
		//notePage.AddNote(regularNote, 1);
    } 
}
