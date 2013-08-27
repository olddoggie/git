package mpd;

import org.apache.log4j.Logger;

//done
public class S6 {
	private Account a;
	private Logger logger = Logger.getLogger(S6.class);
	
	public S6(Account a){
		this.a=a;
	}


//the Cure Forecasted MPD
public void process () {
	printInput();
	//F20034_003 is the same as cure mpd from step 5
	if(a.F20034_003<a.F01915_001){
		a.F20034_003= a.F01915_001;
		a.F01913_001 = "$" + a.F01915_001 + " MPD Amt Allowable Ovrd";
		a.F01912_001 = "$" + a.F01915_001 + " MPD Amt Allowable Ovrd";
		}
	
	if(a.F20034_003>a.F01921_001){
		a.F20034_003 = a.F01921_001;
		a.F01913_001 = "CurBal Ovrd";
		a.F01912_001 = "CurBal Ovrd";
		}
	printOutput();
	}

public void printInput(){
	logger.debug("S6 Input ------------------------- ");
	logger.debug("F01915_001:" +  a.F01915_001);
	logger.debug("F01921_001:" +  a.F01921_001);
	logger.debug("F20034_003:" +  a.F20034_003);
}
public void printOutput(){
	logger.debug("S6 Output ------------------------- ");
	logger.info("F01913_001:" +  a.F01913_001);
	//logger.debug("F01912_001:" +  a.F01912_001);
	logger.info("F20034_003:" +  a.F20034_003);
}

public static void main(String[] args) {
	
}
}
