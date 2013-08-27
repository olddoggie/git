package mpd;

import org.apache.log4j.Logger;
//done
/**
 * Calculate Estimated Daily and Monthly Percentage Rate for Merchandise/Cash and CTD FC
 * a.F01909_001 and a.F01908_001 are not used in the following mpd steps
 */
public class S4 {
	private Account a;
	private static Logger logger = Logger.getLogger(S4.class);
	
	public S4(Account a){
		this.a=a;
	}

	public void process(){
		printInput();
		processMechandise();
		processCash();
		//a.F01921_001 is higher than a.F01014_001, for example, 2032 vs 2000
		a.F01921_001=(a.F01014_001-a.F01049_001)*a.F01904_001 + a.F01049_001*a.F01906_001 + a.F01014_001;
		printOutput();
	}
	public void processMechandise() {
		if ((a.F01208_001 + a.F01209_001) < a.F01210_001) {
			a.F01903_001 = a.F01210_001 / 365;	
		} else {
			a.F01903_001 = (a.F01208_001 + a.F01209_001) / 365;			
		}
		a.F01904_001 = a.F01903_001 * 33;
		a.F01908_001 = (a.F01014_001 - a.F01049_001) * a.F01903_001 * a.F01911_001;
	}

	public void processCash() {
		if ((a.F01216_001 + a.F01217_001) < a.F01218_001) {
			a.F01905_001 = a.F01218_001 / 365;
		} else {
			a.F01905_001 = (a.F01216_001 + a.F01217_001) / 365;
		}
		a.F01906_001 = a.F01905_001 * 33;
		a.F01909_001 = a.F01049_001 * a.F01905_001 * a.F01911_001;
	}
	
	public void printInput(){
	logger.debug("S4 Output ------------------------- ");
	logger.debug("F01902_001:" +  a.F01902_001);
	logger.debug("F01914_001:" +  a.F01914_001);
	logger.debug("F01917_001:" +  a.F01917_001);		
	logger.debug("F01915_001:" +  a.F01915_001);
	logger.debug("F01920_001:" +  a.F01920_001);
}
	public void printOutput(){
	logger.debug("S4 Output ------------------------- ");
	logger.debug("F01904_001:" +  a.F01904_001);
	logger.debug("F01908_001:" +  a.F01908_001);
	logger.debug("F01905_001:" +  a.F01905_001);		
	logger.debug("F01909_001:" +  a.F01909_001);
	logger.debug("F01912_001:" +  a.F01912_001);
	logger.debug("");
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
