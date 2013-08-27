package fs.pages;
import static frameLib.PrintTestCases.print;
import frameLib.MyWebElement;


public class MobilePayServiceCanclledPage extends FsBasePage {

	public static  MyWebElement addButton =  myWebelement("btn_tb_mp_add", "addButton");	
	
	public static MobilePayServiceCanclledPage load() {
		pageTitle = "MobilePay Service Cancelled";
		addButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MobilePayServiceCanclledPage();
	}
}
