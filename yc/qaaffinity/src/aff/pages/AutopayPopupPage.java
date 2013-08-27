package aff.pages;

import static frameLib.PrintTestCases.print;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utilityLib.Tools;

import applications.BasePage;

import frameLib.*;


public class AutopayPopupPage extends AffinityBasePage {
	//private String autopayPageTitle = "Autopay";
	public static MyWebElement amount = myWebelement("_jsx_0_c");
	public static MyWebElement changeReasonDropdownList = myWebelement("_jsx_0_f" ,"changeReasonDropdownList");
	public static MyWebElement okButton = myWebelement("xpath://SPAN[contains(.,'OK')]");
	public static MyWebElement cancelAutopayButton = myWebelement("_jsx_0_3b" ,"cancelAutopayButton");
	public static MyWebElement changeAutopayButton = myWebelement("_jsx_0_3c" ,"cancelAutopayButton");
	
	
//	public MyWebElement noButton = myWebelement("name:no");	

	//private static String parentWindowHandle = driver.getWindowHandle();
	
	public static AutopayPopupPage load() {
		pageTitle = "Autopay";
		waitUntilWindowPresent(pageTitle);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new AutopayPopupPage();	
	}

	public static void close()
	{
//		String tempWindowHandler = driver.getWindowHandle();
//		driver.close();
//		driver.switchTo().window(tempWindowHandler);
		((JavascriptExecutor)driver).executeScript("window.onbeforeunload = function(e){};");
		driver.close();	
		waitUntilWindowPresent("Affinity - Autopay");
		//driver.switchTo().window(parentWindowHandle);
	}
	
	public static void cancelAutopay(){
		//changeReasonDropdownList.sendKeys("New Information");
		cancelAutopayButton.waitUntilElementEnabled().click();
		clickAutopayButton("jsx30button_text", "Yes");
		changeAutopayButton.click();
	}
	
	
	public static void clickAutopayButton(String locator, String innerText){
		List<WebElement> buttonList = driver.findElements(By.className(locator));
		for(WebElement e : buttonList){
			if(e.getText().equalsIgnoreCase(innerText)){
				e.click();
			}
		}
	}
	

//	public void clickNoButton() {
//		
////		Long s = System.currentTimeMillis();
//		noButton.click();
//		waitUntilWindowPresent(nonMonHomePageTitle);
////		Long e = System.currentTimeMillis();
////		System.out.println("inside nonmonLink.click()" + (e-s));
////		
////		s = System.currentTimeMillis();
////		waitUntilWindowPresent(parentPageTitle);
////		e = System.currentTimeMillis();
////		System.out.println("waitUntilWindowPresent(parentPageTitle)" + (e-s));
//		
//		
//		//new TestFrameLib(driver).waitUntilWindowPresent(parentPageTitle);
//		//System.out.println(driver.getTitle());
//		//new NonmonHomePage(driver).cliLink.waitUntilElementPresentInWindow(parentPageTitle);
//
//	}		
	
//	public void clickYesButton() {
//		yesButton.click();
//		waitUntilWindowPresent(pageTitle);
//		//new NonmonHomePage(driver).cliLink.waitUntilElementPresentInWindow(parentPageTitle);
//		//switch2Window(parentPageTitle);
//	}	


}
