package fs.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class RegistrationTermsPage extends FsBasePage {
	public static MyWebElement iDisagreeButton = myWebelement("cssSelector:button[title='I Disagree']", "iDisagreeButton");
	public static MyWebElement iAgreeButton = myWebelement("cssSelector:button[title='I Agree']", "iAgreeButton");
	public static MyWebElement registrationTermForm = myWebelement("name:registrationTermForm", "registrationTermForm");	

	public static RegistrationTermsPage load() {
		pageTitle = "1FBUSA Online Services Registration Terms";
		registrationTermForm.waitUntilElementFound();
		verifyPageTitle(pageTitle);
		print("On " + pageTitle + " page");
		return new RegistrationTermsPage();
	}
	

}
