package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class AccountSecurityHomePage extends AffinityBasePage {	
	public MyWebElement accountSecurityTab = myWebelement("accountSecurityTab");
	public MyWebElement securityHintDropDown = myWebelement("secqlist");
	public MyWebElement securityWordEditBox = myWebelement("secans");
	public MyWebElement otherSecurityHintEditBox = myWebelement("othersecans");
	public MyWebElement effectiveMonthEditBox = myWebelement("effDtMonth");
	public MyWebElement effectiveDayEditBox = myWebelement("effDtDay");
	public MyWebElement effectiveYearEditBox = myWebelement("effDtYear");
	public MyWebElement sendLetterButton = myWebelement("send_letter_id");
	public MyWebElement submitButton = myWebelement("submit_id");
	public MyWebElement historyButton = myWebelement("history_id");
	public MyWebElement cancelButton = myWebelement("cancel_id");
	
	public AccountSecurityHomePage(WebDriver driver) {

		super(driver);		
		submitButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = "Affinity - Account Security";
		verifyPageTitle(pageTitle);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}

	public void addUpdateRemoveSWQuick(String securityHint) {
		addUpdateRemoveSW(securityHint, "", "yu", "01", "01", "2011");
	}
	
	public void addUpdateRemoveSW(String securityHint,String otherSecurityHint, String securityWord,
			String effectiveMonth, String effectiveDay, String effectiveYear) {
		securityHintDropDown.selectByVisibleText(securityHint);
		if (!otherSecurityHint.isEmpty()) {
			otherSecurityHintEditBox.clearSet(otherSecurityHint);
		}		
		securityWordEditBox.clearSet(securityWord);
		effectiveMonthEditBox.clearSet(effectiveMonth);	
		effectiveDayEditBox.clearSet(effectiveDay);
		effectiveYearEditBox.clearSet(effectiveYear);
		submitButton.click();
	}	

}
