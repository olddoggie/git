package mpd;

import org.apache.log4j.Logger;

import tools.Tools;

//done
public class S3C {
	private Account a;
	private Logger logger = Logger.getLogger(S3C.class);
	public S3C(Account a){
		this.a = a;
	}

	private Object[][] getDataTable() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s3c";
		String tableName = "s3cTable";
		String[][] dtatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		return dtatTable;
	}

	//a is the data in s3c table, for exmaple, "2 or 7"
	private boolean compareF01195_001(String b, String a) {
		String[] sArr = a.split("OR");
		for (String s : sArr) {
			if (s.trim().equalsIgnoreCase(b.trim()))
				return true;
		}
		return false;
	}

	public void process() {
		String[][] s3cArr = (String[][]) getDataTable();
		boolean negCounterCompare = false;
		boolean cppormCompare = false;
		boolean cppompCompare = false;
		
		
		for (String[] row : s3cArr) {
			String F01195_001_Column = row[0].trim();
			String F02073_001_Column = row[1].trim();
			String F02100_001_Column = row[2].trim();
			if (F01195_001_Column.equalsIgnoreCase("N/A") || a.F01195_001.equalsIgnoreCase("N/A") || compareF01195_001(a.F01195_001, F01195_001_Column)) {
				negCounterCompare = true;
			}
			if (F02073_001_Column.equalsIgnoreCase("N/A") || a.F02073_001.equalsIgnoreCase("N/A") || a.F02073_001.equalsIgnoreCase(F02073_001_Column)) {
				cppormCompare = true;
			}
			if (F02100_001_Column.equalsIgnoreCase("N/A") || a.F02100_001.equalsIgnoreCase("N/A") || a.F02100_001.equalsIgnoreCase(F02100_001_Column)) {
				cppompCompare = true;
			}

			if (negCounterCompare && cppormCompare && cppompCompare) {
				a.F01206_001 = Double.parseDouble(row[3].trim());
				a.F02092_001 = Double.parseDouble(row[4].trim().replace("$", ""));
				a.F01207_001 = Double.parseDouble(row[5].trim().replace("$", ""));
				a.F01205_001 = Integer.parseInt(row[6].trim().replace("$", ""));
				a.F01912_001 = row[7];
				a.F01913_001 = row[7];
				break;
			}
			negCounterCompare = false;
			cppormCompare = false;
			cppompCompare = false;			
		}
		printOutput();
	}
	
	public void printOutput(){
		logger.debug("S3C Output ------------------------- ");
		logger.info("F01205_001:" +  a.F01205_001);
	}
	public static void main(String[] args) {

	}
}
