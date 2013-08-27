package excel;

import java.io.File;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.FormulaCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;

/**
 * This class will convert excel test case into java testng format.
 * 
 */
public class __ExcelParser_obsolete {

	private static Logger logger = Logger.getLogger("main");
	public static String comment,tcName,tcBody;
	

	public static String createTestCase(String comment, String tcName, String tcBody){
		StringBuilder sb = new StringBuilder();
		sb.append("//" + comment + "\\n");
		sb.append("@Test\\n");
		sb.append("public void " + tcName + "{}(");
		sb.append(tcBody);
		sb.append("\\n{");
		return sb.toString();
	}
	public static void ExportQTPFile(String myDir) throws Exception {
		int myRow;
		String sourceFilePath;
		String sourceWorksheetName;
		Workbook sourceBook;
		Sheet sourceSheet;
		

		List<File> myFiles = (List<File>) FileUtils.listFiles(new File(myDir), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : myFiles) {
			try {
				// if (file.getName().toUpperCase().matches("QA_.*\\.XLS")) {
				if (file.getName().matches("keywordFrameworkDemo.xls")) {
					logger.info("source file --- " + file.getPath());

					sourceFilePath = file.getPath();
					sourceBook = Workbook.getWorkbook(new File(sourceFilePath));

					for (int i = 0; i < sourceBook.getNumberOfSheets(); i++) {
						sourceWorksheetName = sourceBook.getSheet(i).getName();
						if (sourceWorksheetName.toUpperCase().startsWith("TEST_")) {
							sourceSheet = sourceBook.getSheet(i);

//// start get test case from worksheet
							//Tuple settingTable = findRowNumbers(sourceSheet, 0, "###setting###");
							myRow = getRowNumber(sourceSheet, 0, "###end");
							boolean tcEnableFlag = false;
							for (int row = 0; row < myRow; row++) {
								try {
									String firstColumnCell = sourceSheet.getCell(0, row).getContents().toLowerCase();
									String firstColumnCellNext = sourceSheet.getCell(0, row+1).getContents().toLowerCase();
									if (firstColumnCell.startsWith("###tc")) {
										tcEnableFlag= true;
										//tcStartFlag = !tcStartFlag;
										comment = firstColumnCell;
										tcName = comment.substring(3,9);
										continue;
									} 
									if(tcEnableFlag){
										String statement = sourceSheet.getCell(3, row).getContents() + " " + sourceSheet.getCell(4, row).getContents() + "." + sourceSheet.getCell(5, row).getContents() +"\r\n";
										tcBody = tcBody + statement;
									}

									
									if (firstColumnCellNext.startsWith("###")){		
										if(firstColumnCellNext.startsWith("###tc"))
										{
											System.out.println(comment);
											System.out.println(tcBody);
											comment ="";
											tcName ="";
											tcBody = "'";											
										}else{	
											//tcEnableFlag = false;
											continue;
										}

									}
									
										
										
										
//// end of test case retrieval
															
								} catch (Exception e) {
									logger.error("Unexpected errors:" + file.getName());
								}
							}
						}
					}
					sourceBook.close();
				}
			} catch (Exception e) {
				logger.error("Unexpected errors:" + file.getName());
			}
		}
	}

	public static int getRowNumber(Sheet sourceSheet, int columnNumber, String s) {
		int myRow = 0;
		for (int j = 0; j < 10000; j++) {
			if (sourceSheet.getCell(columnNumber, j).getContents().toLowerCase().contains(s)) {
				myRow = j;
				break;
			}
		}
		return myRow;
	}

//	public static Tuple findRowNumbers(Sheet sourceSheet, int columnNumber, String tableName) {
//		int startRow = 0;
//		int endRow = 0;
//		for (int j = 0; j < 10000; j++) {
//			if (sourceSheet.getCell(columnNumber, j).getContents().toLowerCase().contains(tableName)) {
//				startRow = j;
//				break;
//			}
//		}
//
//		for (int k = startRow + 1; k < 10000; k++) {
//			if (sourceSheet.getCell(columnNumber, k).getContents().isEmpty() || sourceSheet.getCell(columnNumber, k).getContents().startsWith("###")) {
//				endRow = k - 1;
//				break;
//			}
//		}
//		return new Tuple(startRow, endRow);
//	}

	// public static List<String> getTestCases(){
	//
	// }
	public static void main(String[] args) {

		try {
			ExportQTPFile("resource/");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
