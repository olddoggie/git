package aff.pages;

import org.openqa.selenium.WebDriver;
import frameLib.*;
import static frameLib.PrintTestCases.*;

public class AccountSecurityConfirmPage extends AffinityBasePage {
	public MyWebElement okButton = myWebelement("security_ok");
	public MyWebElement accountSecurityLeftTable = myWebelement("column_left", "accountSecurityLeftTable");
	public MyWebElement scriptRightTable = myWebelement("column_right", "scriptRightTable");
	
	public AccountSecurityConfirmPage(WebDriver driver) {
		super(driver);
		scriptRightTable.waitUntilElementEnabledInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
