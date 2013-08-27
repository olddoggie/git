package frameLib.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilityLib.Tools;
import frameLib.MyException;

import static applications.BasePage.driver;
import applications.BaseTestSuite;
// This listener is to monitor whether a test case is passed or failed
// it can be used to print out the test result for failed/success test cases

public class MyTestCaseStatusListener implements ITestListener {
	public static boolean status = true;

	//@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	//@Override
	public void onTestSuccess(ITestResult result) {
		// Reporter.setCurrentTestResult(result);
		// Reporter.log (BaseTestSuite.testInfo);
		// BaseTestSuite.testInfo ="";
		// status = true;
	}

	//@Override
	public void onTestFailure(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Reporter.log(BaseTestSuite.testInfo);
		BaseTestSuite.testInfo = "";
		
		captureScreenshot(result);
	
		
		// SimpleDateFormat formater = new
		// SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		// String fileName = result.getName() + "_" +
		// formater.format(Calendar.getInstance().getTime()) + ".jpg";
		//
		// Tools.getScreenShot("test-output/screenshot/" + fileName);
		// Reporter.setCurrentTestResult(result);
		// Reporter.log (BaseTestSuite.testInfo);
		// String screenshotLink = "<a href=\"" + "screenshot/" + fileName +
		// "\">Screenshot</a>";
		// Reporter.log(screenshotLink);
		//
		// Throwable t = result.getThrowable();
		// String message =BaseTestSuite.testInfo + "<br/>" + screenshotLink +
		// "<br/>" + t.getMessage();
		// BaseTestSuite.testInfo ="";
		// Throwable new1 = new Throwable(message);
		// result.setThrowable(new1);
		// status = false;
	}

	//@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	//@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	//@Override
	public void onStart(ITestContext context) {
		// System.out.println("start" + context.getAllTestMethods().length);
		// System.out.println("start" + context.getName());
		// System.out.println("start" + context.getAllTestMethods());
		// TODO Auto-generated method stub

	}

	//@Override
	public void onFinish(ITestContext context) {
		// System.out.println("end" + context.getAllTestMethods().length);
		// System.out.println("end" + context.getName());
		// System.out.println("end" + context.getAllTestMethods());

	}
	
	public static void captureScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			try {
				SimpleDateFormat formater = new SimpleDateFormat(
						"dd_MM_yyyy_hh_mm_ss");
				if (driver != null) {
					driver.switchTo().defaultContent();
					File f = ((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE);
					try {
						String fileName = result.getName()
								+ "_"
								+ formater.format(Calendar.getInstance()
										.getTime()) + ".jpg";
						FileUtils.copyFile(f, new File(
								"./test-output/screenshot/" + fileName));
						Reporter.setCurrentTestResult(result);
						// Reporter.log (testInfo);
						Reporter.log("<a href=\"" + "screenshot/" + fileName
								+ "\" target=\"_blank\">Screenshot</a>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (ScreenshotException se) {
				se.printStackTrace();
			}
		}
	}

}
