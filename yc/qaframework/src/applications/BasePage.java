package applications;

import frameLib.MyException;
import frameLib.MyWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.Set;

import static frameLib.MyVerifyAssert.verifyContains;

// This will be the base class for concrete page class
public class BasePage {
    public static WebDriver driver;
    protected static WebDriverWait wait;
    private static final int IMPLICITLY_WAIT_TIME = 5;
    private static final int PAGE_WAIT_TIME = 60;
	protected static String pageTitle = "defaultPage";

	public static String getPageTitle() {
        pageTitle = driver.getTitle() ;
        return pageTitle;
	}

	// This function is to hide driver upon get mywebelement
	public static MyWebElement myWebelement(String locator) {
		return new MyWebElement(locator);
	}

	// //This function is to hide driver upon get mywebelement
	// public MyWebElement myWebelement(String locator, String name)
	// {
	// return new MyWebElement(driver, locator, name);
	// }

	// This function is to hide driver upon get mywebelement
	public static MyWebElement myWebelement(String locator, String name,
			String... subLocator) {
		return new MyWebElement(locator, name, subLocator);
	}

	public static MyWebElement myWebelement(String locator, String name,
			int index) {
		return new MyWebElement(locator, name, index);
	}

    public static MyWebElement myWebelement(String locator, String name,
                                            int index, String... subLocator) {
        return new MyWebElement(locator, name, index, subLocator);
    }

    public static MyWebElement myWebelement(String locator, String name,
                                            int index, String[] subLocator,int[] sub_index) {
        return new MyWebElement(locator, name, index, subLocator,sub_index);
    }

	// public MyWebElement myWebelement(WebDriver driver, String locator, String
	// elementName)
	// {
	// return new MyWebElement(driver, locator, elementName);
	// }

	public static void switchToFrame(String frame) {
		driver.switchTo().defaultContent().switchTo().frame(frame);
	}

	public static void verifyPageTitle(String pageTitle) {
		if (!pageTitle.equals(driver.getTitle())) {
			throw new IllegalStateException("This is not " + pageTitle);
		}
	}

	public static String getBody() {
		return driver.findElement(By.tagName("body")).getText();
	}

	public static void verifyPageContains(String s) {
		verifyContains(" Verify " + getPageTitle() + " Page contains: " + s, getBody()
				.replaceAll("\r|\n", " "), s);
	}

	public static void navigateTo(String url) {
		driver.get(url);
	}

	public static void closeBrowser() {
//		driver.close();
		driver.quit();
        BaseTestSuite.testInfo = "";    // clean the test output msg
	}

	public static WebDriverWait getWait() {
		return wait;
	}

	public static void setWait(WebDriverWait wait) {
		BasePage.wait = wait;
	}

