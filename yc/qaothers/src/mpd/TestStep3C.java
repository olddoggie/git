package mpd;

import static tools.Account.*;
import static tools.MPDShared.runAllSteps;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mpd.Tools;
/** Set a high current balance and set other fees to 0 */
public class TestStep3C {

	private static Object[][] getDataTable() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s3cTest";
		String tableName = "s3cTestTable";
		String[][] dtatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		return dtatTable;
	}
	
	@DataProvider(name = "s3cTestTable")
	private Object[][] s3cTable() throws Exception {
		Object[][] inputTable = getDataTable();
		int executionNumber=0;
		for(Object[] o: inputTable){
			// column J 
			if(!((String)o[0]).trim().equals(""))executionNumber++;
		}
		
		Object[][] outputFinalTable = new Object[executionNumber][inputTable[0].length];
		int j= 0;
		for(Object[] o: inputTable){
			if(!((String)o[0]).trim().equals("")){
				outputFinalTable[j] = o;
				j++;
			}
		}
		return outputFinalTable;
	}

	
	
	//@Test(dataProvider = "s3cTestTable")
	public void testStep3C_JunCode(String execution,String d01195, String d02073, String d02100, String d01014, String d02089,
			String d02090, String d02091,String d01912){
		
		F01195_001= d01195.trim(); 
		F02073_001= d02073.trim(); 
		F02100_001 = d02100.trim(); 		
		F01014_001 = Integer.parseInt(d01014.trim());
		//F02091_001 = Integer.parseInt(d02091);
		runAllSteps();
		Assert.assertEquals(F01912_001, d01912);
	}
	
	@Test(dataProvider = "s3cTestTable")
	public void testStep3C_StoredProcedure(String execution,String d01195, String d02073, String d02100, String d01014, String d02089,
			String d02090, String d02091,String d01912){
		
		String sql = String.format("EXEC [dbo].[pb_getPricingOverride]	@negAmCounter=%s, @CPPORM = '%s',@CPPOMP = '%s',@CurBal = 100000",d01195,d02073,d02100);
//		Tools.AFFINITYNOTES.queryForMap(sql).get("OverrideDesc");
//		List l = Tools.AFFINITYNOTES.queryForList(sql, String.class);
		
		String overRide = (String) Tools.AFFINITYNOTES.queryForMap(sql).get("OverrideDesc");
		Assert.assertEquals(overRide,d01912.trim());
	}
}
