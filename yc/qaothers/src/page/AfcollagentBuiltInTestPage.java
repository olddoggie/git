package page;

import frameLib.MyWebElement;
import applications.BasePage;
import static frameLib.PrintTestCases.print;

public class AfcollagentBuiltInTestPage extends BasePage {

	public static MyWebElement afServiceFrame =  myWebelement("afService", "afServiceFrame");
	public static AfcollagentBuiltInTestPage load() {
		waitUntilPageReady();
		afServiceFrame.waitUntilElementPresent();
		pageTitle = driver.getTitle();
		print("On " + pageTitle + " page");
		return new AfcollagentBuiltInTestPage();
	}
	
	
}
