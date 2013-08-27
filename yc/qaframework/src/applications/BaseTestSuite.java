package applications;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utilityLib.Tools;

import frameLib.MyException;
import frameLib.MyWebElement;
//import static properties.Common.*;
//This will be the base class for test case suite class
public class BaseTestSuite {
	// protected AFService service;
//	protected WebDriver driver;
//	protected WebDriverWait wait;
//	protected String expected;
//	protected String actual;
//	protected boolean expectedBoolean;
//	protected boolean actualBoolean;	
	//protected boolean testPassed = true;
	
	//This value is used in print test info
	public static String testInfo = "";

	//protected boolean isRunAll = true;
	// This property will control which row in a data file to run
	// It will work together with utilityLib.Tools.getDataByTableName
	// executeRow = 0  will execute all test cases in the datatable
	protected int executeRow=0;
	
	@Parameters({"executeRow" })
	@BeforeClass(alwaysRun = true)
	protected void setUpBeforeClass(int executeRow) {
		this.executeRow = executeRow;
	}
	
}
