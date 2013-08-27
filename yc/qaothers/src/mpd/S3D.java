package mpd;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

//done
public class S3D {
	private Account a;
	private Logger logger = Logger.getLogger(S3D.class);
	
	public S3D(Account a){
		this.a=a;
	}


	public void process() {
		if (a.F02100_001.equalsIgnoreCase("TIER$30")) {
			List<String> pricingCodeList1 = Arrays.asList("F017", "B538", "F018", "P412", "P413", "P804", "T296", "RETL", "T026", "RETH", "Z077", "T034",
					"Z078");
			List<String> pricingCodeList2 = Arrays.asList("P738", "P740", "Z108", "T054", "Z109", "Z124", "T320", "Z125");
			if (pricingCodeList1.contains(a.F01202_001)) {
				a.F02073_001 = "2.3% or $30";
				a.F01913_001 = "2.3% or $30";
				a.F01912_001 = "2.3% or $30";
			} else if (pricingCodeList2.contains(a.F01202_001)) {
				a.F02073_001 = "2.4% or $30";
				a.F01913_001 = "2.4% or $30";
				a.F01912_001 = "2.4% or $30";
			}
		}		
		printOutput();
	}
	
	public void printOutput(){
		logger.debug("S3D Output ------------------------- ");
		logger.info("F02073_001:" +  a.F02073_001);
		logger.info("F01913_001:" +  a.F01913_001);
		logger.info("F01912_001:" +  a.F01912_001);
	}
	public static void main(String[] args) {

	}
}
