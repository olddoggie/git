package aff.bat;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import aff.pages.*;
import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;

public class CollectionTest extends SharedTest {

	@BeforeClass
	protected void launchRemoteDriver() {
		loadWebDriver("ie");
	}	
	
	@Test(priority = 10)
	public void loginTest() {
		new LandingPage(driver).open(COLL_URL);
		new LoginPage(driver).loginAff(ODS_USER_NAME, ODS_PASSWORD, SECURITY_AGENT_FDR_INDEX);
	}
	
	/**
	 * Steps to reproduce. <br>
	 * 1. Login affinity<br>
	 * 2. Load an account in search page<br>
	 * 3. Click nonmon link, then click request an adjustment link <br>
	 *  <p>
	 * Expected result:<br>
	 * 1. Verify adjustment page is loaded<br>
	 */
	@Test(priority = 800)
	public void requestAnAdjustmentTest() {
//		NavigationPage navigationPage = new NavigationPage(driver);
//		navigationPage.getNaviNonmonLink().click();
//		navigationPage.getNaviRequestAdjustmentLink().click();
//		RequestAnAdjustmentPage requestAnAdjustmentPage = new RequestAnAdjustmentPage(driver);

	}


	
	//@AfterClass
	public void teardownAfterClass(){
		new AffinityBasePage(driver).closeAffinityBrowser();
	}	
}
