package mpd;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static mpd.MPDShared.runAllSteps;

/** Set a high current balance and set other fees to 0 */
public class TestStep5 {
	private Logger logger = Logger.getLogger(TestStep5.class);

	private static Object[][] getDataTable() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s5Test";
		String tableName = "s5TestTable";
		String[][] dtatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		return dtatTable;
	}

	@DataProvider(name = "s5TestTable")
	private Object[][] s5cTable() throws Exception {
		Object[][] inputTable = getDataTable();
		int executionNumber = 0;
		for (Object[] o : inputTable) {
			// column J
			if (!((String) o[0]).trim().equals(""))
				executionNumber++;
		}

		Object[][] outputFinalTable = new Object[executionNumber][inputTable[0].length];
		int j = 0;
		for (Object[] o : inputTable) {
			if (!((String) o[0]).trim().equals("")) {
				outputFinalTable[j] = o;
				j++;
			}
		}
		return outputFinalTable;
	}

	//@Test(dataProvider = "s5TestTable")
	public void testStep5(String execution,String scenario, String d01201, String d01014, String d01195, String d02073, String d02100, String d01205, String d01912,
			String d01900, String d01913, String d20034) {
		Account a = new Account();
		logger.warn("\nTestcase:" + execution + "-------");
		if (!d01201.isEmpty()){
			a.F01202_001 = d01201;
		}
		if (!d01014.isEmpty()){
			a.F01014_001 = Integer.parseInt(d01014);
			}
		if (!d01195.isEmpty())
			a.F01195_001 = d01195;
		if (!d02073.isEmpty())
			a.F02073_001 = d02073;
		if (!d02100.isEmpty())
			a.F02100_001 = d02100;
		if (!d01205.isEmpty())
			a.F01205_001 = Integer.parseInt(d01205);
		a.setCalucatedFields();
		runAllSteps(a);
		System.out.println(a.F01912_001 + "\t" + a.F01900_001 + "\t" +  a.F01913_001 + "\t" + a.F20034_003);
		//Assert.assertEquals(d01205, a.F01205_001 + "");
	}
	
	
	@Test
	public void test_temp() {
		logger.warn("\nTestcase: test_temp -------");
		Account a = new Account();
		a.F02073_001 = "41PFFCAF";
		a.F01014_001 = 60;
		//a.F01021_001 = 350;
		a.setCalucatedFields();
		runAllSteps(a);
	}
	
	
	// Test flag= 30
	//@Test
	public void test5_010() {
		logger.warn("\nTestcase: test5_010 -------");
		Account a = new Account();
		a.F01202_001 = "T151";
		a.F01014_001 = 36;
		a.F01021_001 = 350;
		a.F12100 = 10;
		a.setCalucatedFields();
		runAllSteps(a);
	}

	//@Test
	public void test5_020() {
		logger.warn("\nTestcase: test5_020 -------");
		Account a = new Account();
		a.F01202_001 = "T151";
		a.F01014_001 = 36;
		a.F01021_001 = 350;
		a.setCalucatedFields();
		runAllSteps(a);
	}

	//@Test
	public void test5_030() {
		logger.warn("\nTestcase: test5_030 -------");
		Account a = new Account();
		a.F01202_001 = "T151";
		a.F01014_001 = 5000;
		a.F01021_001 = 6000;
		a.setCalucatedFields();
		runAllSteps(a);
	}

	// @Test
	public void test5_040() {
		logger.warn("\nTestcase: test5_040 -------");
		Account a = new Account();
		a.F01202_001 = "T151";
		a.F01014_001 = 8000;
		a.F01021_001 = 350;
		a.setCalucatedFields();
		runAllSteps(a);
	}
}
