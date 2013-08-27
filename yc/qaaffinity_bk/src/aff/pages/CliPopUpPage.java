package aff.pages;

import static frameLib.PrintTestCases.print;
import static frameLib.TestFrameLib.*;
import org.openqa.selenium.WebDriver;
import applications.BasePage;

import frameLib.*;


public class CliPopUpPage extends AffinityBasePage {
	//this is the page title of both the popup window and the landing page title after yesButton is clicked
	private String pageTitle = "Affinity - Credit Line Increase";
	//this is the page title when noButton is clicked
	private String nonMonHomePageTitle = "Affinity - Nonmon Home Page";
	public MyWebElement yesButton = myWebelement("name:yes");
	public MyWebElement noButton = myWebelement("name:no");	
	private String parentWindowHandle = driver.getWindowHandle();
	
	public CliPopUpPage(WebDriver driver) {
		super(driver);		
		waitUntilWindowPresent(pageTitle);
		//new TestFrameLib(driver).waitUntilWindowPresent(pageTitle);
		//new TestFrameLib(driver).switch2Window(pageTitle);		
		//yesButton.waitUntilElementPresentInWindow(pageTitle);
		//print("On " + pageTitle + " page");
	}

	
	public void clickNoButton() {
		
//		Long s = System.currentTimeMillis();
		noButton.click();
		driver.switchTo().window(parentWindowHandle);
		//waitUntilWindowPresent(nonMonHomePageTitle);
		
//		Long e = System.currentTimeMillis();
//		System.out.println("inside nonmonLink.click()" + (e-s));
//		
//		s = System.currentTimeMillis();
//		waitUntilWindowPresent(parentPageTitle);
//		e = System.currentTimeMillis();
//		System.out.println("waitUntilWindowPresent(parentPageTitle)" + (e-s));
		
		
		//new TestFrameLib(driver).waitUntilWindowPresent(parentPageTitle);
		//System.out.println(driver.getTitle());
		//new NonmonHomePage(driver).cliLink.waitUntilElementPresentInWindow(parentPageTitle);

	}		
	
	public void clickYesButton() {
		yesButton.click();
		driver.switchTo().window(parentWindowHandle);
		//waitUntilWindowPresent(pageTitle);
		
		//new NonmonHomePage(driver).cliLink.waitUntilElementPresentInWindow(parentPageTitle);
		//switch2Window(parentPageTitle);
	}	


}
