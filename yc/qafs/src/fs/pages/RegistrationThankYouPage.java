package fs.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class RegistrationThankYouPage extends FsBasePage {
	
	public static RegistrationThankYouPage load() {
		pageTitle = "1FBUSA Online Services Registration Thank You";
		print("On " + pageTitle + " page");
		return new RegistrationThankYouPage();
	}


}
