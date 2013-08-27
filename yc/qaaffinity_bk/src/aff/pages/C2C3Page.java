package aff.pages;

import static frameLib.PrintTestCases.print;

import org.openqa.selenium.WebDriver;
import frameLib.*;

public class C2C3Page extends AffinityBasePage {	

	public MyWebElement cardTypeDropDown = myWebelement("cardType");
	public MyWebElement debitCreditCardEditBox = myWebelement("debitCreditCard");
	public MyWebElement accountNumberFst4DigitsEditBox = myWebelement("accountNumberFst4Digigts");
	public MyWebElement accountNumberSnd4DigitsEditBox = myWebelement("accountNumberSnd4Digigts");
	public MyWebElement accountNumberTrd4DigitsEditBox = myWebelement("accountNumberTrd4Digigts");
	public MyWebElement expirationMonthEditBox = myWebelement("expirationMonth");
	public MyWebElement expirationYearEditBox = myWebelement("expirationYear");
	public MyWebElement nameOnCardEditBox = myWebelement("nameOnCard");
	public MyWebElement nickNameEditBox = myWebelement("nickName");
	public MyWebElement relationshipDropDown = myWebelement("relationship");
	public MyWebElement billingAddress1EditBox = myWebelement("address1");
	public MyWebElement Pull1FBAddrButton = myWebelement("Pull1FBAddr");
	public MyWebElement billingAddress2EditBox = myWebelement("address2");
	public MyWebElement cityEditBox = myWebelement("city");
	public MyWebElement stateEditBox = myWebelement("state");
	public MyWebElement zip1EditBox = myWebelement("zip1");
	public MyWebElement zip2EditBox = myWebelement("zip2");
	public MyWebElement phone1EditBox = myWebelement("phone1");
	public MyWebElement phone2EditBox = myWebelement("phone2");
	public MyWebElement phone3EditBox = myWebelement("phone3");
	public MyWebElement storeDetailYesRadioBox = myWebelement("cssSelector:input[name=retainAcct][value=Yes]");
	public MyWebElement storeDetailNoRadioBox = myWebelement("cssSelector:input[name=retainAcct][value=No]");
	public MyWebElement scriptTable = myWebelement("column_right");
	public MyWebElement nextButton = myWebelement("next");
	public MyWebElement restartButton = myWebelement("restart");
	public MyWebElement terminateButton = myWebelement("terminate");


	
	public C2C3Page(WebDriver driver) {
		super(driver);
		//Enroll Source Account
		pageTitle = "Affinity - C2C";
		terminateButton.waitUntilElementPresentInFrame(CONTENT_FRAME);
		pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
	}
}
