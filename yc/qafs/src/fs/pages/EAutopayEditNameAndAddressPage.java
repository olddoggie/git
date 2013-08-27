package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.FrameUtilities;
import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayEditNameAndAddressPage extends FsBasePage {
	
	public static MyWebElement primaryName_firstNameEditBox = myWebelement("name:primaryName.firstName", "primaryName_firstNameEditBox");
	public static MyWebElement primaryName_middleInitialEditBox = myWebelement("name:primaryName.middleInitial", "primaryName_middleInitialEditBox");
	public static MyWebElement primaryName_lastNameEditBox = myWebelement("name:primaryName.lastName", "primaryName_lastNameEditBox");
	public static MyWebElement secondaryName_firstNameEditBox = myWebelement("name:secondaryName.firstName", "secondaryName_firstNameEditBox");
	public static MyWebElement secondaryName_middleInitialEditBox = myWebelement("name:secondaryName.middleInitial", "secondaryName_middleInitialEditBox");
	public static MyWebElement secondaryName_lastNameEditBox = myWebelement("name:secondaryName.lastName", "secondaryName_lastNameEditBox");
	public static MyWebElement addressLine1EditBox = myWebelement("name:address.addressLine1", "addressLine1EditBox");
	public static MyWebElement addressLine2EditBox = myWebelement("name:address.addressLine2", "addressLine2EditBox");
	public static MyWebElement cityEditBox = myWebelement("name:address.city", "cityEditBox");
	public static MyWebElement stateEditBox = myWebelement("name:address.state", "stateEditBox");
	public static MyWebElement zip5EditBox = myWebelement("name:address.zip5", "zip5EditBox");
	public static MyWebElement zip4EditBox = myWebelement("name:address.zip4", "zip4EditBox");
	
	public static MyWebElement editButton = myWebelement("name:ap_editperson_edit", "editButton");
	public static MyWebElement doNotEditButton = myWebelement("name:ap_editperson_donotedit", "dnNotEditButton");



	public static EAutopayEditNameAndAddressPage load() {
		pageTitle = "eAutopay Edit Name(s) and Address on Check";
		editButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayEditNameAndAddressPage();
	}

	public static void editPersonInfo(String address, String city, String zipCode){
		addressLine1EditBox.clearSet(address);
		cityEditBox.clearSet(city);
		zip5EditBox.clearSet(zipCode);
		editButton.click();
	}

}
