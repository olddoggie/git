package tools;

import java.util.Arrays;
import java.util.List;
import static tools.Account.*;

//done
public class S2D {

	public static void main(String[] args) {

	}

	public static void process() {
		if (F02100_001.equalsIgnoreCase("TIER$30")) {

			if (Arrays.asList("F017", "B538", "P412", "P804", "P738", "T296", "Z108", "T054", "RETL", "T026", "Z077", "T034", "Z124", "T320", "P740")
					.contains(F01202_001) && F01014_001 <= 1000) {
				F02086_001 = "2.1% or $30";
			} else if (Arrays.asList("F018", "Z109", "P413", "RETH", "Z078", "Z125").contains(F01202_001) && F01014_001 <= 1000) {
				F02086_001 = "2.2% or $30";
			}else if (Arrays.asList("F017", "B538", "F018", "P412",  "P413",  "P804", "T296", "RETL", "T026", "RETH", "Z077", "T034","Z078").contains(F01202_001) && F01014_001 > 1000) {
				F02086_001 = "2.3% or $30";
			}else if (Arrays.asList("P738",  "P740", "Z108", "T054", "Z109",  "Z124", "T320", "Z125").contains(F01202_001) && F01014_001 > 1000 && F01014_001<=3000) {
				F02086_001 = "2.3% or $30";
			}else if (Arrays.asList("P738",  "P740", "Z108", "T054", "Z109", "Z124", "T320", "Z125").contains(F01202_001) && F01014_001 > 3000) {
				F02086_001 = "2.4% or $30";
			}
		}
	}
}
