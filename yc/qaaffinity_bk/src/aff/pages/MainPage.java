package aff.pages;


import static frameLib.PrintTestCases.print;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utilityLib.Tools;

import frameLib.*;


public class MainPage extends AffinityBasePage{ 
	public MyWebElement navigationPageSearchLink = myWebelement("xpath://a[@title='Ctrl+Alt+s']");	
	public MyWebElement notePageSubmitButton = myWebelement("addNoteButton");	
	public MyWebElement accountPageVdpDropDown = myWebelement("vdp_select");	
	public MyWebElement navigationPageCriticalNoteTab = myWebelement("criticalTab");	
	public MyWebElement criticalNoteNotesDataTable = myWebelement("notesDataTable");	

	public MainPage(WebDriver driver) {
		super(driver);	
		//getReady();
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("Wait " + pageTitle + " page to load");
	}
	
	public void getReady()
	{
		criticalNoteNotesDataTable.waitUntilElementPresentInFrame(CONTENT_FRAME);
		notePageSubmitButton.waitUntilElementPresentInFrame(NOTE_FRAME);
		accountPageVdpDropDown.waitUntilElementPresentInFrame(ACCOUNT_FRAME);
	}
	
//	public void closeAffinityBrowserBackup1(){
//		String pageTitle = driver.getTitle();
//		driver.get("http://qa2support.1fbusa.com/afcollagent/");
//		if (!(pageTitle.equals("Affinity Login")||pageTitle.equals("Affinity")))
//		{
//			wait.until(new AlertPresent()).accept();
//		}
//		myWebelement("name:ntLogin").waitUntilElementFound();
//		// driver.close();
//		driver.quit();
//	}
//	public void closeAffinityBrowserBackup2(){
//		driver.switchTo().defaultContent();
//		driver.close();
//		 //Thread.sleep(500);	
//		Tools.pressEnterKey();
//	}	

	
}
