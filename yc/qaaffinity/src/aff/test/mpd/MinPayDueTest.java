package aff.test.mpd;

import utilityLib.Tools;

import org.testng.ITestResult;
import org.testng.annotations.*;

import bizprocesses.Onefbbizprocess;
import bizprocesses.RtbUtil;

import aff.BaseTestSuiteAffinity;
import aff.Utilities;
import aff.pages.*;

import static applications.BasePage.*;
import static myconstant.TestAccounts.*;
import static myconstant.General.*;
import static aff.AffinityConstants.*;

@SuppressWarnings("static-access")
public class MinPayDueTest extends BaseTestSuiteAffinity {

	//generate scenarios for mpd testing
	//@BeforeClass
	public void setUpBeforeTestMpd() {
		 RtbUtil.generateScenarios("affinity/xls/", "mpd_data.xls");
		 RtbUtil.copyDefaultCapXML();
		 System.out.print("TestMpd done");
	}	
	
	 @Parameters({ "browserType" })
	 @BeforeMethod
	 private void setUpBeforeMethod(String browserType) throws Exception {
	 resetAffinity();
	 openBrowser(browserType);
	 }

	@AfterMethod
	public void setUpAfterMethod(ITestResult result) {
		AffinityBasePage.closeAffinityBrowser();
		
	}
	
	@DataProvider(name = "scenariosTestTable")
	private Object[][] getDataTable() {
		String xlFilePath = "affinity/xls/mpd_data.xls";
		String sheetName = "mpdScemarios";
		String tableName = "scenariosTestTable";
		String[][] datatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName,executeRow);
		return datatTable;
	}

	@Test(dataProvider = "scenariosTestTable")
	public void mpdTest(String execution, String scenario, String description, String mpdDesc, String mpd, String pricingMpdDesc, String pricingMpd,
			String curMpdDesc) {
		Onefbbizprocess.setScenario(scenario);
		navigateTo(COLL_URL);
		LandingPage.load().submitForm();
		AffLoginPage.load().loginAff(ODS_USER_NAME, ODS_PASSWORD, SECURITY_AGENT_FDR_INDEX);
		SearchPageColl.load().researchRadioButton.click();
		SearchPageColl.uncheckAutoPullOn();
		SearchPageColl.searchByAcctNumber(CCA_BAT_ACCOUNT_NUMBER);
		//pay attention the sequence of the load
		//make sure the page under testing is the last one to load
		AffNavigationPage.load();
		AddNotePage.Load();
		DataPanelPage.load();

		DataPanelPage.minPmtDueAmount.verifyEquals(mpd);
		DataPanelPage.pricingMinPmtDueAmount.verifyEquals(pricingMpd);
		DataPanelPage.minPmtHoverOver.verifyContains(mpdDesc);
		DataPanelPage.pricingMinPmtHoverOver.verifyContains(pricingMpdDesc);
		DataPanelPage.strtgyPanel.verifyContains(curMpdDesc);
	}

}
