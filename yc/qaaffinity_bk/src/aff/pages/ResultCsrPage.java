package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class ResultCsrPage extends AffinityBasePage {
	public MyWebElement securityActivityCheckbox = myWebelement("ID_Activity_022");	
	public MyWebElement AchActivityCheckbox = myWebelement("ID_Activity_031");		
	public MyWebElement contactCardMemberRadio = myWebelement("ID_Contact_001");	
	public MyWebElement completeCallButton = myWebelement("complete");
	public MyWebElement clearButton = myWebelement("result_clear");	
	
	public ResultCsrPage(WebDriver driver) {
		super(driver);
		clearButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
	public void resolveCall()
	{		
		contactCardMemberRadio.click();
		AchActivityCheckbox.click();
		completeCallButton.click();
	}
	

}
