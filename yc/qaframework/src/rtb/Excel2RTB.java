package rtb;
import java.util.*;
import java.lang.Boolean;
import jxl.*;
import jxl.read.biff.BiffException;
import java.io.*;

public class Excel2RTB {
	private Workbook book;
	private File rtbBaseDirectory;

	private Vector<Column> columns = new Vector<Column>();
	private Hashtable<String, Column> columnTable = new Hashtable<String, Column>();
	Vector<String> executableScenarios = new Vector<String>();
	// when this flag is false, only the green scenario will be exported
	private boolean exportAll;
	// generateFlag = -1 (most strict -- only generate xmls in both test and sn_ worksheets)
	// generateFlag = 0 (moderate -- only generate xmls in sn_ worksheets)
	// generateFlag = 1 (all -- generate xmls in any worksheets)
	private int generateFlag = -1;
	// private boolean generateFlag = false;
	private int headerRow = 0;
	private int scenarioColumn = 0;
	private int firstDataColumn = scenarioColumn + 1;
	private int baseDataColumn = 1;

	private static class Column {
		String name;
		String prefix;
		String uri;
		int idx;
		int level;
		boolean data;

		public Column(String name, String prefix, String uri, Boolean data,
				int idx, int level) {
			this.name = name;
			this.prefix = prefix;
			this.uri = uri;
			this.data = data;
			this.idx = idx;
			this.level = level;
		}
	}

	public Excel2RTB(String rtbBaseDirectory, String book, Boolean exportSetting)
			throws IOException, BiffException {
		System.out.println(book);
		this.book = Workbook.getWorkbook(new File(book));
		this.rtbBaseDirectory = new File(rtbBaseDirectory);
		this.exportAll = exportSetting;
		this.executableScenarios = retrieveScenarios();
	}

	public Excel2RTB(String rtbBaseDirectory, String book,
			Boolean exportSetting, int testSheetFlag) throws IOException,
			BiffException {
		System.out.println(book);
		this.book = Workbook.getWorkbook(new File(book));
		this.rtbBaseDirectory = new File(rtbBaseDirectory);
		this.exportAll = exportSetting;
		this.generateFlag = testSheetFlag;
		this.executableScenarios = retrieveScenarios();
	}

	private void readColumns(Sheet sheet, int rowIndex) {
		Column c;
		int i = 0;

		do {
			c = null;
			String prefix = sheet.getCell(i + 1, rowIndex).getContents();
			String uri = sheet.getCell(i + 1, rowIndex + 1).getContents();
			String isData = sheet.getCell(i + 1, rowIndex + 3).getContents();
			String name = sheet.getCell(i + 1, rowIndex + 4).getContents();
			int level;

			try {
				level = Integer.parseInt(sheet.getCell(i + 1, rowIndex + 2)
						.getContents());
			} catch (Exception e) {
				level = -1;
			}

			if (isData.equalsIgnoreCase("true")) {
				c = new Column(name, prefix, uri, true, i, level);
			} else if (name != null && name != "") {
				c = new Column(name, prefix, uri, false, i, level);
			}

			if (c != null)
				columns.add(c);
			i++;
		} while (i < sheet.getColumns() - 1 && c != null);
	}

	/*
	 * Main algorithm to extract encoded RTB files from an Excel document.
	 * Highly recursive and somewhat terrifying.
	 */
	private void readData(Sheet sheet, int level, int baseRow, int siblingIdx,
			int colIdx, String indent, XMLWriter writer, int parentIdx)
			throws IOException {
		String value = sheet.getCell(colIdx + baseDataColumn,
				baseRow + siblingIdx).getContents();
		Column c = columns.get(colIdx);

		// If this element is a complex type (may contain children, but no
		// value)
		if (!c.data && value != "") {

			// write open tag
			writer.openTag(writer, c.name, c.prefix, c.uri);

			// read children
			int i = colIdx + 1;
			while (i + 1 < columns.size() + 1
					&& Integer.parseInt(sheet.getCell(i + 1, headerRow + 2)
							.getContents()) >= level + 1) {
				if (Integer.parseInt(sheet.getCell(i + 1, headerRow + 2)
						.getContents()) == level + 1)
					readData(sheet, level + 1, baseRow, siblingIdx, i, indent
							+ "  ", writer, colIdx);
				i++;
			}

			// write closing tag
			writer.closeTag(writer, c.name, c.prefix, c.uri);

			// read siblings
			if (siblingIdx != 0)
				return;
			i = baseRow + 1;
			while (i < sheet.getRows()
					&& sheet.getCell(0, i).getContents().equals("")) {
				if (!sheet.getCell(colIdx + baseDataColumn, i).getContents().equals("")) {
					readData(sheet, level, baseRow, i - baseRow, colIdx,
							indent, writer, parentIdx);
				}
				i++;
			}

			
						
			// If this element is a simple type (contains a value, but no
			// children)
		} else {
			writer.dataElement(writer, c.name, c.prefix, c.uri, value);

			// read siblings
			if (siblingIdx != 0)
				return;
			int i = baseRow + 1;
			while (i < sheet.getRows()
					&& sheet.getCell(parentIdx + baseDataColumn, i)
							.getContents().equals("")) {
				if (!sheet.getCell(colIdx + baseDataColumn, i).getContents().equals("")) {
					readData(sheet, level, baseRow, i - baseRow, colIdx,
							indent, writer, parentIdx);
				}
				i++;
			}
		}
	}