	public static void openBrowser(String browser) {
		try {
			if (browser.contains("firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.contains("chrome")) {
                // refer to http://code.google.com/p/selenium/wiki/ChromeDriver
                // download chromedriver.exe to testing pc
                // make sure chromedriver.exe is in the PATH system variable
			    System.setProperty("webdriver.chrome.driver", "webDriverServer/chromedriver.exe");
				driver = new ChromeDriver();
			} else if(browser.contains("safari")){
			    driver = new SafariDriver();
			}
			else {
				// Tools.killProcess("IEDriverServer.exe");
			    System.setProperty("webdriver.ie.driver", "webDriverServer/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			wait = new WebDriverWait(driver, PAGE_WAIT_TIME);
//			driver.manage().timeouts()
//					.implicitlyWait(IMPLICITYLY_WAIT_TIME, TimeUnit.SECONDS);
		} catch (Exception e) {
		    System.out.println(e);
			System.out.println("load webdriver failed");
		}
	}

	public static void waitUntilWindowPresent(String windowName) {
		final String myWindowName = windowName;
		wait.until(new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				try {
					String s = null;
					Set<String> handlers = driver.getWindowHandles();
					for (String handler : handlers) {
						driver.switchTo().window(handler);
						// if (driver.getTitle().contains(myWindowName)) {
						if (driver.getTitle().equals(myWindowName)) {
							s = driver.getTitle();
							break;
						}
					}
					return s;
				} catch (Exception e) {
					throw new NoSuchWindowException("Window is not displayed");
				}
			}
		});
	}

	public static void waitUntilWindowPresentByPageSource(final String sourceIdentity) {
		wait.until(new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				try {
					String s = null;
					Set<String> handlers = driver.getWindowHandles();
					for (String handler : handlers) {
						driver.switchTo().window(handler);
						// if (driver.getTitle().contains(myWindowName)) {
						if (driver.getPageSource().contains(sourceIdentity)) {
							s = driver.getTitle();
							break;
						}
					}
					return s;
				} catch (Exception e) {
					throw new NoSuchWindowException("Window is not displayed");
				}
			}
		});
	}

	public static void waitUntilContentPresent(final String contentIdentity) {
		wait.until(new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				try {
					String s = null;
					if (driver.getPageSource().contains(contentIdentity)) {
						s = driver.getTitle();
						}
					return s;
				} catch (Exception e) {
					throw new MyException("waitUntilContentPresent failed");
				}
			}
		});
	}

	public static void waitUntilPageReady() {
		wait.until(new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				try {
					String s = null;
					if (((String)((JavascriptExecutor)driver).executeScript("return document.readyState")).equalsIgnoreCase("complete")) {

						s = "complete";
						}
					return s;
				} catch (Exception e) {
					throw new MyException("waitUntilPageIsReady failed");
				}
			}
		});
	}


	public static void waitUntilPageTitlePresent(final String pageTitle) {
		wait.until(new ExpectedCondition<String>() {
			public String apply(WebDriver driver) {
				try {
					String s = null;
					if (driver.getTitle().contains(pageTitle)) {
						s = pageTitle;
						}
					return s;
				} catch (Exception e) {
					throw new MyException("waitUntilPageTitlePresent failed");
				}
			}
		});
	}
	public static void verifyAllElementsPresent(BasePage bp) {

		String pageTitle = bp.getPageTitle();
		Class<? extends BasePage> c = bp.getClass();
		Field[] fArr = c.getDeclaredFields();
		for (Field f : fArr) {
			if (f.getType().getName().contains("MyWebElement")) {
				String locator;
				try {
					locator = ((MyWebElement) f.get(bp)).getLocator();
					try {
						((MyWebElement) f.get(bp)).waitUntilElementPresent();
					} catch (Exception e) {
						throw new MyException(pageTitle + "---" + locator);
					}
				} catch (Exception e1) {
					throw new MyException(e1);
				}

			}

		}

	}

//	public static void captureScreenshot(ITestResult result) {
//		if (!result.isSuccess()) {
//			try {
//				SimpleDateFormat formater = new SimpleDateFormat(
//						"dd_MM_yyyy_hh_mm_ss");
//				if (driver != null) {
//					driver.switchTo().defaultContent();
//					File f = ((TakesScreenshot) driver)
//							.getScreenshotAs(OutputType.FILE);
//					try {
//						String fileName = result.getName()
//								+ "_"
//								+ formater.format(Calendar.getInstance()
//										.getTime()) + ".jpg";
//						FileUtils.copyFile(f, new File(
//								"./test-output/screenshot/" + fileName));
//						Reporter.setCurrentTestResult(result);
//						// Reporter.log (testInfo);
//						Reporter.log("<a href=\"" + "screenshot/" + fileName
//								+ "\" target=\"_blank\">Screenshot</a>");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			} catch (ScreenshotException se) {
//				se.printStackTrace();
//			}
//		}
//	}

}

// driver.findElement(MyBy.locator("xpath://table[@id='SortTable']//tbody//tr//td[3]//ancestor::div")).getText()
// driver.findElement(MyBy.locator("xpath://table[contains(@id,'Sort')]")).getText()
//
// driver.findElement(MyBy.locator("cssSelector:table[id^='Sor']")).getText()
// driver.findElement(MyBy.locator("cssSelector:table[id$='able']")).getText()
// driver.findElement(MyBy.locator("cssSelector:table[id*='Tabl']")).getText()
