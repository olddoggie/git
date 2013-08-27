package mpd;

import org.apache.log4j.Logger;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import static tools.Account.*;

// not done -- find the field name of Current Pricing Description 
public class S2C {	
	private static Logger logger = Logger.getLogger(S2C.class);
	private Account a;

	public S2C(Account a){
		this.a = a;
	}
	public void main(String[] args)  {
		//System.out.println(geta.F02073_001_CPPORM("MPDPN108","N/A","2200"));
	}
	
	private static Object[][] getDataTable() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s2c";
		String tableName = "s2cTable";
		String[][] datatTable = Tools.getDataByTableName(xlFilePath, sheetName, tableName);
		return datatTable;
	}
	
	public void printInput(){
		logger.debug("S2C Input ------------------------- ");
		logger.debug("F02073_001:" +  a.F02073_001);
		logger.debug("F02100_001:" +  a.F02100_001);
		logger.debug("F01041_001:" +  a.F01014_001);
	}
	public void printOutput(){
		logger.debug("S2C Output ------------------------- ");
		logger.debug("F02086_001:" +  a.F02086_001);
		logger.debug("");
	}
	
	public void process() {
		printInput();
		String[][] s2cArr = (String[][]) getDataTable();	
		boolean currentBalanceCompare= false;
		boolean cppormCompare= false;
		boolean cppompCompare= false;
		for(String[] row: s2cArr){	
			String F02073_001_Column = row[0].trim();
			String F02100_001_Column = row[1].trim();			
			String F01014_001_Column = row[2].trim();
			// need change row index if the line column is not included -- not done
			if(F02073_001_Column.equalsIgnoreCase("N/A") ||a.F02073_001.equalsIgnoreCase("N/A") || a.F02073_001.equalsIgnoreCase(F02073_001_Column)){
				cppormCompare = true;
			}
			if(F02100_001_Column.equalsIgnoreCase("N/A") || a.F02100_001.equalsIgnoreCase("N/A") || a.F02100_001.equalsIgnoreCase(F02100_001_Column)){
				cppompCompare = true;
			}
			if(F01014_001_Column.equalsIgnoreCase("N/A") || ("" + a.F01014_001).equalsIgnoreCase("N/A")){
				currentBalanceCompare = true;
			}else{
				currentBalanceCompare=compareBalance(("" + a.F01014_001), F01014_001_Column);
			}
			
			if(currentBalanceCompare&&cppormCompare&&cppompCompare){
				a.F02086_001 = row[3].trim();
			}
			
			currentBalanceCompare = false;
			cppormCompare = false;
			cppompCompare = false;
		}
		printOutput();
	}

	public boolean compareBalance(String a, String b){
		ExpressionParser parser = new SpelExpressionParser();
		String expStr;
		if(a.matches("\\d+")&&!(b.matches("\\d+"))) {
			expStr = b.replaceAll("x", a);
			return (Boolean)parser.parseExpression(expStr).getValue();	
		}
		if(b.matches("\\d+")&&!(a.matches("\\d+"))) {
			expStr = a.replaceAll("x", b);
			return (Boolean)parser.parseExpression(expStr).getValue();	
		}		
		return a.equals(b);			}
	
	}
