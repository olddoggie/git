package tools;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
