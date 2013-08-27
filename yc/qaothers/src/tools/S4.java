package tools;

import static tools.Account.*;

import org.apache.log4j.Logger;
//done
/**
 * Calculate Estimated Daily and Monthly Percentage Rate for Merchandise/Cash and CTD FC
 * F01909_001 and F01908_001 are not used in the following mpd steps
 */
public class S4 {
	private static Logger logger = Logger.getLogger(S4.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void process(){
		printInput();
		processMechandise();
		processCash();
		//F01921_001 is higher than F01014_001, for example, 2032 vs 2000
		F01921_001=(F01014_001-F01049_001)*F01904_001 + F01049_001*F01906_001 + F01014_001;
		printOutput();
	}
	public static void processMechandise() {
		if ((F01208_001 + F01209_001) < F01210_001) {
			F01903_001 = F01210_001 / 365;	
		} else {
			F01903_001 = (F01208_001 + F01209_001) / 365;			
		}
		F01904_001 = F01903_001 * 33;
		F01908_001 = (F01014_001 - F01049_001) * F01903_001 * F01911_001;
	}

	public static void processCash() {
		if ((F01216_001 + F01217_001) < F01218_001) {
			F01905_001 = F01218_001 / 365;
		} else {
			F01905_001 = (F01216_001 + F01217_001) / 365;
		}
		F01906_001 = F01905_001 * 33;
		F01909_001 = F01049_001 * F01905_001 * F01911_001;
	}
	
	public static void printInput(){
	logger.warn("S4 Output ------------------------- ");
	logger.warn("F01902_001:" +  F01902_001);
	logger.warn("F01914_001:" +  F01914_001);
	logger.warn("F01917_001:" +  F01917_001);		
	logger.warn("F01915_001:" +  F01915_001);
	logger.warn("F01920_001:" +  F01920_001);
}
	public static void printOutput(){
	logger.warn("S4 Output ------------------------- ");
	logger.warn("F01904_001:" +  F01904_001);
	logger.warn("F01908_001:" +  F01908_001);
	logger.warn("F01905_001:" +  F01905_001);		
	logger.warn("F01909_001:" +  F01909_001);
	logger.warn("F01912_001:" +  F01912_001);
	logger.warn("");
}
	
}
