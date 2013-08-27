package fs.pages;
import static frameLib.PrintTestCases.print;
import frameLib.MyWebElement;


public class MobilePayServiceEnrolledPage extends FsBasePage {

	public static  MyWebElement contentBlock =  myWebelement("className:container_content", "submitButton");	
	public static  MyWebElement editThisInformationButton =  myWebelement("btn_tb_mp_edit", "editThisInformationButton");
	public static  MyWebElement cancelMobilePayServiceButton =  myWebelement("btn_tb_mp_cancel", "cancelMobilePayServiceButton");	
	
	public static MobilePayServiceEnrolledPage load() {
		pageTitle = "MobilePay Service Enrolled";
		editThisInformationButton.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new MobilePayServiceEnrolledPage();
	}
}
