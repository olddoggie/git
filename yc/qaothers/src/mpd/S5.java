package mpd;

import java.util.Arrays;
import org.apache.log4j.Logger;

public class S5 {
	private Account a;
	private Logger logger = Logger.getLogger(S5.class);
	
	public S5(Account a){
		this.a=a;
	}

	// calculatea.F01907
	public void process() {
		printInput();
		if (a.F01205_001 == 0) {
			a.F01907_001 = 0;
		} else if (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 34, 35, 36, 37).contains(a.F01205_001)) {
			if (a.F01921_001 * a.F01206_001 < a.F01207_001) {
				logger.info("1921_001 * a.F01206_001 < a.F01207_001--" +  a.F01921_001 * a.F01206_001 + ":" + a.F01207_001);
				a.F01907_001 = a.F01207_001;
			} else {
				logger.info("1921_001 * a.F01206_001 > a.F01207_001--" +  a.F01921_001 * a.F01206_001 + ":" + a.F01207_001);
				a.F01907_001 = a.F01921_001 * a.F01206_001;
			}
		} else if (a.F01205_001 == 11 || a.F01205_001 == 12) {
			double minimumDueAmtPlusFC = a.F01207_001 + (a.F01921_001 - a.F01049_001) * a.F01904_001 + a.F01049_001 * a.F01906_001;
			if (a.F01921_001 * a.F01206_001 < minimumDueAmtPlusFC) {
				logger.info("F01921_001 * a.F01206_001: < minimumDueAmtPlusFC--" +  a.F01921_001 * a.F01206_001 + ":" + minimumDueAmtPlusFC);
				a.F01907_001 = minimumDueAmtPlusFC;
			} else {
				logger.info("F01921_001 * a.F01206_001 > minimumDueAmtPlusFC--" +  a.F01921_001 * a.F01206_001 + ":" + minimumDueAmtPlusFC);
				a.F01907_001 = a.F01921_001 * a.F01206_001;
			}
		} else if (a.F01205_001 == 13) {
			double minimumDueamtPlusFCPlusFees = (a.F01921_001 - a.F01049_001) * a.F01904_001 + a.F01049_001 * a.F01906_001 + a.F01211_001 + a.F01212_001 + a.F01207_001;
			if ((a.F01921_001 - a.F01021_001) < minimumDueamtPlusFCPlusFees) {
				logger.info("1921_001 - a.F01021_001 < minimumDueamtPlusFCPlusFees--" +  (a.F01921_001 - a.F01021_001) + ":" + minimumDueamtPlusFCPlusFees);
				a.F01907_001 = minimumDueamtPlusFCPlusFees;
			} else {
				logger.info("F01921_001 - a.F01021_001 > minimumDueamtPlusFCPlusFees--" +  (a.F01921_001 - a.F01021_001) + ":" + minimumDueamtPlusFCPlusFees);
				a.F01907_001 = a.F01921_001 - a.F01021_001;
			}
		} else if (Arrays.asList(19, 20, 38, 39).contains(a.F01205_001)) {
			double percentageOfBalancePlusFeesPlusFC = a.F01921_001 * a.F01206_001 + (a.F01921_001 - a.F01049_001) * a.F01904_001 + a.F01049_001 * a.F01906_001 + a.F01211_001
					+ a.F01212_001;
			if (a.F01207_001 < percentageOfBalancePlusFeesPlusFC) {
				logger.info("F01207_001 < percentageOfBalancePlusFeesPlusFC---" +  (a.F01207_001) + ":" + percentageOfBalancePlusFeesPlusFC);
				a.F01907_001 = percentageOfBalancePlusFeesPlusFC;
			} else {
				logger.info("F01207_001 > percentageOfBalancePlusFeesPlusFC---" +  (a.F01207_001) + ":" + percentageOfBalancePlusFeesPlusFC);
				a.F01907_001 = a.F01207_001;
			}
		} else if (a.F01205_001 == 21) {
			// this is obsolete
			double feesPlusFCPlusAddOn = (a.F01921_001 - a.F01049_001) * a.F01904_001 + a.F01049_001 * a.F01906_001 + a.F01211_001 + a.F01212_001 + a.F02092_001;
			double F01921_001XF01206_001 = a.F01921_001 * a.F01206_001;
			a.F01907_001 = Math.max(feesPlusFCPlusAddOn, Math.max(F01921_001XF01206_001, a.F01207_001));
		} else if (a.F01205_001 == 30 || a.F01205_001 == 31) {
			if (a.F01016_001 > 0 || a.F01003 != 8) {
				double feesPlusFC = (a.F01921_001 - a.F01049_001) * a.F01904_001 + a.F01049_001 * a.F01906_001 + a.F01211_001 + a.F01212_001;
				a.F01907_001 = feesPlusFC + 1;
			} else {
				if (a.F01207_001 < a.F01921_001 * a.F01206_001) {
					logger.info("F01207_001 < F01921_001 * a.F01206_001---" +  a.F01207_001 + ":" + a.F01921_001 * a.F01206_001);
					a.F01907_001 = a.F01921_001 * a.F01206_001;
				} else {
					logger.info("F01207_001 > F01921_001 * a.F01206_001---" +  a.F01207_001 + ":" + a.F01921_001 * a.F01206_001);
					a.F01907_001 = a.F01207_001;
				}
			}
		} else if (a.F01205_001 == 32) {
			double percentageOfBalnancePlusLateFee = a.F01921_001 * a.F01206_001 + (a.F01921_001 - a.F01049_001) * a.F01904_001 + a.F01049_001 * a.F01906_001 + a.F01211_001;
			if (a.F01207_001 < percentageOfBalnancePlusLateFee) {
				logger.info("F01207_001 < percentageOfBalnancePlusLateFee--"  + a.F01207_001 + ":" + percentageOfBalnancePlusLateFee);
				a.F01907_001 = percentageOfBalnancePlusLateFee;
			} else {
				logger.info("F01207_001 > percentageOfBalnancePlusLateFee--"  + a.F01207_001 + ":" + percentageOfBalnancePlusLateFee);
				a.F01907_001 = a.F01207_001;
			}
		} else if (a.F01205_001 == 33) {
			double percentageOfBalnancePlusFee = a.F01921_001 * a.F01206_001 + a.F01211_001 + a.F01212_001;
			if (a.F01207_001 < percentageOfBalnancePlusFee) {
				logger.info("F01207_001 < percentageOfBalnancePlusFee--"  + a.F01207_001 + ":" + percentageOfBalnancePlusFee);
				a.F01907_001 = percentageOfBalnancePlusFee;
			} else {
				logger.info("F01207_001 > percentageOfBalnancePlusFee--"  + a.F01207_001 + ":" + percentageOfBalnancePlusFee);
				a.F01907_001 = a.F01207_001;
			}
		} else if (a.F01205_001 == 40 || a.F01205_001 == 41) {
			double temp = a.F01921_001 * a.F01206_001 + a.F02092_001;
			if (a.F01207_001 < temp) {
				logger.info("F1207_001 < F01921_001 * F01206_001 + F02092_001--"  + a.F01207_001 + ":" + temp);
				a.F01907_001 = temp;
			} else {
				logger.info("F1207_001 > F01921_001 * F01206_001 + F02092_001--"  + a.F01207_001 + ":" + temp);
				a.F01907_001 = a.F01207_001;
			}
		} else if (a.F01205_001 == 42) {
			double percentageOfBalancePlusFeesPlusFCPlusMF = a.F01921_001 * a.F01206_001 + (a.F01921_001 - a.F01049_001) * a.F01904_001 + a.F01049_001 * a.F01906_001
					+ a.F01211_001 + a.F01212_001 + a.F01918_001;
			if (a.F01207_001 < percentageOfBalancePlusFeesPlusFCPlusMF) {
				logger.info("F01207_001 < percentageOfBalancePlusFeesPlusFCPlusMF--"  + a.F01207_001 + ":" + percentageOfBalancePlusFeesPlusFCPlusMF);
				a.F01907_001 = percentageOfBalancePlusFeesPlusFCPlusMF;
			} else {
				logger.info("F01207_001 > percentageOfBalancePlusFeesPlusFCPlusMF--"  + a.F01207_001 + ":" + percentageOfBalancePlusFeesPlusFCPlusMF);
				a.F01907_001 = a.F01207_001;
			}
		}
		// this is the new step in mpd doc
		a.F20034_003 = a.F01907_001;
		printOutput();
	}
	
	
	public void printInput(){
		logger.info("S5 Input ------------------------- ");
		logger.info("F01205_001:" +  a.F01205_001);
	}
	public void printOutput(){
		logger.info("S5 Output ------------------------- ");
		logger.info("F01907_001:" +  a.F01907_001);
		logger.info("F20034_003:" +  a.F20034_003);

	}
}
