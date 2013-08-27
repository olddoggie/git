package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class CustomerServiceHomePage extends FsBasePage {
	public static MyWebElement accountPaperlessSettingLink =  myWebelement("linkText:Account Paperless Setting", "accountPaperlessSettingLink");
	public static MyWebElement submitMyPersonalInformationLink =  myWebelement("name:&lid=updateInfoBanner", "submitMyPersonalInformationLink");
	public static MyWebElement changePasswordLink =  myWebelement("name:&lid=changePasswordBanner", "changePasswordLink");
	public static MyWebElement overLimitServiceLink =  myWebelement("linkText:Over Limit Service", "overLimitServiceLink");
	

	public static CustomerServiceHomePage load() {
		pageTitle = "Customer Service Home";
		changePasswordLink.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new CustomerServiceHomePage();
	}

}
