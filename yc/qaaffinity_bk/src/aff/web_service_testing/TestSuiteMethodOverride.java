package aff.web_service_testing;

import static aff.properties.Affinity.*;
import static aff.properties.Common.*;
import static frameLib.FrameUtilities.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rtb.RtbUtil;
import rtbLib.MyAssertArrays;
import utilityLib.Tools;

import aff.BaseTestSuiteAffinity;
import com.onefbusa.afddo.UserDataDDO;
import com.onefbusa.afddo.UserDataInputDDO;

public class TestSuiteMethodOverride extends BaseTestSuiteAffinity {
	private UserDataInputDDO userDataInputDDO = new AfServiceDdo().getUserDataInputDDO();

	@BeforeClass(alwaysRun = true)
	public void setUpBeforeTestMpd() {
		//isRunAll = false;
		
		 RtbUtil.setConnection(BW_SERVER_USER_NAME, BW_SERVER_PASSWORD,
		 BW_SERVER_NAME);
		 //RtbUtil.deleteBwFolder("1fbbizprocess");
		 //RtbUtil.cleanCopyBwFolder("1fbbizprocess_default", "1fbbizprocess");
		 RtbUtil.generateScenarios("resource/xls/" ,"QA_MethodOverride.xls");
		 System.out.print("QA_MethodOverride.xls done");

	}

