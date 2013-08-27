package fs.pages;
import static frameLib.PrintTestCases.print;
import frameLib.MyWebElement;


public class MobilePayServiceConfirmEditPage extends FsBasePage {

	public static  MyWebElement noButton =  myWebelement("btn_tb_mp_remove_no", "noButton");	
	public static  MyWebElement yesButton =  myWebelement("btn_tb_mp_remove_yes", "yesButton");
	
	public static MobilePayServiceConfirmEditPage load() {
		pageTitle = "MobilePay Service Confirm Edit";
		noButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MobilePayServiceConfirmEditPage();
	}
}
