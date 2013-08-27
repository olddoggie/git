package aff.web_service_testing;

import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static aff.properties.TestingAccountsProperties.*;
import static frameLib.MyVerifyAssert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rtb.RtbUtil;
import aff.BaseTestSuiteAffinity;
import aff.pages.TermsPage;
import bizprocesses.Onefbbizprocess;

import com.onefbusa.afddo.TermsAPR;
import com.onefbusa.afddo.TermsAPRInputDDO;

import database.OdsSql;
public class TestSuiteTermAPR extends BaseTestSuiteAffinity {

	@BeforeClass(alwaysRun = true)
	public void setUpBeforeTestSuiteTermAPR() {
		
		RtbUtil.deleteBwFolder("1fbbizprocess");
		String[] files = {"resource/xls/QA_Term.xls"};
		RtbUtil.putFiles2BW(files);
		RtbUtil.generateScenarios("xls/QA_Term.xls");
		System.out.println("Export done");
		loadWebDriver("ie");
		fromLandingPage2SearchPage(CSR_URL);
	}
		
	@Test
	/**
	 * pricing strategy: RZ1L
	 * SCRA = yes
	 * verify:  current purchase = 0/current cash = SCRA cash /script = ARP_026
	 */
	public void testScraOnly0PurcaseHighCash() throws Exception {
		OdsSql.updateClientControl(ACCOUNT_NUMBER, 4, "S");
		OdsSql.updatePricingStrategy(ACCOUNT_NUMBER, "RZ1L");
		TermsAPRInputDDO termsAPRInputDDO = new AfServiceDdo(ACCOUNT_NUMBER).getTermsAPRInputDDO();
		TermsAPR returnedDDO = Onefbbizprocess.getAFService().termsAPR(termsAPRInputDDO).getTermsAPR();
		double currentCashAPR = returnedDDO.getCurrentCashAPR();
		verifyTrue("verify currentCashAPR==0.06", currentCashAPR==0.06);
		double currentPurchaseAPR = returnedDDO.getCurrentPurchaseAPR();
		verifyTrue("verify currentPurchaseAPR==0.06", currentPurchaseAPR==0.0);
		fromSearchPage2TermPage(ACCOUNT_NUMBER);
		TermsPage termsPage = new TermsPage(driver);
		termsPage.termsScriptCode.verifyContains("APR_026");
	}

}
