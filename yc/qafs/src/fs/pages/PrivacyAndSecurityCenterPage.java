package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class PrivacyAndSecurityCenterPage extends FsBasePage {
	
	public static PrivacyAndSecurityCenterPage load() {
		pageTitle = "Privacy & Security Center";
		waitUntilContentPresent("1FBUSA Online Privacy and Security Center");
		print("On " + pageTitle + " page");
		return new PrivacyAndSecurityCenterPage();
	}


}
