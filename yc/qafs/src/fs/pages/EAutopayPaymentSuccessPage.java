package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import frameLib.MyWebElement;

import applications.BasePage;


public class EAutopayPaymentSuccessPage extends FsBasePage {
	
	//This element contains confirmation number, payto payfrom, payamount and paydate
	public static MyWebElement paymentInfoList = myWebelement("className:acct_info_list", "paymentInfoList");	
//	public static MyWebElement paymentInfoList = myWebelement("cssSelector:[class='acct_info_list']", "paymentInfoList");
	//public static MyWebElement paymentInfoList = myWebelement("cssSelector:ul.acct_info_disp_wrap", "paymentInfoList");
	
	public static MyWebElement confirmationNumberValue = myWebelement("className:acct_info_list","confirmationNumberValue", "xPath:li[2]");	
	public static MyWebElement payToValue = myWebelement("className:acct_info_list","payToValue", "xPath:li[4]");	
//	public static MyWebElement payToLabelValue = myWebelement("className:acct_info_list", "payToValue","xPath:li[4]");	
	public static MyWebElement payFromValue = myWebelement("className:acct_info_list", "payFromValue", "xPath:li[6]");
	public static MyWebElement paymentAmountValue = myWebelement("className:acct_info_list","paymentAmountValue", "xPath:li[8]");	
	public static MyWebElement payDateValue = myWebelement("className:acct_info_list","payDateValue", "xPath:li[10]");	


	public static EAutopayPaymentSuccessPage load() {
		pageTitle = "eAutopay Payment Success";
		paymentInfoList.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayPaymentSuccessPage();
	}


}
