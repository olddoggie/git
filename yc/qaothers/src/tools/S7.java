package tools;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import tools.Tools;
import static tools.Account.*;
//done
public class S7 {

	private static List<String> Post_NegAm_Grp;
	private static List<String> NegAm_Cycle_2_Potential_CU_Grp= Arrays.asList("NAC_2CC", "NAB_2CC");
	private static Logger logger = Logger.getLogger(S7.class);

	public static void main(String[] args) {

	}

	public static void process(){
		Post_NegAm_Grp = getPostNegAmGrp();
		process_S7A();
		process_S7B();
		process_S7C();
		

	}

	public static void printS7COutput(){		
		logger.warn("S7C Output ------------------------- ");
		logger.error("F01912_001:" +  F01912_001);
		logger.error("F01900_001:" +  F01900_001);
		logger.warn("");
	}
	
	public static void printS7CInput(){		
		logger.warn("S7C Input ------------------------- ");
		logger.warn("F01912_001:" +  F01912_001);
		logger.warn("F01900_001:" +  F01900_001);

	}
	
	public static List<String> getPostNegAmGrp() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s7";
		String tableName = "postNegAmGrpArray";
		String[][] dataOrg = Tools.getDataByTableName(xlFilePath, sheetName,
				tableName);
		return Tools.convert2DimArray2OneDimList(dataOrg);
	}

	//Calculated Forecasted MPD F01900:001
	public static void process_S7A() {
		if((!Post_NegAm_Grp.contains(F02073_001)) && (F20034_003<(F02089_001+F02090_001+F02091_001+15))){
			F01900_001= F02089_001+F02090_001+F02091_001+15;
			F01912_001 = "LS FFC+$15 Ovrd";
		}else
		{
			F01900_001 = F20034_003;
		}
	}
	
	public static void process_S7B() {
		// need confirm
		if((!NegAm_Cycle_2_Potential_CU_Grp.contains(F02073_001)) &&F02188_001>0){
			F01900_001= F01900_001 + F02188_001;
			F01912_001 = F01912_001 + "+CU"; 
			}
	}
	
	public static void process_S7C(){
		printS7CInput();
		if(F01900_001>F01921_001){
			F01900_001 =F01014_001; 
			F01912_001 = "CurBal Ovrd";
		}
		printS7COutput();
	}
}
