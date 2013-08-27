package rtb;

import org.apache.commons.io.FileUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

import java.util.*;
import java.lang.Boolean;
import jxl.*;
import jxl.write.*;
import java.io.*;

public class RTB2ExcelExtractor {
	private static SAXBuilder builder = new SAXBuilder();

	static Vector<Column> columns = new Vector<Column>();
	static Hashtable<String, Column> columnTable = new Hashtable<String, Column>();

	private static class Column {
		String name;
		String prefix;
		String uri;
		int idx;
		int level;
		boolean data;

		public Column(String name, String prefix, String uri, boolean data,
				int idx, int level) {
			this.name = name;
			this.prefix = prefix;
			this.uri = uri;
			this.data = data;
			this.level = level;
			this.idx = idx;
		}
	}

	public static void processElement(Element element, String parentPath,
			int level) {
		if (!columnTable.containsKey(parentPath + element.getName())) {
			Column c = new Column(element.getName(), element
					.getNamespacePrefix(), element.getNamespaceURI(), element
					.getChildren().size() == 0, columns.size(), level);
			columns.add(c);
			columnTable.put(parentPath + c.name, c);
		}

		for (Object e : element.getChildren()) {
			processElement((Element) e, parentPath + element.getName() + "/",
					level + 1);
		}
	}

	public static int printScenario(String name, Element e,
			WritableSheet sheet, int baseRow) throws Exception {
		Label scenarioName = new Label(0, baseRow, name);
		sheet.addCell(scenarioName);
		return printData(e, sheet, 0, 0, "", baseRow);
	}

	public static int printData(Element e, WritableSheet sheet,
			int childNumber, int parentChildNumber, String parentPath,
			int baseRow) throws Exception {
		int lastRow = 0;

		if (e.getChildren().size() > 0) {
			Label value = new Label(
					columnTable.get(parentPath + e.getName()).idx + 1, baseRow
							+ childNumber, childNumber + "");
			sheet.addCell(value);
			String lastName = "";
			int nextChildNumber = 0;

			for (int i = 0; i < e.getChildren().size(); i++) {
				String name = ((Element) e.getChildren().get(i)).getName();
				if (name.equals(lastName))
					nextChildNumber++;
				else
					nextChildNumber = 0;
				lastName = name;
				lastRow = Math.max(lastRow, printData((Element) e.getChildren()
						.get(i), sheet, nextChildNumber, childNumber,
						parentPath + e.getName() + "/", baseRow));
			}
		} else {
			jxl.write.Label value = new Label(columnTable.get(parentPath
					+ e.getName()).idx + 1, baseRow + parentChildNumber, e
					.getText());
			lastRow = Math.max(lastRow, baseRow + parentChildNumber);
			sheet.addCell(value);
		}

		return lastRow;
	}

	public static int printFileHeader(WritableSheet sheet, File folder, int row)
			throws Exception {
		String path[] = folder.getAbsolutePath().split("\\\\");
		String process = path[path.length - 3];
		String datasource = path[path.length - 2];
		String procedure = path[path.length - 1];

		sheet.addCell(new Label(0, row, process));
		// Use unix path delimiter
		sheet.addCell(new Label(0, row + 1, datasource + "/" + procedure));
		return row + 2;
	}

	public static void printHeader(WritableSheet sheet) throws Exception {
		sheet.addCell(new Label(0, 4, "scenario"));
		for (int i = 0; i < columns.size(); i++) {
			Column c = columns.get(i);
			Label prefix = new Label(i + 1, 0, c.prefix);
			Label uri = new Label(i + 1, 1, c.uri);
			Label level = new Label(i + 1, 2, c.level + "");
			Label name = new Label(i + 1, 4, c.name);
			Label isData;

			if (c.data)
				isData = new Label(i + 1, 3, "TRUE");
			else
				isData = new Label(i + 1, 3, "FALSE");

			sheet.addCell(prefix);
			sheet.addCell(uri);
			sheet.addCell(name);
			sheet.addCell(isData);
			sheet.addCell(level);
		}
	}

	private static int printSplitter(WritableSheet sheet, int row)
			throws Exception {
		sheet.addCell(new Label(0, row, "----"));
		return row + 1;
	}

	public static int printErrorScenario(String name, Element e,
			WritableSheet sheet, int baseRow) throws Exception {
		sheet.addCell(new Label(0, baseRow, name));
		sheet.addCell(new Label(1, baseRow, "0"));
		sheet.addCell(new Label(2, baseRow, ((Element) e.getChildren().get(0))
				.getValue()));
		sheet.addCell(new Label(3, baseRow, ((Element) e.getChildren().get(1))
				.getValue()));
		return baseRow;
	}

