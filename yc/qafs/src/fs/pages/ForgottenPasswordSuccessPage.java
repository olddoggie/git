package fs.pages;


import static frameLib.PrintTestCases.print;

import static myconstant.General.*;

import frameLib.MyWebElement;
import applications.BasePage;


public class ForgottenPasswordSuccessPage extends BasePage{ 
	
	public static ForgottenPasswordSuccessPage load() {
		pageTitle = "Forgotten Password Success";
		waitUntilContentPresent("Your password has been changed");
		verifyPageTitle(pageTitle);
		print("On " + pageTitle + " page");
		return new ForgottenPasswordSuccessPage();
	}

	
}
