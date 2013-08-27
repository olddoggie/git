package fs.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;

public class EAutopayManagePaymentAccountsPage extends FsBasePage {
	public static MyWebElement addAccountLink = myWebelement(
			"name:&lid=addAccount", "addAccountLink");
	public static MyWebElement removeAccount1Link = myWebelement(
			"linkText:Remove Account", "addAccountLink", 0);
	// public static MyWebElement firstAccoutInfo =
	// myWebelement("className:acct_info_disp_wrap", "firstAccoutInfo", 0);
	public static MyWebElement firstAccoutInfo = myWebelement(
			"xpath:(//div[@class='acct_info_disp_wrap'])[1]", "firstAccoutInfo");
	public static MyWebElement secondAccoutInfo = myWebelement(
			"xpath:(//div[@class='acct_info_disp_wrap'])[2]",
			"secondAccoutInfo");

	public static MyWebElement firstNameAddressEditLink = myWebelement(
			"xpath:(//a[@name='&lid=editName'])[1]", "firstNameAddressEditLink");
	public static MyWebElement firstBankEditLink = myWebelement(
			"xpath:(//a[@name='&lid=editBankInfo'])[1]", "firstBankEditLink");

	public static MyWebElement enrolledAccountsDiv = myWebelement(
			"className:container_content", "enrolledAccountsDiv");

	public static EAutopayManagePaymentAccountsPage load() {
		pageTitle = "eAutopay Confirm Enrollment Information";
		//waitUntilPageTitlePresent(pageTitle);
		addAccountLink.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayManagePaymentAccountsPage();
	}
}
