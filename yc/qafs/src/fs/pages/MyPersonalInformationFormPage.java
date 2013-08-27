package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class MyPersonalInformationFormPage extends FsBasePage {

	public static MyWebElement address1 = myWebelement("name:newInfo.address1", "address1");
	public static  MyWebElement address2 = myWebelement("name:newInfo.address2", "address2");
	public static  MyWebElement city = myWebelement("name:newInfo.city", "city");
	public static  MyWebElement state = myWebelement("name:newInfo.state", "state");
	public static  MyWebElement zip = myWebelement("name:newInfo.zip", "zip");
	public static  MyWebElement zip4 = myWebelement("name:newInfo.zip4", "zip4");
	public static  MyWebElement homePhoneAreaCode = myWebelement("name:newInfo.homePhone.areaCode", "homePhoneAreaCode");
	public static  MyWebElement homePhonePrefix = myWebelement("name:newInfo.homePhone.prefix", "homePhonePrefix");
	public static  MyWebElement homePhoneSuffix = myWebelement("name:newInfo.homePhone.suffix", "homePhoneSuffix");
	public static  MyWebElement workPhoneAreaCode = myWebelement("name:newInfo.workPhone.areaCode", "workPhoneAreaCode");
	public static  MyWebElement workPhonePrefix = myWebelement("name:newInfo.workPhone.prefix", "workPhonePrefix");
	public static  MyWebElement workPhoneSuffix = myWebelement("name:newInfo.workPhone.suffix", "workPhoneSuffix");
	public static  MyWebElement altrPhoneAreaCode = myWebelement("name:newInfo.altrPhone.areaCode", "altrPhoneAreaCode");
	public static  MyWebElement altrPhonePrefix = myWebelement("name:newInfo.altrPhone.prefix", "altrPhonePrefix");
	public static  MyWebElement altrPhoneSuffix = myWebelement("name:newInfo.altrPhone.suffix", "altrPhoneSuffix");
	public static  MyWebElement primaryEmailAddress = myWebelement("name:newInfo.primaryEmailAddress", "newInfo.primaryEmailAddress");
	public static  MyWebElement alternateEmailAddress = myWebelement("name:newInfo.alternateEmailAddress", "newInfo.alternateEmailAddress");
	public static  MyWebElement onlineStatementEmailOptOutCheckbox = myWebelement("chk_optout", "onlineStatementEmailOptOutCheckbox");
	public static  MyWebElement doNotSubmitButton = myWebelement("uir_donotsubmit", "doNotSubmitButton");
	public static  MyWebElement submitButton = myWebelement("name:&lid=updatePersonalInfo01", "submitButton");

	
	public static MyPersonalInformationFormPage load() {
		pageTitle = "My Personal Information Form";
		submitButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MyPersonalInformationFormPage();
	}

	public static void submitForm(){
		address1.clearSet("950 tanbark street");
		homePhoneAreaCode.clearSet("415");
		homePhonePrefix.clearSet("871");
		homePhoneSuffix.clearSet("8888");
		primaryEmailAddress.clearSet("edit@1fbusa.com");
		submitButton.click();
	}

}