	public static int printErrHeader(WritableSheet sheet, int row)
			throws Exception {
		sheet.addCell(new Label(0, row, "ERROR SCENARIOS"));
		sheet.addCell(new Label(1, row, "0"));
		sheet.addCell(new Label(2, row, "1"));
		sheet.addCell(new Label(3, row, "1"));

		sheet.addCell(new Label(1, row + 1, "FALSE"));
		sheet.addCell(new Label(2, row + 1, "TRUE"));
		sheet.addCell(new Label(3, row + 1, "TRUE"));

		sheet.addCell(new Label(0, row + 2, "scenario"));
		sheet.addCell(new Label(1, row + 2, "root"));
		sheet.addCell(new Label(2, row + 2, "faultcode"));
		sheet.addCell(new Label(3, row + 2, "faultstring"));

		return row + 2;
	}

	public static void generateExcelFileForStoredProcedure(String spName,
			File folder, Boolean generateErrorScenarioFlag) throws Exception {
		WritableWorkbook workbook = Workbook.createWorkbook(new File(folder
				.getAbsolutePath()
				+ "/" + spName + ".xls"));
		WritableSheet sheet = workbook.createSheet(spName, 0);

		File defaultFile = new File(folder.getAbsolutePath() + "/" + spName
				+ ".xml");
		Document doc = builder.build(defaultFile);
		columnTable.clear();
		columns.clear();
		processElement(doc.getRootElement(), "", 0);

		printFileHeader(sheet, folder, 0);
		printHeader(sheet);

		Vector<File> errFile = new Vector<File>();

		int row = printScenario(spName, doc.getRootElement(), sheet, 5);
		int errRow = 5;

		File[] list = folder.listFiles();

		for (File f : list) {
			String scenarioName = f.getName();
			scenarioName = scenarioName.split("\\.")[0];
			if (!f.equals(defaultFile) && f.getName().endsWith(".xml")) {
				//doc = builder.build(f);
				try {
					doc = builder.build(f);
					row = printScenario(scenarioName, doc.getRootElement(),
							sheet, row + 1);
				} catch (Exception e) {
					errFile.add(f);
				}
			}
		}

		//row = printSplitter(sheet, row + 2);
		row = printSplitter(sheet, row + 1);		
		row = printFileHeader(sheet, folder, row);
		row = printErrHeader(sheet, row);
		if(generateErrorScenarioFlag)
		{
			for (File f : errFile) {
				String scenarioName = f.getName();
				scenarioName = scenarioName.split("\\.")[0];
				if (!f.equals(defaultFile) && f.getName().endsWith(".xml")) {
					doc = builder.build(f);
					try {
						System.out.println(f.getName());
						row = printErrorScenario(scenarioName,
								doc.getRootElement(), sheet, row + 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		workbook.write();
		workbook.close();
	}

	public static void extractRTB(File rootFolder, Boolean generateErrorScenarioFlag) throws Exception {
		// Check for default file
		String name = rootFolder.getName();
		File defaultFile = new File(rootFolder.getAbsolutePath() + "/" + name
				+ ".xml");
		if (defaultFile.exists()) {
			generateExcelFileForStoredProcedure(name, rootFolder, generateErrorScenarioFlag);
		} else {
			File fs[] = rootFolder.listFiles();
			for (File f : fs) {
				if (f.isDirectory())
					extractRTB(f,generateErrorScenarioFlag);
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		// extractRTB(new File(args[0]));
		// File("C:\\1fb_test\\testdata\\BizworksRegressionTestbed\\1fbbizprocess\\FDR\\GetCAPPOMPUserData"));
//		 extractRTB(new
//		 File("C:\1fb_test\testdata\BizworksRegressionTestbed\1fbbizprocess\FDR\GetCAPPOMPUserData"));
		 extractRTB(new	 File("C:/1fb/testdata/BizworksRegressionTestbed/accountbizprocess/FDR/GetBonusProgramData"),true);
//		 extractRTB(new	 File("C:/1fb/testdata/BizworksRegressionTestbed/1fbbizprocess/AffinityNotes/aff_getSCRARates"),true);
//		 extractRTB(new	 File("C:/1fb/testdata/BizworksRegressionTestbed/1fbbizprocess/AffinityNotes/aff_getSCRARates"),true);
		// extractRTB(new
		// File("C:\\1fb\\testdata\\BizworksRegressionTestbed\\1fbbizprocess\\FDR\\GetCycleToDateTransactions"));
		// extractRTB(new
		// File("C:\\1fb\\testdata\\BizworksRegressionTestbed\\1fbbizprocess\\AffinityNotes\\aff_getPricingStrategyInfo"));
		// extractRTB(new
		// File("C:\\1fb\\testdata\\BizworksRegressionTestbed\\1fbbizprocess\\FDR\\GetNegAmDataFromCAP"));

		System.out.println("Done");

	}
}
