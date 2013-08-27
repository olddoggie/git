package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;
import static myconstant.AccountDefaultValues.*;



public class EAutopayEnrollAccountPage extends FsBasePage {
	
	public static MyWebElement primaryName_firstNameEditBox = myWebelement("name:primaryName.firstName", "primaryName_firstNameEditBox");
	public static MyWebElement primaryName_middleInitialEditBox = myWebelement("name:primaryName.middleInitial", "primaryName_middleInitialEditBox");
	public static MyWebElement primaryName_lastNameEditBox = myWebelement("name:primaryName.lastName", "primaryName_lastNameEditBox");
	public static MyWebElement secondaryName_firstNameEditBox = myWebelement("name:secondaryName.firstName", "secondaryName_firstNameEditBox");
	public static MyWebElement secondaryName_middleInitialEditBox = myWebelement("name:secondaryName.middleInitial", "secondaryName_middleInitialEditBox");
	public static MyWebElement secondaryName_lastNameEditBox = myWebelement("name:secondaryName.lastName", "secondaryName_lastNameEditBox");
	public static MyWebElement addressLine1EditBox = myWebelement("name:address.addressLine1", "addressLine1EditBox");
	public static MyWebElement addressLine2EditBox = myWebelement("name:address.addressLine2", "addressLine2EditBox");
	public static MyWebElement cityEditBox = myWebelement("name:address.city", "cityEditBox");
	public static MyWebElement stateSelectBox = myWebelement("name:address.state", "stateSelectBox");
	public static MyWebElement zip5EditBox = myWebelement("name:address.zip5", "zip5EditBox");
	public static MyWebElement zip4EditBox = myWebelement("name:address.zip4", "zip4EditBox");
	public static MyWebElement abaNumberEditBox = myWebelement("name:bank.abaNumber", "abaNumberEditBox");
	public static MyWebElement checkingNumberEditBox = myWebelement("name:checkingNumber", "checkingNumberEditBox");
	public static MyWebElement confirmCheckingNumberEditBox = myWebelement("name:confirmCheckingNumber", "confirmCheckingNumberEditBox");
	public static MyWebElement continueButton = myWebelement("name:ap_enroll_continue", "continueButton");



	public static EAutopayEnrollAccountPage load() {
		pageTitle = "eAutopay Enroll Account";
		continueButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayEnrollAccountPage();
	}

	public static void enrollAccount(String abaNumber, String checkingAccountNumber){
		primaryName_firstNameEditBox.clearSet(PRIMARY_FIRST_NAME_DEFAULT);
		primaryName_lastNameEditBox.clearSet(PRIMARY_LAST_NAME_DEFAULT);
		addressLine1EditBox.clearSet(ADDRESS_LINE_1_DEFAULT);
		cityEditBox.clearSet(CITY_DEFAULT);
		stateSelectBox.selectByVisibleText(STATE_DEFAULT);
		zip5EditBox.clearSet(ZIP5_DEFAULT);
		abaNumberEditBox.clearSet(abaNumber);
		checkingNumberEditBox.clearSet(checkingAccountNumber);
		confirmCheckingNumberEditBox.clearSet(checkingAccountNumber);
		continueButton.click();
	}
	
	public static void enrollAccount(){
		//print("Enroll account:" + " aba number=" + ABA_NUMBER_DEFAULT  + " checking account=" + CHECKING_ACCOUNT_NUMBER_DEFAULT);
		enrollAccount(ABA_NUMBER_DEFAULT, CHECKING_ACCOUNT_NUMBER_DEFAULT);
	}

}