	private void loadScenario(Sheet sheet, File outputFile, int scenarioRow) {
		try {
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
			XMLWriter writer = new XMLWriter(outputFile);
			writer.header(writer);

			readData(sheet, 0, scenarioRow, 0, 0, "", writer, 0);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadScenariosForStoredProcedure(Sheet sheet,
			File rtbBaseDirectory) {

		columns = new Vector<Column>();
		headerRow = 0;

		readColumns(sheet, 0);
		for (int i = 5; i < sheet.getRows(); i++) {

			if (sheet.getCell(0, i).getContents().startsWith("---")) {
				columns.clear();
				readColumns(sheet, i + 1);
				headerRow = i + 1;
				i += 6;
			}
			if (i < sheet.getRows()
					&& !sheet.getCell(0, i).getContents().equals("")
					&& executableScenarios.contains(sheet.getCell(0, i)
							.getContents().trim())) {
				String scenario = sheet.getCell(0, i).getContents().trim();
				String application = sheet.getCell(0, headerRow).getContents();
				String rtbPath = sheet.getCell(0, headerRow + 1).getContents();
				File f = new File(rtbBaseDirectory + "/" + application + "/"
						+ rtbPath + "/" + scenario + ".xml");
				loadScenario(sheet, f, i);
				System.out.println(sheet.getCell(0, i).getContents() + " "
						+ f.getAbsolutePath());
			}
		}
	}

	public void loadAllScenarios() throws Exception {
		for (int i = 0; i < book.getNumberOfSheets(); i++) {
			if (generateFlag == 1) {
				String temp = book.getSheet(i).getCell(0, 1).getContents();
				if (temp.contains("\\")||temp.contains("/"))
					loadScenariosForStoredProcedure(book.getSheet(i),
							rtbBaseDirectory);
			} else {
				if (book.getSheet(i).getName().startsWith("SN_")) {
					if (book.getSheet(i).getCell(0, 1).getContents().contains(
							"\\")||book.getSheet(i).getCell(0, 1).getContents().contains(
									"/"))
						loadScenariosForStoredProcedure(book.getSheet(i),
								rtbBaseDirectory);
				}
			}

		}
	}

	public Vector<String> retrieveScenarios() {
		Vector<String> myScenarios = new Vector<String>();
		Sheet mySheet;
		int myNumberOfSheets, myRowsNumber;
		myNumberOfSheets = book.getNumberOfSheets();

		for (int i = 0; i < myNumberOfSheets; i++) {
			mySheet = book.getSheet(i);
			if (generateFlag == 1) {
				myRowsNumber = mySheet.getRows();
				for (int r = 5; r < myRowsNumber; r++) {
					try {
						if (mySheet.getCell(0, r) != null) {
							myScenarios.add(mySheet.getCell(0, r).getContents()
									.trim());
						}
					} catch (Exception e) {
						System.out.println(book.getSheet(i).getName()
								+ ": cell 0 in row---" + r + "---is null");
					}

				}
			} else if (generateFlag == 0) {
				if (book.getSheet(i).getName().startsWith("SN_")) {
					myRowsNumber = mySheet.getRows();
					for (int r = 5; r < myRowsNumber; r++) {
						try {
							if (mySheet.getCell(0, r) != null) {
								myScenarios.add(mySheet.getCell(0, r)
										.getContents().trim());
							}
						} catch (Exception e) {
							System.out.println(book.getSheet(i).getName()
									+ ": cell 0 in row---" + r + "---is null");
						}
					}
				}
			} else {
				if (book.getSheet(i).getName().startsWith("test")) {
					myRowsNumber = mySheet.getRows();
					// System.out.println(myRowsNumber);
					for (int r = 5; r < myRowsNumber; r++) {
						// System.out.println(r);
						try {
							if (mySheet.getCell(0, r) != null) {
								if (exportAll) {
									myScenarios.add(mySheet.getCell(0, r)
											.getContents().trim());
								} else if (mySheet.getCell(0, r)
										.getCellFormat().getBackgroundColour()
										.getDescription().indexOf("green") > 0) {
									myScenarios.add(mySheet.getCell(0, r)
											.getContents().trim());
								}
							}
						} catch (Exception e) {

							System.out.println(book.getSheet(i).getName()
									+ ": cell 0 in row---" + r + "---is null");
						}
					}
				}
			}

		}
		return myScenarios;
	}

	public static void main(String args[]) throws Exception {
		// args[1]=rtbBaseDirectory, args[2]=book, args[3] = exportSetting, args[4] = generateFlag
		// (green one/or all scenarios)
//		Excel2RTB loader = new Excel2RTB(args[0], args[1], Boolean
//				.parseBoolean(args[2]),Integer
//				.parseInt(args[3]) );
//		loader.loadAllScenarios();
		
		
//		Excel2RTB loader = new Excel2RTB("C:/1fb/testdata/BizworksRegressionTestbed/", "C:/1fb/testdata/BizworksRegressionTestbed/accountbizprocess/FDR/GetBonusProgramData/GetBonusProgramData.xls", true, 1);
//				loader.loadAllScenarios();		
		
		if (args[0].equals("excel2RTB")) {
			// args[1]=rtbBaseDirectory, args[2]=book, args[3] = exportSetting, args[4] = generateFlag
			// (green one/or all scenarios)
			Excel2RTB loader = new Excel2RTB(args[1], args[2], Boolean
					.parseBoolean(args[3]),Integer
					.parseInt(args[4]) );
			loader.loadAllScenarios();
			System.out.println("excel2RTB is done successfully");
		} else {
			RTB2ExcelExtractor.extractRTB(new File(args[1]), true);
			System.out.println("RTB2Excel is done successfully");
		}

	}
}
