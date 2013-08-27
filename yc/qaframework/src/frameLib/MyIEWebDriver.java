package frameLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class MyIEWebDriver extends InternetExplorerDriver implements MyWebDriver{

	public WebElement findElement(String locator)
	{
		String[] lArr = locator.split(":");
		String by = lArr[0];
		String using = lArr[1];
		return findElement(by, using);
	}
	
	
	
	
//	  public WebElement findElement(String by, String using) {
//		    if (using == null) {
//		      throw new IllegalArgumentException("Cannot find elements when the selector is null.");
//		    }
//
//		    Response response = execute(DriverCommand.FIND_ELEMENT,
//		        ImmutableMap.of("using", by, "value", using));
//		    return (WebElement) response.getValue();
//		  }

}
