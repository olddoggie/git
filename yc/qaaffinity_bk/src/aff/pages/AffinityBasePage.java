package aff.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import applications.BasePage;
public class AffinityBasePage extends BasePage {
	
	public static final String NOTE_FRAME = "_note";
	public static final String CONTENT_FRAME = "_content";
	public static final String ACCOUNT_FRAME = "_account";

	public static void closeAffinityBrowser(){
		driver.switchTo().defaultContent();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This js function will suppress the confirmation window
		js.executeScript("window.onbeforeunload = function(e){};");
		driver.close();

		////This is another way to close affinity window with the alert box on		
//		js.executeScript("window.close()");
//		wait.until(new AlertPresent()).accept();
	}
}
