package audit;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import page.AfcollagentBuiltInTestPage;
import applications.BaseTestSuite;
import static applications.BasePage.*;


@SuppressWarnings("static-access")
public class TestBWConfiguration extends BaseTestSuite {

	@Parameters({ "browserType" })
	@BeforeMethod(alwaysRun = true)
	private void setUpBeforeMethod(String browserType) throws Exception {
		openBrowser(browserType);
	}

	@AfterMethod(alwaysRun = true)
	public void setUpAfterMethod(ITestResult result) {
		closeBrowser();
	}

	@DataProvider(name = "urls")
	private Object[][] urls_Data() throws Exception {
		return new Object[][] {
				{ "http://support.1fbusa.com/afcollagent/builtInTest.do" },
				{ "http://supportsf.1fbusa.com/afcollagent/builtInTest.do" },
				{ "http://support.1fbusa.com/afcsragent/builtInTest.do" },
				{ "http://supportsf.1fbusa.com/afcsragent/builtInTest.do" } };
	}

	/** verify 1fbbizprocess configuration for MPD */
	@Test(dataProvider = "urls")
	public void tc_1fbbizprocessConfig(String url) {
		navigateTo(url);		
		driver.switchTo().defaultContent().switchTo().frame("afservice");
		String expected = "ConstantsCollectionsMinDue 0.0265";
		AfcollagentBuiltInTestPage.verifyPageContains(expected);
		expected = "ConstantsCollectionsAlternateMinDue 0.035";
		AfcollagentBuiltInTestPage.verifyPageContains(expected);
	}
}
