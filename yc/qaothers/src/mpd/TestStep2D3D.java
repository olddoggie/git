package mpd;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mpd.Tools;

/** This class  test both 2d and 3d */
public class TestStep2D3D {

	private static Object[][] getDataTable() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s2dTest";
		String tableName = "s2dTestTable";
		String[][] dtatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		return dtatTable;
	}
	
	@DataProvider(name = "s2dTestTable")
	private Object[][] s2dTable() throws Exception {
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

	
	
	@Test(dataProvider = "s2dTestTable")
	public void testStep2D(String execution,String scenario, String d01202, String d02100, String d01014, String d02086){
	
		String sql = String.format("EXEC [dbo].[aff_Get_MPD_Short_Desc_Override_By_Pricing]	@PricingStrategy = '%s',@CPPOMP = '%s',@CurrBalance = %s",d01202,d02100, d01014);
		List l = Tools.AFFINITYNOTES.queryForList(sql, String.class);
		Assert.assertEquals(l.get(0),d02086.trim());
	}	

}
