package utilityLib;

import static utilityLib.Tools.addDays;
import static utilityLib.Tools.getDateString;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.onefbusa.securityservice.SecurityServiceDecorator;

import frameLib.MyException;

public class Tools {

	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

//	private static SecurityServiceDecorator security = new SecurityServiceDecorator();
//	public static String encryptString(String s ){
//		return security.encrypt(s);
//	}
//	
//	public static String decryptString(String s ){
//		return security.decrypt(s);
//	}
	
	public static boolean isProcessRunging(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.toUpperCase().contains(serviceName.toUpperCase())) {
				return true;
			}
		}

		return false;

	}

	public static void killProcess(String serviceName) {	
		try {
			while (isProcessRunging(serviceName)) {
				Runtime.getRuntime().exec(KILL + serviceName.toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean doesWebElementExist(WebDriver driver, By selector) {
		try {
			driver.findElement(selector);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static String getSource(WebDriver driver, By selector, String property) {
		WebElement element = driver.findElement(selector);
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0]." + property + ";", element);
	}

	public static void select(WebElement w)

	{
		int count = 0;
		// if( w.isSelected() == false)
		// {
		// w.click();
		// }

		while (w.isSelected() == false && count < 3) {
			w.click();
			count++;
		}
	}

	public static void deselect(WebElement w) {
		int count = 0;
		// if( w.isSelected() == true)
		// {
		// w.click();
		// }

		while (w.isSelected() == true && count < 3) {
			w.click();
			count++;
		}
	}

	/**
	 * If executeRow <0, only the non-empty execution row will be executed
	 * if executeRow>0, the specified row when will be executed
	 * if executeRow=0, the whole data table will be returned. 
	 */
	public static String[][] getDataByTableName(String xlFilePath, String sheetName, String tableName, int executeRow) {
//		System.out.println(xlFilePath);
//		System.out.println(executeRow);
		String[][] datatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		if(executeRow < 0) datatTable = getExecutionTable(datatTable);
		if(executeRow>0) datatTable = Arrays.copyOfRange(datatTable, executeRow-1,executeRow);
		return datatTable;
	}
	
	
	/**
	 * This function first gets the number of rows that execution(first column 0
	 * is not empty) Then create an array with the size of non empty row Then
	 * add the data to the new created array
	 */
	public static String[][] getExecutionTable(String[][] rawTable)  {
		String[][] inputTable = rawTable;
		int executionNumber = 0;
		// execution column will be the first column in a data table
		for (String[] o : inputTable) {
			// column J
			if (!((String) o[0]).trim().equals(""))
				executionNumber++;
		}

		// inputTable[0].length is the number of column in the datasheet
		String[][] outputFinalTable = new String[executionNumber][inputTable[0].length];
		int j = 0;
		for (String[] o : inputTable) {
			if (! o[0].trim().equals("")) {
				outputFinalTable[j] = o;
				j++;
			}
		}
		return outputFinalTable;
	}
	
	
	public static String[][] getDataByTableName(String xlFilePath, String sheetName, String tableName) {
		
		String[][] tabArray = null;
		try {
			InputStream stream = Tools.class.getClassLoader()
					.getResourceAsStream(xlFilePath);
			Workbook workbook = Workbook.getWorkbook(stream);
			//Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
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

	/**
	 * 
	 * @param xlFilePath
	 * @param sheetName
	 * @return
	 * 
	 */
	public static String[][] getTableArray(String xlFilePath, String sheetName) {
		String tableName = "---";
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			// this is cell with first --- 
			// this is right above the definition of variable row
			Cell firstTthreeDashCell = sheet.findCell(tableName);
			int lableRow = firstTthreeDashCell.getRow() + 1;
			// this is cell with second --- 
			// this is above the start of the data row
			Cell secondDashCell = sheet.findCell(tableName, 0, lableRow, 0, 64000, false);
			int firstDataRow = secondDashCell.getRow() + 1;
			
			// this is cell with third ---
			// this is right after the end of the data row
			// we don't need the cell with four ---
			Cell endDashCell = sheet.findCell(tableName, 0, firstDataRow, 0, 64000, false);
			int endDashRow = endDashCell.getRow();

			// Get the list of valid columns
			// column without name will not be added to the array
			List<Integer> labelList = new ArrayList<Integer>();
			int totalColumns = sheet.getColumns();
			for (int c = 0; c < totalColumns; c++) {
				String columnName = sheet.getCell(c, lableRow).getContents();
				if (!columnName.trim().isEmpty()) {
					labelList.add(c);
				}
			}

			//Get each row
			List<String> rowList = new ArrayList<String>();
			List<List<String>> tabList = new ArrayList<List<String>>();
			for (int i = firstDataRow; i < endDashRow; i++) {
				for (int j : labelList) {
					rowList.add(sheet.getCell(j, i).getContents());
				}
				tabList.add(new ArrayList<String>(rowList));
				rowList.clear();
			}

			//convert list list to two dimension arrays
			int rowNumber = tabList.size();
			int colNumber = labelList.size();
			tabArray = new String[rowNumber][colNumber];
			for (int i = 0; i < rowNumber; i++)
			{
				for (int j = 0; j < colNumber; j++)
				{
					tabArray[i][j] = tabList.get(i).get(j);
				}
			}
		} catch (Exception e) {
			System.out.println("error in getTableArray()");
		}
		return (tabArray);
	}
	
	
	public static void getScreenShot(String file){
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_PRINTSCREEN);
			robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
			robot.keyRelease(KeyEvent.VK_ALT);

			Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
			RenderedImage text = (RenderedImage) t.getTransferData(DataFlavor.imageFlavor);
			ImageIO.write(text, "jpg", new File(file));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new MyException("Get screenshot failed");
		}

	}
	
	public static void pressEnterKey(){
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);


		} catch (Exception e) {
			throw new MyException("Press key failed");
		}
	}
	
	public static String addDays(Calendar cal, int days, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);	
		cal.add(Calendar.DATE, days);	
		return sdf.format(cal.getTime());		
	}
	
	public static String getDateString(Calendar cal,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());		
	}	

	public static String getDateString(String format, String timeZone){
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		return getDateString(cal,format);		
	}
	
	public static String quote(String s){
		return "'" + s + "'";
	}
	
	public static String doubleQuote(String s){
		return "\"" + s + "\"";
	}
	
	public static String trimLeadingZeros(String s){
		return s.replaceFirst("^0+(?!$)", "");
	}
	
	

	public static void main(String[]args){

		
		
	}
}
