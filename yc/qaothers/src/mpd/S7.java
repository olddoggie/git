package mpd;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import mpd.Tools;

//done
public class S7 {
	private Account a;
	private  Logger logger = Logger.getLogger(S7.class);
	
	public S7(Account a){
		this.a=a;
	}
	private  List<String> Post_NegAm_Grp;
	private  List<String> NegAm_Cycle_2_Potential_CU_Grp= Arrays.asList("NAC_2CC", "NAB_2CC");

	public  void process(){
		Post_NegAm_Grp = getPostNegAmGrp();
		process_S7A();
		process_S7B();
		process_S7C();	
	}

	public  void printS7COutput(){		
		logger.debug("S7C Output ------------------------- ");
		logger.info("F01912_001:" +  a.F01912_001);
		logger.info("F01900_001:" +  a.F01900_001);
		logger.debug("");
	}
	
	public  void printS7CInput(){		
		logger.debug("S7C Input ------------------------- ");
		logger.debug("F01912_001:" +  a.F01912_001);
		logger.debug("F01900_001:" +  a.F01900_001);

	}
	
	public  List<String> getPostNegAmGrp() {
		String xlFilePath = "resources/mpd_data.xls";
		String sheetName = "s7";
		String tableName = "postNegAmGrpArray";
		String[][] dataOrg = Tools.getDataByTableName(xlFilePath, sheetName,
				tableName);
		return Tools.convert2DimArray2OneDimList(dataOrg);
	}

	//Calculated Forecasted MPD a.F01900:001
	public  void process_S7A() {
		if((!Post_NegAm_Grp.contains(a.F02073_001)) && (a.F20034_003<(a.F02089_001+a.F02090_001+a.F02091_001+15))){
			a.F01900_001= a.F02089_001+a.F02090_001+a.F02091_001+15;
			a.F01912_001 = "LS FFC+$15 Ovrd";
		}else
		{
			a.F01900_001 = a.F20034_003;
		}
	}
	
	public  void process_S7B() {
		// need confirm
		if((!NegAm_Cycle_2_Potential_CU_Grp.contains(a.F02073_001)) &&a.F02188_001>0){
			a.F01900_001= a.F01900_001 + a.F02188_001;
			a.F01912_001 = a.F01912_001 + "+CU"; 
			}
	}
	
	public void process_S7C(){
		printS7CInput();
		if(a.F01900_001>a.F01921_001){
			a.F01900_001 =a.F01014_001; 
			a.F01912_001 = "CurBal Ovrd";
		}
		printS7COutput();
	}
	


	public static void main(String[] args) {

	}

}
