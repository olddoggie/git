package mpd;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mpd.Tools;

/** Set a high current balance and set other fees to 0 */
public class TestStep2C {

	private static Object[][] getDataTable() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s2cTest";
		String tableName = "s2cTestTable";
		String[][] dtatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		return dtatTable;
	}
	
	@DataProvider(name = "s2cTestTable")
	private Object[][] s2cTable() throws Exception {
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

	
	
	@Test(dataProvider = "s2cTestTable")
	public void testStep2C(String execution, String d02073, String d02100, String d01014, String d02086){
	
		String sql = String.format("EXEC [dbo].[aff_GetMPDDescByMethodOverride]	@CPPORM = '%s',@CPPOMP = '%s',@CurBal = %s",d02073,d02100, d01014);
		//String sql = String.format("EXEC [dbo].[aff_GetMPDDescByMethodOverride]	@CPPORM = '',@CPPOMP = '',@CurBal = 240");
		List l = Tools.AFFINITYNOTES.queryForList(sql, String.class);
		Assert.assertEquals(l.get(0),d02086.trim());
	}	

}
