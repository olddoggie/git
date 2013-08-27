package mpd;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ddsteps.dataset.DataTable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class Tools {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("datasource.xml");
	public static final JdbcTemplate PROGRAMBUDDY = (JdbcTemplate) context.getBean("programBuddyJdbcTemplate");	
	public static final JdbcTemplate AFFINITYNOTES = (JdbcTemplate) context.getBean("affinityNotesJdbcTemplate");	
	public static void main(String[]args){
		String xlFilePath = "resources/s3c_data.xls";
		String sheetName = "postNegAmGrp";
		String tableName = "postNegAmGrpArray";
		String[][] dataOrg = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		List<String> data = convert2DimArray2OneDimList(dataOrg);
		for(String s: data){
			System.out.println(s);
		}
	}

	
	public static List<String> convert2DimArray2OneDimList(String[][]arr2){
		List<String> list = new ArrayList<String>();
		int size = arr2.length;
		String[] resultArr = new String[size];
		for(int i = 0; i<size;i++){
			list.add(arr2[i][0]);
		}
		return list;
		
	}
public static String[][] getDataByTableName(String xlFilePath, String sheetName, String tableName) {
		
		String[][] tabArray = null;
		try {
//			InputStream stream = Tools.class.getClassLoader()
//					.getResourceAsStream(xlFilePath);
//			Workbook workbook = Workbook.getWorkbook(stream);
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			int startRow, startCol, endDashRow, endCol, ci, cj;
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell endDashCell = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);

			endDashRow = endDashCell.getRow();
			endCol = endDashCell.getColumn();
			// System.out.println("startRow="+startRow+", endDashRow="+endDashRow+", " +
			// "startCol="+startCol+", endCol="+endCol);
			tabArray = new String[endDashRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			for (int i = startRow + 1; i < endDashRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getTableArray()");
		}
		return (tabArray);
	}


	public static void findPricingStrategyForS5(int MINIMUM_PAYMENT_DUE_CALCULATION_FLAG){
		String s = "select PARENT_PRICING_STRATEGY, PRICINGSTRATEGY, punitive_severity_level,MINIMUM_PAYMENT_DUE_CALCULATION_FLAG "
				+ "from dbo.TBL_PRICING_STRATEGIES "
				+ "where punitive_severity_level <> 99 order by PARENT_PRICING_STRATEGY,punitive_severity_level desc,MAXIMUM_CURRENT_BALANCE desc";
		List<Map<String, Object>> lmap = Tools.PROGRAMBUDDY.queryForList(s);
		String first = "start";
		String second;
		int counter = 0;
		for (int i = 0; i < lmap.size() - 1; i++) {
			// first = (String) lmap.get(0).get("PARENT_PRICING_STRATEGY");
			second = (String) lmap.get(i).get("PARENT_PRICING_STRATEGY");
			if (second.equals(first)) {
				continue;
			}
			//System.out.println(i + "--" );
			String ps = (String) lmap.get(i).get("PRICINGSTRATEGY");
			int firstFlag = (Integer) (lmap.get(i).get("MINIMUM_PAYMENT_DUE_CALCULATION_FLAG"));
			if (firstFlag == MINIMUM_PAYMENT_DUE_CALCULATION_FLAG) {
				System.out.println(i + " PARENT_PRICING_STRATEGY:" + first  + "   PRICINGSTRATEGY:" + ps);
				counter++;
				if(counter>10)
				break;
			}
			first = second;
		}
	}
	
	
	public static void findPricingStrategyDiffFromSPS(){
		String s = "select PARENT_PRICING_STRATEGY, PRICINGSTRATEGY, punitive_severity_level,MINIMUM_PAYMENT_DUE_CALCULATION_FLAG "
				+ "from dbo.TBL_PRICING_STRATEGIES "
				+ "where punitive_severity_level <> 99 order by PARENT_PRICING_STRATEGY,punitive_severity_level desc,MAXIMUM_CURRENT_BALANCE desc";
		List<Map<String, Object>> lmap = Tools.PROGRAMBUDDY.queryForList(s);
		String first = "start";
		String second;
		int counter = 0;
		for (int i = 0; i < lmap.size() - 1; i++) {
			// first = (String) lmap.get(0).get("PARENT_PRICING_STRATEGY");
			second = (String) lmap.get(i).get("PARENT_PRICING_STRATEGY");
			if (second.equals(first)) {
				continue;
			}
			//System.out.println(i + "--" );
			String ps = (String) lmap.get(i).get("PRICINGSTRATEGY");
			if (!ps.equalsIgnoreCase(second) ) {
				System.out.println(i + " PARENT_PRICING_STRATEGY:" + first  + "   PRICINGSTRATEGY:" + ps);
				counter++;
				if(counter>10)
				break;
			}
			first = second;
		}
	}
////get one dimension array
//public static String[] getArrayByTableName(String xlFilePath, String sheetName, String tableName) {
//	
//	String[] tabArray = null;
//	try {
////		InputStream stream = Tools.class.getClassLoader()
////				.getResourceAsStream(xlFilePath);
////		Workbook workbook = Workbook.getWorkbook(stream);
//		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
//		Sheet sheet = workbook.getSheet(sheetName);
//		int startRow, startCol, endDashRow;
//		Cell tableStart = sheet.findCell(tableName);
//		startRow = tableStart.getRow();
//		startCol = tableStart.getColumn();
//
//		Cell endDashCell = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);
//
//		endDashRow = endDashCell.getRow();		
//		// System.out.println("startRow="+startRow+", endDashRow="+endDashRow+", " +
//		// "startCol="+startCol+", endCol="+endCol);
//		tabArray = new String[endDashRow - startRow - 1];
//	
//		int ci = 0;
//		for (int i = startRow + 1; i < endDashRow; i++) {
//			   tabArray[ci] = sheet.getCell(startCol + 1, i).getContents();
//			   ci++;
//		
//		}
//	} catch (Exception e) {
//		System.out.println("error in getTableArray()");
//	}
//	return (tabArray);
//}

}
