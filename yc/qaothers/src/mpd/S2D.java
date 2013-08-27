package mpd;

import java.util.Arrays;
import org.apache.log4j.Logger;


//done
public class S2D {
	private Account a;
	private Logger logger = Logger.getLogger(S2D.class);
	
	public S2D(Account a){
		this.a = a;
	}

	public void process() {
		if (a.F02100_001.equalsIgnoreCase("TIER$30")) {

			if (Arrays.asList("a.F017", "B538", "P412", "P804", "P738", "T296", "Z108", "T054", "RETL", "T026", "Z077", "T034", "Z124", "T320", "P740")
					.contains(a.F01202_001) && a.F01014_001 <= 1000) {
				a.F02086_001 = "2.1% or $30";
			} else if (Arrays.asList("a.F018", "Z109", "P413", "RETH", "Z078", "Z125").contains(a.F01202_001) && a.F01014_001 <= 1000) {
				a.F02086_001 = "2.2% or $30";
			}else if (Arrays.asList("a.F017", "B538", "a.F018", "P412",  "P413",  "P804", "T296", "RETL", "T026", "RETH", "Z077", "T034","Z078").contains(a.F01202_001) && a.F01014_001 > 1000) {
				a.F02086_001 = "2.3% or $30";
			}else if (Arrays.asList("P738",  "P740", "Z108", "T054", "Z109",  "Z124", "T320", "Z125").contains(a.F01202_001) && a.F01014_001 > 1000 && a.F01014_001<=3000) {
				a.F02086_001 = "2.3% or $30";
			}else if (Arrays.asList("P738",  "P740", "Z108", "T054", "Z109", "Z124", "T320", "Z125").contains(a.F01202_001) && a.F01014_001 > 3000) {
				a.F02086_001 = "2.4% or $30";
			}
		}
	}
	
	public static void main(String[] args) {

	}

}
