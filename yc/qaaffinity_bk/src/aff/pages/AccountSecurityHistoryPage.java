package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class AccountSecurityHistoryPage extends AffinityBasePage {
	public MyWebElement historyTable = myWebelement("xpath://table[@class='acctsec_hist_table']", "historyTable");
	public MyWebElement historyTableBody = myWebelement("xpath://table[@class='acctsec_hist_table']/tbody", "historyTableBody");
	public MyWebElement backButton = myWebelement("back");
	
	public AccountSecurityHistoryPage(WebDriver driver) {
		super(driver);
		historyTable.waitUntilElementEnabledInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
