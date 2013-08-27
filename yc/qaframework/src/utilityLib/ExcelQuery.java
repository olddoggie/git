package utilityLib;

import java.sql.*;
/**
 * 
 * @author jyu
 * reference url: http://xytang.blogspot.com/2008/06/sun-jdbc-odbc-driver-privates.html
 */
public class ExcelQuery {
	public static void main(String[] args) {
		String sqlStmt;
		//sqlStmt = "Select * from [3750$] where INRL_STTS_CD = 'O'";
		sqlStmt = "Select * from [3750$] where INRL_STTS_CD = 'O'";
		printQuery(sqlStmt);
	}

	
	public static void printQuery(String s)
	{

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			 //String url = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=C:/Documents and Settings/myPath/Desktop/qa.xls");
	        String url = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=resource/3750.xls;"
	                + "DriverID=22;READONLY=false";
			//Connection con = DriverManager.getConnection("jdbc:odbc:fdr"); // this is using odbc
	        //java.sql.DriverManager.getConnection( "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=C:/Documents and Settings/myPath/Desktop/qa.xls");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			//s = "Select * from [3750$] where INRL_STTS_CD = 'O' and "
			ResultSet rs = st.executeQuery(s);

			while (rs.next()) {

				String accountNumber = rs.getString(2);
				int creditLimit = rs.getInt(4);
				double currentBalance = rs.getInt(5);				
				double unPaidBalance = rs.getDouble(6);
				int daysDlinqCt = rs.getInt(7);
				String internalStatus = rs.getString(9);

				if (unPaidBalance - (int) unPaidBalance > 0) {
					if (daysDlinqCt < 60) {
						 System.out.print(accountNumber);
						 System.out.print(", ");
						 System.out.print(creditLimit);
						 System.out.print(", ");
						 System.out.print(currentBalance);
						 System.out.print(", ");
						 System.out.print(unPaidBalance);
						 System.out.print(", ");
						 System.out.print(daysDlinqCt);
						 System.out.print(", ");
						 System.out.print(internalStatus);
						 System.out.print(", ");
						 System.out.println("");
					}

				}

			}

			st.close();
			con.close();
			System.out.println("done");

		} catch (Exception ex) {
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
		}
	}
}
