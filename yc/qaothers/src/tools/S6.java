package tools;

import static tools.Account.*;

import org.apache.log4j.Logger;

//done
public class S6 {
	private static Logger logger = Logger.getLogger(S6.class);
	public static void main(String[] args) {

		
	}

//the Cure Forecasted MPD
public static void process () {
	printInput();
	if(F20034_003<F01915_001){
		F20034_003= F01915_001;
		F01913_001 = "$" + F01915_001 + " MPD Amt Allowable Ovrd";
		F01912_001 = "$" + F01915_001 + " MPD Amt Allowable Ovrd";
		}
	
	if(F20034_003>F01921_001){
		F20034_003 = F01921_001;
		F01913_001 = "CurBal Ovrd";
		F01912_001 = "CurBal Ovrd";
		}
	printOutput();
	}

public static void printInput(){
	logger.warn("S6 Input ------------------------- ");
	logger.warn("F01915_001:" +  F01915_001);
	logger.warn("F01921_001:" +  F01921_001);
	logger.warn("F20034_003:" +  F20034_003);
}
public static void printOutput(){
	logger.warn("S6 Output ------------------------- ");
	logger.error("F01913_001:" +  F01913_001);
	//logger.warn("F01912_001:" +  F01912_001);
	logger.error("F20034_003:" +  F20034_003);
}

}
