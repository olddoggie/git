package excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class __CopyOfTestNgClassExporter {
	private String TAG = "###";
	private String KEY_WORD_TAG = "###my";
	private String GLOBAL_VARIABLE_TAG = "###globalvariables";
	private String TEST_CASE_TAG = "###tc";
	private String BEFORE_CLASS_TAG = "###beforclass";
	private String BEFORE_METHOD_TAG = "###beforemethod";
	private String TEST_SUITE_TAG = "###ts";
	private final String END_ROW_TOKEN = "###end";
	private Sheet sourceSheet;
	private int endRowNumber = 10000;

	public static void main(String[] args) throws BiffException, IOException {
		// String s =
		// "###TC10010\nUse Case 224: Account Security Summary\n1. Main Flow";
		// String keyword = "getLogin(\"name\",\"password\")";
		// keyword = keyword.replaceAll("(.*?)(\\(.*?\\))", "$1"+ "(aaa )");
		// keyword = "LoginPage loginPage = new LoginPage()";
		// keyword = keyword.replaceAll("(.*? )(.*=.*new.*)" , "$2");

		
	
		Workbook sourceBook = Workbook.getWorkbook(new File("resource/keywordFrameworkDemo.xls"));

		for (int i = 0; i < sourceBook.getNumberOfSheets(); i++) {
			String sourceWorksheetName = sourceBook.getSheet(i).getName();
			if (sourceWorksheetName.toUpperCase().startsWith("TEST_")) {
				__CopyOfTestNgClassExporter exporter = new __CopyOfTestNgClassExporter(sourceBook.getSheet(i));
				System.out.println(exporter.buildTestClass());
			}
		}
	}

	public String buildTestClass() {
		StringBuilder tcBuilder = new StringBuilder();
		tcBuilder.append("package test;\n");
		tcBuilder.append(getLibs());
		tcBuilder.append("\n");
		for (String s : getTestSuite())
			tcBuilder.append(s);
		for (String s : getGlobalVariable())
			tcBuilder.append(s);
		for (String s : getTestCases())
			tcBuilder.append(s);
		for (String s : getKeyWordMethod())
			tcBuilder.append(s);
		tcBuilder.append("}");

		return tcBuilder.toString();
	}

	public String getLibs() {
		StringBuilder libsBuilder = new StringBuilder();
		libsBuilder.append("import org.testng.ITestResult;\n");
		libsBuilder.append("import org.testng.annotations.*;\n");

		libsBuilder.append("import frameLib.listener.*;\n");
		libsBuilder.append("import static frameLib.MyWebDriverLib.*;\n");

		libsBuilder.append("import static properties.Affinity.*;\n");
		libsBuilder.append("import static properties.Common.*;\n");
		libsBuilder.append("import static properties.TestingAccounts.*;\n");
		return libsBuilder.toString();
	}

	public List<String> getGlobalVariable() {
		return getTestNgBlocks(GLOBAL_VARIABLE_TAG);
	}

	public List<String> getTestSuite() {
		return getTestNgBlocks(TEST_SUITE_TAG);
	}

	public List<String> getKeyWordMethod() {
		return getTestNgBlocks(KEY_WORD_TAG);
	}

	public List<String> getSetupBeforeClass() {
		return getTestNgBlocks(BEFORE_CLASS_TAG);
	}

	public List<String> getSetupBeforeMethod() {
		return getTestNgBlocks(BEFORE_METHOD_TAG);
	}

	public List<String> getTestCases() {
		return getTestNgBlocks(TEST_CASE_TAG);
	}

	public String getVariableStatement(String variable, String value) {
		String result = "private String " + variable + "=" + value;
		return result;
	}

	// the blocks can be variables and methods
	private List<String> getTestNgBlocks(String myMethodTag) {
		String tcDescription = "";
		String name = "";
		String tcAnotation = "";
		boolean tcEnableFlag = false;
		List<String> testCaseList = new ArrayList<String>();
		List<String> statments = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < endRowNumber; row++) {
			String firstColumnCell = getCellContents(0, row);
			String nextFirstColumnCell = getCellContents(0, row + 1);
			if (firstColumnCell.toLowerCase().startsWith(myMethodTag)) {
				tcEnableFlag = true;
				tcDescription = getTcDescription(firstColumnCell);
				name = getname(firstColumnCell);
				tcAnotation = getAnotation(firstColumnCell);
				if (nextFirstColumnCell.toLowerCase().startsWith(TAG)) {
					sb.append(tcDescription);
					// if (myMethodTag.equalsIgnoreCase(KEY_WORD_TAG)) {
					// sb.append("public void " + name + "(){\n");
					// } else
					if (myMethodTag.equalsIgnoreCase(TEST_SUITE_TAG)) {
						sb.append("@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })\n");
						sb.append("public class " + name + "{\n");
					} else if (myMethodTag.equalsIgnoreCase(GLOBAL_VARIABLE_TAG)) {
						// Do nothing
					}else if (myMethodTag.equalsIgnoreCase(KEY_WORD_TAG)) {
						sb.append("public void " + addStringType2Parameter(name) + "{}\n");
					} 
					else {
						if (!tcAnotation.isEmpty())
							sb.append(tcAnotation + "\n");
						sb.append("public void " + name + "(){}\n");
					}
					testCaseList.add(sb.toString());
					sb = new StringBuilder();
					tcEnableFlag = false;
					tcDescription = "";
					name = "";
				} else {
					continue;
				}

			}
			if (tcEnableFlag) {
				if (!getCellContents(5, row).isEmpty() || !getCellContents(4, row).isEmpty()) {
					String statement = getTcStatement(getCellContents(3, row), getCellContents(4, row), getCellContents(5, row),
							getCellContents(6, row));
					statments.add(statement);
				}

			}
			// generate test case
			// reset the variable for next test case
			if (nextFirstColumnCell.toLowerCase().startsWith(TAG)) {
				if (tcEnableFlag) {
					sb.append(tcDescription);
					if (myMethodTag.equalsIgnoreCase(TEST_SUITE_TAG)) {
						sb.append("@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })\n");
						sb.append("public class " + name + "{\n");
					} else if (myMethodTag.equalsIgnoreCase(GLOBAL_VARIABLE_TAG)) {
						// Do nothing
					}else if (myMethodTag.equalsIgnoreCase(KEY_WORD_TAG)) {
						sb.append("public void " + addStringType2Parameter(name) + "{\n");
					} 
					else {
						if (!tcAnotation.isEmpty())
							sb.append(tcAnotation + "\n");
						sb.append("public void " + name + "(){\n");
					}

					statments = getTcStatements(statments);
					for (String s : statments)
						sb.append(s);

					if (!myMethodTag.equalsIgnoreCase(GLOBAL_VARIABLE_TAG) && !myMethodTag.equalsIgnoreCase(TEST_SUITE_TAG)) {
						sb.append("}\n\n");
					} else {
						sb.append("\n");
					}
					testCaseList.add(sb.toString());
				}
				sb = new StringBuilder();
				tcEnableFlag = false;
				tcDescription = "";
				name = "";
				statments = new ArrayList<String>();
			}
		}

		return testCaseList;
	}

	/**
	 * Remove the duplicate definition of the same variable. For example, change
	 * from LoginPage loginPage = new LoginPage(driver);LoginPage loginPage =
	 * new LoginPage(driver); to LoginPage loginPage = new
	 * LoginPage(driver);loginPage = new LoginPage(driver);
	 */
	private List<String> getTcStatements(List<String> statements) {

		int size = statements.size();
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (statements.get(i).contains("new") && statements.get(j).equals(statements.get(i))) {
					statements.set(j, statements.get(j).replaceAll("(.*? )(.*=.*new.*)", "$2"));
				}
			}
		}
		return statements;
	}

	private String getTcStatement(String command, String lib, String keyword, String value) {
		String statement;
		String keywordWithValue = keyword.replaceAll("(.*?)(\\(.*?\\))", "$1" + "(" + value + ")");
		// low case the first character
		String libLowerCase = "";
		if (lib.length() > 1)
			libLowerCase = lib.substring(0, 1).toLowerCase() + lib.substring(1);

		if (command.equalsIgnoreCase("call")) {
			if (lib.toLowerCase().contains("page")) {
				statement = libLowerCase + "." + keywordWithValue;
			} else {
				statement = lib + "." + keywordWithValue;
			}
		} else if (command.equalsIgnoreCase("on"))
			statement = lib + " " + libLowerCase + " = new " + lib + "(driver)";
		else if (command.equalsIgnoreCase("click"))
			statement = libLowerCase + "." + keyword + ".Click()";
		else if (command.equalsIgnoreCase("declare"))
			statement = "private String " + keyword + " = " + value;
		else
			statement = libLowerCase + "." + keyword + "." + command + "(" + value + ")";
		return statement + ";\n";
	}

	private String getname(String s) {
		String[] sArr = s.split("\n");
		sArr[0] = sArr[0].replace("###", "");
		return sArr[0];
	}

	private String getAnotation(String s) {
		String[] sArr = s.split("\n");
		if (sArr.length > 1 && sArr[1].startsWith("@")) {
			return sArr[1];
		}
		return "";
	}

	private String getTcDescription(String s) {
		String[] sArr = s.split("\n");
		String result = "";
		if (sArr.length > 1) {
			for (int i = 1; i < sArr.length; i++) {
				if (!sArr[i].startsWith("@"))
					result = result + "//" + sArr[i] + "\n";
			}
		}
		return result;
	}

	public __CopyOfTestNgClassExporter(Sheet sourceSheet) {
		this.sourceSheet = sourceSheet;
		getRowNumber(0, END_ROW_TOKEN);
	}

	public int getRowNumber(int columnNumber, String s) {
		for (int j = 0; j < 10000; j++) {
			if (sourceSheet.getCell(columnNumber, j).getContents().toLowerCase().contains(s)) {
				endRowNumber = j;
				break;
			}
		}
		return endRowNumber;
	}

	public String getCellContents(int column, int row) {
		return sourceSheet.getCell(column, row).getContents();
	}

	/**
	 * 
	 * format method and String to method parameter
	 * e.g. change mehtod form login(username, password) to login(String username, String password)
	 */
	public String addStringType2Parameter(String method ){
		String result =  "";
		String name = method.replaceAll("(.*?)(\\(.*?\\))", "$1");
		String parameters = method.replaceAll("(.*?\\()(.*?)(\\))", "$2");
		String[] sArr = parameters.split(",");
		for (String s: sArr){
			result = result + "String " + s + ",";
		}
		//remove the trail ","
		result = result.substring(0, result.length()-1);
		result = name + "(" + result + ")";
		return result;
	}
}
