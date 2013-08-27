package frameLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface MyWebDriver extends WebDriver{
	
	 WebElement findElement(String locator);
}
