package aff.pages;


import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;


public class DataPanelPage extends AffinityBasePage{ 
	public MyWebElement accountSecuritySection = myWebelement("account_sec_word_3");
	public MyWebElement vdpSelect = myWebelement("vdp_select");	
	public MyWebElement accountSecurityLinkInVerifyVDP = myWebelement("accountSecurityLinkInVerifyVDP");
	public MyWebElement constantDataPanel = myWebelement("cdp_wrap");
	public MyWebElement variableDataPanel = myWebelement("vdp_wrap");
	public MyWebElement updateButton = myWebelement("updateBtn");		
	public DataPanelPage(WebDriver driver) {
		super(driver);	
		vdpSelect.waitUntilElementEnabledInFrame(ACCOUNT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
	
	
//	protected String verifyTabUpdateButton = "updateBtn";
//	public MyWebElement getUpdateButton()
//	{		
//		return waitMyWebElementInFramePresent(verifyTabUpdateButton, ACCOUNT_FRAME);		
//	}	
	
}
