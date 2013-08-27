package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class Permission2DiscussPage extends AffinityBasePage {
	public MyWebElement firstNameEditBox = myWebelement("p2d_add_first");
	public MyWebElement lastNameEditBox = myWebelement("p2d_add_last");
	public MyWebElement relationshipEditBox = myWebelement("p2d_add_relation");
	public MyWebElement effectiveMonth = myWebelement("dt_granted_month");
	public MyWebElement effectiveDay = myWebelement("dt_granted_day");
	public MyWebElement effectiveYear = myWebelement("dt_granted_year");	
	
	public MyWebElement firstRemoveCheckBox = myWebelement("p2d_dt_remove0");
	public MyWebElement firstEditReleationship = myWebelement("p2d_edit_relation1");
	public MyWebElement secondRemoveCheckBox = myWebelement("p2d_dt_remove1");
	public MyWebElement ActiveP2DTable = myWebelement("table_p2d_remove_edit");
	
	public MyWebElement submitButton = myWebelement("p2d_submit");
	public MyWebElement clearButton = myWebelement("p2d_clear");
	public MyWebElement terminateButton = myWebelement("p2d_terminate");
	public MyWebElement doneButton = myWebelement("p2d_done");	
	
	
	public Permission2DiscussPage(WebDriver driver) {
		super(driver);
		pageTitle = "Affinity - Permission to Discuss";
//		switchToFrame(CONTENT_FRAME);
//		submitButton.waitUntilElementPresent();
		submitButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		verifyPageTitle(pageTitle);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
	
	public void addPermission(String firstName, String secondName, String reletionship)
	{
		firstNameEditBox.clearSet(firstName);
		lastNameEditBox.clearSet(secondName);
		relationshipEditBox.selectByVisibleText(reletionship);
		submitButton.click();
	}
	
	public void removeFirstPermission()
	{
		firstRemoveCheckBox.check();
		submitButton.click();
	}

}
