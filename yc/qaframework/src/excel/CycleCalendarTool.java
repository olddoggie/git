package excel;

import java.io.File;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * 1. Run this class to generate the expected result based on The Ultimate Cycle Calendar.xls
 * 2. Copy the expected result from console window and saved as expected Result from cyclecaldendartool_java.TXT
 * 3. Run the following query
 *  select * from dbo.CycleDueDate where CycleDate>='2013-01-01 00:00:00.000' order by cpicsp asc, CycleDate asc ,CycleCode asc, StmtDueDate asc 
 *  in sql server >CollRep database
 *  4. Save the query result to actual result from sqlserver.csv
 *  5. Compare the expected result to the actual result using ultra compare
 */
public class CycleCalendarTool {
	private Sheet sourceSheet;
	private String calendar;
	private int[] cArr = new int[22];
	private int currentYear;

	public static void main(String[] args) throws BiffException, IOException {
		int currentYear = 2013;
		String filePath = "resource/The Ultimate Cycle Calendar.xls";
		
		Workbook sourceBook = Workbook.getWorkbook(new File(filePath));
		Sheet sourceSheet = sourceBook.getSheet(Integer.toString(currentYear));
		CycleCalendarTool tool = new CycleCalendarTool(currentYear,sourceSheet, "24DAYGRC");
		tool.generateRecords();
		tool = new CycleCalendarTool(currentYear,sourceSheet, "25DAYGRC");
		tool.generateRecords();
		
		
	}


	public CycleCalendarTool(int currentYear,Sheet sourceSheet,  String calendar) {
		this.currentYear = currentYear;
		this.sourceSheet = sourceSheet;
		this.calendar = calendar;
		setStatementDayArray();
	}

	public void generateRecords() {
		int statementYear;
		int cycleMonth;
		int statementMonth;
		int cycleDay;
		int statementDay;
		int cycle;
		String statement = "";

		for (int r = 1; r < 37; r = r + 3) {
			for (int c = 1; c < 75; c = c + 1) {
				String cycleMonthString = sourceSheet.getCell(0, r).getContents();
				String cycleDayString = sourceSheet.getCell(c, r).getContents();
				String cycleString = sourceSheet.getCell(c, r + 1).getContents();
				cycleDay = String2Int(cycleDayString);
				cycle = String2Int(cycleString);
				cycleMonth = getMonth(cycleMonthString);

				statementMonth = getStatementMonth(cycle, cycleMonth);
				statementYear = getStatementYear(cycle, cycleMonth);
				statementDay = cArr[cycle];

				// 1. cycle day is NOT empty
				if (!cycleDayString.isEmpty()) {
					// 1.1 cycle is empty
					if (cycleString.isEmpty()) {
						statement = currentYear + "-" + add021digitNumber(cycleMonth) + "-" + add021digitNumber(cycleDay) + " 00:00:00.000,0,NULL,"
								+ calendar.toUpperCase();
						System.out.println(statement);
					}
					// 1.2. cycle is not empty
					else {
						statement = currentYear + "-" + add021digitNumber(cycleMonth) + "-" + add021digitNumber(cycleDay) + " 00:00:00.000," + cycle
								+ "," + statementYear + "-" + add021digitNumber(statementMonth) + "-" + add021digitNumber(statementDay)
								+ " 00:00:00.000," + calendar.toUpperCase();
						System.out.println(statement);
					}
				}
				// 2. cycle is NOT empty and cycle date is empty
				if (cycleDayString.isEmpty() && !cycleString.isEmpty()) {
					cycleDay = String2Int(sourceSheet.getCell(c - 1, r).getContents());
					statement = currentYear + "-" + add021digitNumber(cycleMonth) + "-" + add021digitNumber(cycleDay) + " 00:00:00.000," + cycle
							+ "," + statementYear + "-" + add021digitNumber(statementMonth) + "-" + add021digitNumber(statementDay)
							+ " 00:00:00.000," + calendar.toUpperCase();
					System.out.println(statement);
				}
			}
		}

	}

	private int getStatementMonth(int cycle, int cycleMonth) {
		int statementMonth = cycleMonth;
		if (cycle > 2) {
			statementMonth = cycleMonth + 1;
			if (statementMonth > 12)
				statementMonth = 1;
		}
		return statementMonth;
	}

	private int getStatementYear(int cycle, int cycleMonth) {
		int statementYear = currentYear;
		if (cycle > 2 && cycleMonth == 12) {
			statementYear = currentYear + 1;
		}
		return statementYear;
	}

	private int String2Int(String s) {
		int myInt = 0;
		if (!s.isEmpty()) {
			myInt = Integer.parseInt(s);
		}
		return myInt;
	}

	private void setStatementDayArray() {
		int i = 1;
		cArr[0] = 0;
		if (calendar.contains("24DAYGRC")) {
			for (int c = 1; c < 76; c = c + 3) {
				if (!sourceSheet.getCell(c, 39).getContents().isEmpty()) {
					String temp = sourceSheet.getCell(c, 39).getContents();
					cArr[i] = Integer.parseInt(temp.substring(0, temp.length() - 2));
				}
				i++;
			}
		} else {
			for (int c = 1; c < 76; c = c + 3) {
				if (!sourceSheet.getCell(c, 40).getContents().isEmpty()) {
					String temp = sourceSheet.getCell(c, 40).getContents();
					cArr[i] = Integer.parseInt(temp.substring(0, temp.length() - 2));
				}
				i++;
			}
		}
	}

	private String add021digitNumber(int i) {
		String day;
		if (i < 10 && i > 0) {
			day = "0" + i;
		} else {
			day = Integer.toString(i);
		}
		return day;
	}

	private int getMonth(String s) {
		int month = 0;
		if (s.equalsIgnoreCase("January")) {
			month = 1;
		} else if (s.equalsIgnoreCase("February")) {
			month = 2;
		} else if (s.equalsIgnoreCase("March")) {
			month = 3;
		} else if (s.equalsIgnoreCase("April")) {
			month = 4;
		} else if (s.equalsIgnoreCase("May")) {
			month = 5;
		} else if (s.equalsIgnoreCase("June")) {
			month = 6;
		} else if (s.equalsIgnoreCase("July")) {
			month = 7;
		} else if (s.equalsIgnoreCase("August")) {
			month = 8;
		} else if (s.equalsIgnoreCase("September")) {
			month = 9;
		} else if (s.equalsIgnoreCase("October")) {
			month = 10;
		} else if (s.equalsIgnoreCase("November")) {
			month = 11;
		} else if (s.equalsIgnoreCase("December")) {
			month = 12;
		}
		return month;
	}

}
