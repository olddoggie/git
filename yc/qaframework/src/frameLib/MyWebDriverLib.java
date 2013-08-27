package frameLib;

import java.util.List;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyWebDriverLib {
	protected WebDriver driver;
	//protected WebDriverWait wait;
	//protected Properties appConfigures;

	public void setValue(By by, String s)
	{
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(s);
	}
	
	public MyWebDriverLib(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void selectWindow(String title) {
		Set<String> handlers = driver.getWindowHandles();
		if (driver.getWindowHandles().size() >= 1) {
			for (String handler : handlers) {
				driver.switchTo().window(handler);
				if (driver.getTitle().contains(title)) {
					// System.out.println("Get focus on Affinity Login window");
					break;
				}
			}
		} else {
			System.out.println("No windows founded!");
		}
	}

	// public boolean IsTextPresent(WebElement we, String s) {
	// return we.getText().contains(s);
	// }

	public String getWebElementsText(List<WebElement> myList) {
		StringBuilder s = new StringBuilder();
		for (WebElement e : myList) {
			s.append(e.getText());
			s.append("~~");
		}

		return s.toString();
	}

	public static void mySleep(double seconds) {
		long time = System.currentTimeMillis();
		while (System.currentTimeMillis() < (time + seconds * 1000)) {

		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

//	public static void callOracleSql(String strSql) {
//		DatabaseCall.callOracle(strSql, appConfigures.getProperty("oracleURL"), appConfigures.getProperty("oracleUserName"), appConfigures.getProperty("oraclePassword"));
//	}
//
//	public static void callODS_RPC_Sql(String strSql)
//	{
//		DatabaseCall.callODS_RPC(strSql, appConfigures.getProperty("ODS_URL"), appConfigures.getProperty("userName"), appConfigures.getProperty("password"));
//	}
	// public WebDriver getDriver() {
	// return driver;
	// }
	//
	// public void setDriver(WebDriver driver) {
	// this.driver = driver;
	// }

	// public WebDriverWait getWait() {
	// return wait;
	// }
	//
	// public void setWait(WebDriverWait wait) {
	// this.wait = wait;
	// }
	//
	//
	// public Properties getAppConfigures() {
	// return appConfigures;
	// }
	//
	//
	// public void setAppConfigures(Properties appConfigures) {
	// this.appConfigures = appConfigures;
	// }
	//	

}
