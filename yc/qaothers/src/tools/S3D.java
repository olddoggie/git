package tools;

import java.util.Arrays;
import java.util.List;

import tools.Tools;
import static tools.Account.*;

//done
public class S3D {

	public static void main(String[] args) {

	}

	public static void process() {
		if (F02100_001.equalsIgnoreCase("TIER$30")) {
			List<String> pricingCodeList1 = Arrays.asList("F017", "B538", "F018", "P412", "P413", "P804", "T296", "RETL", "T026", "RETH", "Z077", "T034",
					"Z078");
			List<String> pricingCodeList2 = Arrays.asList("P738", "P740", "Z108", "T054", "Z109", "Z124", "T320", "Z125");
			if (pricingCodeList1.contains(F01202_001)) {
				F02073_001 = "2.3% or $30";
				F01913_001 = "2.3% or $30";
				F01912_001 = "2.3% or $30";
			} else if (pricingCodeList2.contains(F01202_001)) {
				F02073_001 = "2.4% or $30";
				F01913_001 = "2.4% or $30";
				F01912_001 = "2.4% or $30";
			}
		}
	}
}
