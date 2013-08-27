package tools;

import tools.Tools;
import static tools.Account.*;

//done
public class S3C {

	public static void main(String[] args) {

	}

	private static Object[][] getDataTable() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s3c";
		String tableName = "s3cTable";
		String[][] dtatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		return dtatTable;
	}

	//a is the data in s3c table, for exmaple, "2 or 7"
	private static boolean compareF01195_001(String b, String a) {
		String[] sArr = a.split("OR");
		for (String s : sArr) {
			if (s.trim().equalsIgnoreCase(b.trim()))
				return true;
		}
		return false;
	}

	public static void process() {
		String[][] s3cArr = (String[][]) getDataTable();
		boolean negCounterCompare = false;
		boolean cppormCompare = false;
		boolean cppompCompare = false;
		
		
		for (String[] row : s3cArr) {
			String F01195_001_Column = row[0].trim();
			String F02073_001_Column = row[1].trim();
			String F02100_001_Column = row[2].trim();
			if (F01195_001_Column.equalsIgnoreCase("N/A") || F01195_001.equalsIgnoreCase("N/A") || compareF01195_001(F01195_001, F01195_001_Column)) {
				negCounterCompare = true;
			}
			if (F02073_001_Column.equalsIgnoreCase("N/A") || F02073_001.equalsIgnoreCase("N/A") || F02073_001.equalsIgnoreCase(F02073_001_Column)) {
				cppormCompare = true;
			}
			if (F02100_001_Column.equalsIgnoreCase("N/A") || F02100_001.equalsIgnoreCase("N/A") || F02100_001.equalsIgnoreCase(F02100_001_Column)) {
				cppompCompare = true;
			}

			if (negCounterCompare && cppormCompare && cppompCompare) {
				F01206_001 = Double.parseDouble(row[3].trim());
				F02092_001 = Double.parseDouble(row[4].trim().replace("$", ""));
				F01207_001 = Double.parseDouble(row[5].trim().replace("$", ""));
				F01205_001 = Integer.parseInt(row[6].trim().replace("$", ""));
				F01912_001 = row[7];
				F01913_001 = row[7];
				break;
			}
			negCounterCompare = false;
			cppormCompare = false;
			cppompCompare = false;
			
		}
		
		System.out.println(F01912_001);

	}
}
