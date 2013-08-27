package tools;

import java.util.Arrays;

import org.apache.log4j.Logger;

import static tools.Account.*;

//done
public class S5 {
	private static Logger logger = Logger.getLogger(S5.class);
	public static void main(String[] args) {

	}

	// calculateF01907
	public static void process() {
		printInput();
		if (F01205_001 == 0) {
			F01907_001 = 0;
		} else if (Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 34, 35, 36, 37).contains(F01205_001)) {
			if (F01921_001 * F01206_001 < F01207_001) {
				logger.warn("F01921_001 * F01206_001 < F01207_001--" +  F01921_001 * F01206_001 + ":" + F01207_001);
				F01907_001 = F01207_001;
			} else {
				logger.warn("F01921_001 * F01206_001 > F01207_001--" +  F01921_001 * F01206_001 + ":" + F01207_001);
				F01907_001 = F01921_001 * F01206_001;
			}
		} else if (F01205_001 == 11 || F01205_001 == 12) {
			double minimumDueAmtPlusFC = F01207_001 + (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001;
			if (F01921_001 * F01206_001 < minimumDueAmtPlusFC) {
				logger.warn("F01921_001 * F01206_001: < minimumDueAmtPlusFC--" +  F01921_001 * F01206_001 + ":" + minimumDueAmtPlusFC);
				F01907_001 = minimumDueAmtPlusFC;
			} else {
				logger.warn("F01921_001 * F01206_001 > minimumDueAmtPlusFC--" +  F01921_001 * F01206_001 + ":" + minimumDueAmtPlusFC);
				F01907_001 = F01921_001 * F01206_001;
			}
		} else if (F01205_001 == 13) {
			double minimumDueamtPlusFCPlusFees = (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001 + F01211_001 + F01212_001 + F01207_001;
			if ((F01921_001 - F01021_001) < minimumDueamtPlusFCPlusFees) {
				logger.warn("F01921_001 - F01021_001 < minimumDueamtPlusFCPlusFees--" +  (F01921_001 - F01021_001) + ":" + minimumDueamtPlusFCPlusFees);
				F01907_001 = minimumDueamtPlusFCPlusFees;
			} else {
				logger.warn("01921_001 - F01021_001 > minimumDueamtPlusFCPlusFees--" +  (F01921_001 - F01021_001) + ":" + minimumDueamtPlusFCPlusFees);
				F01907_001 = F01921_001 - F01021_001;
			}
		} else if (F01205_001 == 19 || F01205_001 == 20) {
			double percentageOfBalancePlusFeesPlusFC = F01921_001 * F01206_001 + (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001 + F01211_001
					+ F01212_001;
			if (F01207_001 < percentageOfBalancePlusFeesPlusFC) {
				F01907_001 = percentageOfBalancePlusFeesPlusFC;
			} else {
				F01907_001 = F01207_001;
			}
		} else if (F01205_001 == 21) {
			double feesPlusFCPlusAddOn = (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001 + F01211_001 + F01212_001 + F02092_001;
			double F01921_001XF01206_001 = F01921_001 * F01206_001;
			F01907_001 = Math.max(feesPlusFCPlusAddOn, Math.max(F01921_001, F01207_001));
		} else if (F01205_001 == 30 || F01205_001 == 31) {
			if (F01016_001 > 0 || F01003 != 8) {
				double feesPlusFC = (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001 + F01211_001 + F01212_001;
				F01907_001 = feesPlusFC + 1;
			} else {
				if (F01207_001 < F01921_001 * F01206_001) {
					F01907_001 = F01921_001 * F01206_001;
				} else {
					F01907_001 = F01207_001;
				}
			}
		} else if (F01205_001 == 32) {
			double percentageOfBalnancePlusLateFee = F01921_001 * F01206_001 + (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001;
			if (F01207_001 < percentageOfBalnancePlusLateFee) {
				F01907_001 = percentageOfBalnancePlusLateFee;
			} else {
				F01907_001 = F01207_001;
			}
		} else if (F01205_001 == 33) {
			double percentageOfBalnancePlusFee = F01921_001 * F01206_001 + F01211_001 + F01212_001;
			if (F01207_001 < percentageOfBalnancePlusFee) {
				F01907_001 = percentageOfBalnancePlusFee;
			} else {
				F01907_001 = F01207_001;
			}
		} else if (F01205_001 == 38 || F01205_001 == 39) {
			double percentageOfBalnancePlusFeePlusFC = F01921_001 * F01206_001 + (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001 + F01211_001
					+ F01212_001;
			if (F01207_001 < percentageOfBalnancePlusFeePlusFC) {
				logger.warn("F01207_001 < percentageOfBalnancePlusFeePlusFC--"  + F01207_001 + ":" + percentageOfBalnancePlusFeePlusFC);
				F01907_001 = percentageOfBalnancePlusFeePlusFC;
			} else {
				logger.warn("F01207_001 > percentageOfBalnancePlusFeePlusFC--"  + F01207_001 + ":" + percentageOfBalnancePlusFeePlusFC);
				F01907_001 = F01207_001;
			}
		} else if (F01205_001 == 40 || F01205_001 == 41) {
			double temp = F01921_001 * F01206_001 + F02092_001;
			if (F01207_001 < temp) {
				F01907_001 = temp;
			} else {
				F01907_001 = F01207_001;
			}
		} else if (F01205_001 == 42) {
			double percentageOfBalancePlusFeesPlusFCPlusMF = F01921_001 * F01206_001 + (F01921_001 - F01049_001) * F01904_001 + F01049_001 * F01906_001
					+ F01211_001 + F01212_001 + F01918_001;
			if (F01207_001 < percentageOfBalancePlusFeesPlusFCPlusMF) {
				F01907_001 = percentageOfBalancePlusFeesPlusFCPlusMF;
			} else {
				F01907_001 = F01207_001;
			}
		}
		// this is the new step in mpd doc
		F20034_003 = F01907_001;
		printOutput();
	}
	
	
	public static void printInput(){
		logger.warn("S5 Input ------------------------- ");
		logger.error("F01205_001:" +  F01205_001);
	}
	public static void printOutput(){
		logger.warn("S5 Output ------------------------- ");
		logger.error("F01907_001:" +  F01907_001);
		logger.error("F20034_003:" +  F20034_003);

	}
}
