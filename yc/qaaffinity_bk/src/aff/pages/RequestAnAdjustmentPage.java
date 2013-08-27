package aff.pages;


import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import applications.BasePage;


public class RequestAnAdjustmentPage extends BasePage{ 
//
//	protected String aff_dataPanelPage_verifyTab_updateBtn = "updateBtn";
//	protected String aff_dataPanelPage_cdp = "cdp_wrap";
//	
	public RequestAnAdjustmentPage(WebDriver driver) {
		super(driver);	
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
	
//
//	public WebElement getCDP()
//	{		
//		return webElementInFramePresent(aff_dataPanelPage_cdp, ACCOUNT_FRAME);		
//	}
//	public WebElement getUpdateButton()
//	{		
//		return webElementInFramePresent(aff_dataPanelPage_verifyTab_updateBtn, ACCOUNT_FRAME);		
//	}	
	
}