	@Test
	public void test(){
		System.out.println("good");
	}
//	//// @DataProvider(name = "mpdStep1_4Data", parallel = true)
//	@DataProvider(name = "mpdStep1_4Data")
//	public Object[][] mpdStep1_4Data() throws Exception {
//		Object[][] retObjArr = Tools.getTableArray("resource/xls/QA_Minimum_Payment_Due_Test.xls", "test_Step_1_4");
//		return (retObjArr);
//	}
//
//	//// @Test(dataProvider = "mpdStep1_4Data",threadPoolSize=3)
//	//@Test(dataProvider = "mpdStep1_4Data")
//	public void testMpdStep1_4Data(String scenario, String execution, String percentCurrentBal, String cureForecastedMPD,
//			String estimatedForecastedMPDDesc, String estimatedCureForecastedMPDDesc, String currentMPDDesc, String lastStmtMPDDesc) throws Exception {
//
//		if (isRunAll == true || (isRunAll == false && (!execution.isEmpty()))) {
//			setScenarioFor1fbBizProcess(scenario);
//			UserDataDDO returnedDDO = service.getUserData(userDataInputDDO);
//			System.out.println("mpdStep1_4---" + scenario + " done" + "----" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//			String actualPercentCurrentBal = Double.toString(Math.ceil(returnedDDO.getPercentCurrentBal()));
//			String actualCureForecastedMPD = Double.toString(Math.ceil(returnedDDO.getCureForecastedMPD()));
//			String actualEstimatedCureForecastedMPDDesc = returnedDDO.getEstimatedCureForecastedMPDDesc();
//			String actualEstimatedForecastedMPDDesc = returnedDDO.getEstimatedForecastedMPDDesc();
//			String actualCurrentMPDDesc = returnedDDO.getCurrentMPDDesc();
//			String actualLastStmtMPDDesc = returnedDDO.getLastStmtMPDDesc();
//
//			String[] expResult = { percentCurrentBal, cureForecastedMPD, estimatedCureForecastedMPDDesc, estimatedForecastedMPDDesc, currentMPDDesc,
//					lastStmtMPDDesc };
//			String[] actArrResult = { actualPercentCurrentBal, actualCureForecastedMPD, actualEstimatedCureForecastedMPDDesc,
//					actualEstimatedForecastedMPDDesc, actualCurrentMPDDesc, actualLastStmtMPDDesc };
//			// this assert should be changed
//			MyAssertArrays.assertArrayEquals(expResult, actArrResult, "test_Step_1_4", scenario, "#.####");
//		}
//
//	}
//
//		@DataProvider(name = "mpdStep5_6Data")
//		public Object[][] mpdStep5_6Data() throws Exception {
//			Object[][] retObjArr = Tools.getTableArray("resource/xls/QA_Minimum_Payment_Due_Test.xls", "test_Step_5_6");
//			return (retObjArr);
//		}
//
//
//		@Test(dataProvider = "mpdStep5_6Data")
//		public void testMpdStep5_6Data(String scenario, String execution, String percentCurrentBal, String cureForecastedMPD,
//				String estimatedForecastedMPDDesc, String estimatedCureForecastedMPDDesc, String currentMPDDesc, String lastStmtMPDDesc) throws Exception {
//
//			if (isRunAll == true || (isRunAll == false && (!execution.isEmpty()))) {
//				setScenarioFor1fbBizProcess(scenario);
//				UserDataDDO returnedDDO = service.getUserData(userDataInputDDO);
//				System.out.println("mpdStep1_4---" + scenario + " done" + "----" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//				String actualPercentCurrentBal = Double.toString(Math.ceil(returnedDDO.getPercentCurrentBal()));
//				String actualCureForecastedMPD = Double.toString(Math.ceil(returnedDDO.getCureForecastedMPD()));
//				String actualEstimatedCureForecastedMPDDesc = returnedDDO.getEstimatedCureForecastedMPDDesc();
//				String actualEstimatedForecastedMPDDesc = returnedDDO.getEstimatedForecastedMPDDesc();
//				String actualCurrentMPDDesc = returnedDDO.getCurrentMPDDesc();
//				String actualLastStmtMPDDesc = returnedDDO.getLastStmtMPDDesc();
//
//				String[] expResult = { percentCurrentBal, cureForecastedMPD, estimatedCureForecastedMPDDesc, estimatedForecastedMPDDesc, currentMPDDesc,
//						lastStmtMPDDesc };
//				String[] actArrResult = { actualPercentCurrentBal, actualCureForecastedMPD, actualEstimatedCureForecastedMPDDesc,
//						actualEstimatedForecastedMPDDesc, actualCurrentMPDDesc, actualLastStmtMPDDesc };
//				// this assert should be changed
//				MyAssertArrays.assertArrayEquals(expResult, actArrResult, "test_Step_5_6", scenario, "#.####");
//			}
//
//		}
//	
//	
//	
//	@DataProvider(name = "mpdLateFeeIdData")
//	public Object[][] mpdLateFeeIdData() throws Exception {
//		Object[][] retObjArr = Tools.getTableArray("resource/xls/QA_Minimum_Payment_Due_Test.xls", "test_LateFeeId");
//		return (retObjArr);
//	}
//
//	//@Test(dataProvider = "mpdLateFeeIdData")
//	public void testMpdLateFeeId(String scenario, String execution, String lateFeeTypeID, String percentCurrentBal, String cureForecastedMPD,
//			String estimatedForecastedMPDDesc, String estimatedCureForecastedMPDDesc, String currentMPDDesc, String lastStmtMPDDesc) throws Exception {
//		if (isRunAll == true || (isRunAll == false && (!execution.isEmpty()))) {
//			System.out.println("mpdLateFeeId---" + scenario + " done" + "----" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//			setScenarioFor1fbBizProcess(scenario);
//			UserDataDDO returnedDDO = service.getUserData(userDataInputDDO);
//			String actualPercentCurrentBal = Double.toString(Math.ceil(returnedDDO.getPercentCurrentBal()));
//			String actualCureForecastedMPD = Double.toString(Math.ceil(returnedDDO.getCureForecastedMPD()));
//			String actualEstimatedCureForecastedMPDDesc = returnedDDO.getEstimatedCureForecastedMPDDesc();
//			String actualEstimatedForecastedMPDDesc = returnedDDO.getEstimatedForecastedMPDDesc();
//			String actualCurrentMPDDesc = returnedDDO.getCurrentMPDDesc();
//			String actualLastStmtMPDDesc = returnedDDO.getLastStmtMPDDesc();
//			String actualLateFeeTypeId = Integer.toString(returnedDDO.getLateFeeTypeId());
//			String[] expResult = { lateFeeTypeID, percentCurrentBal, cureForecastedMPD, estimatedCureForecastedMPDDesc, estimatedForecastedMPDDesc,
//					currentMPDDesc, lastStmtMPDDesc };
//			String[] actArrResult = { actualLateFeeTypeId, actualPercentCurrentBal, actualCureForecastedMPD, actualEstimatedCureForecastedMPDDesc,
//					actualEstimatedForecastedMPDDesc, actualCurrentMPDDesc, actualLastStmtMPDDesc };
//			// PrintArray(actArrResult);
//			MyAssertArrays.assertArrayEquals(expResult, actArrResult, "test_LateFeeId", scenario, "#.####");
//		}
//	}

}
