package fs.pages;
import static frameLib.PrintTestCases.print;
import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;

public class EAutopayMakeAPaymentPage extends FsBasePage {
	
	public static MyWebElement addAccountLink = myWebelement("cssSelector:a[title='Add Account']", "addAccountLink");
	public static MyWebElement editAccountLink = myWebelement("cssSelector:a[title='Edit Account']", "editAccountLink");
	public static MyWebElement editAddressLink = myWebelement("cssSelector:a[title='Edit Address']", "editAddressLink");	
	public static MyWebElement minimumPaymentDueRadioButton = myWebelement("cssSelector:input[value='PMT_AMTS_MPD']", "minimumPaymentDueRadioButton");	
	public static MyWebElement currentBalanceRadioButton = myWebelement("cssSelector:input[value='PMT_AMTS_CB']", "currentBalanceRadioButton");	
	public static MyWebElement lastStatementBalanceRadioButton = myWebelement("cssSelector:input[value='PMT_AMTS_LSB']", "lastStatementBalanceRadioButton");	
	public static MyWebElement otherAmountRadioButton = myWebelement("cssSelector:input[value='PMT_AMTS_OTHER']", "otherAmountRadioButton");
	public static MyWebElement otherAmountEditBox = myWebelement("paymentAmountOther", "otherAmountEditBox");
	public static MyWebElement dueDateValue = myWebelement("due_date", "dueDateValue");
	public static MyWebElement paymentDateEditBox = myWebelement("datepicker", "paymentDateEditBox");
	public static MyWebElement continueButton = myWebelement("name:ap_make_pmt_cont", "continueButton");
	public static MyWebElement sendACHrequestFormCheckBox = myWebelement("sendACHrequest", "sendACHrequest");
	public static MyWebElement calendarImage = myWebelement("className:ui-datepicker-trigger", "calendarImage");
	
	public static MyWebElement mpdAmount = myWebelement("className:pmt_amt_b", "mpdAmount", 0);
	public static MyWebElement lastStatementBalanceAmount = myWebelement("className:pmt_amt_b", "lastStatementBalanceAmount", 1);
	public static MyWebElement currentBalanceAmount = myWebelement("className:pmt_amt_b", "currentBalanceAmount", 2);
	
	public static EAutopayMakeAPaymentPage load() {
		pageTitle = "eAutopay Make a Payment";
		otherAmountEditBox.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new EAutopayMakeAPaymentPage();
	}

	public static void makePayment(String paymentDate, String paymentType, String paymentAmount ){
		//print("Make a payment:" + " paymentDate=" + paymentDate  + " paymentType=" + paymentType  + " paymentAmount=" + paymentAmount);
		if(paymentType.toLowerCase().contains("minimum")){
			minimumPaymentDueRadioButton.check();
		}else if(paymentType.toLowerCase().contains("last")){
			lastStatementBalanceRadioButton.check();
		}else if(paymentType.toLowerCase().contains("current")){
			currentBalanceRadioButton.check();
		}else{
			otherAmountRadioButton.check();
			otherAmountEditBox.clearSet(paymentAmount);
		}
		paymentDateEditBox.clearSet(paymentDate);
		//the first click close the calendar window. the second click is the real click
		calendarImage.click();
		continueButton.click();
	}
	
	public static void makePaymentQuick(String paymentDate, String paymentType, String paymentAmount ){
		//print("Make a payment:" + " paymentDate=" + paymentDate  + " paymentType=" + paymentType  + " paymentAmount=" + paymentAmount);
		if(paymentType.toLowerCase().contains("minimum")){
			minimumPaymentDueRadioButton.check();
		}else if(paymentType.toLowerCase().contains("last")){
			lastStatementBalanceRadioButton.check();
		}else if(paymentType.toLowerCase().contains("current")){
			currentBalanceRadioButton.check();
		}else{
			otherAmountRadioButton.check();
			otherAmountEditBox.clearSet(paymentAmount);
		}
		paymentDateEditBox.clearSet(paymentDate);
		//the first click close the calendar window. the second click is the real click
		calendarImage.click();
		continueButton.click();
	}
	
	
}
