package mpd;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

//done
public class S2B {
	private Account a;
	private Logger logger = Logger.getLogger(S2B.class);
	private static final List<String> misc13Pos35_36_default = Arrays.asList("79", "81", "83", "85", "87", "89", "91", "93", "95", "97", "98", "A2", "A3",
			"A6", "A8", "A9");
	private static final List<String> misc13Pos35_36_v25_v30 = Arrays.asList("25", "30");
	private static final List<String> misc13Pos34 = Arrays.asList("A", "B", "C", "D", "E", "F");
	private static final List<String> misc13Pos38 = Arrays.asList("5", "6", "C");
	private static final List<String> misc9Pos2_3 = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15");
	private static final double defaultF01915 = 0;

	public S2B(Account a){
		this.a = a;
	}
	
	public void printInput(){
		logger.debug("S2B Input ------------------------- ");
		logger.debug("F30476_001:" +  a.F30476_001);
		logger.debug("F30477_001:" +  a.F30477_001);
		logger.debug("F30479_001:" +  a.F30479_001);
		logger.debug("F30695_001:" +  a.F30695_001);
	}
	public void printOutput(){
		logger.debug("S2B Output ------------------------- ");
		logger.debug("F01915_001:" +  a.F01915_001);
		logger.debug("");
	}
	
	public void process() {
		printInput();
		if (Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15").contains(a.F30695_001))
			a.F01915_001 = 35;
		else if (Arrays.asList("79", "81", "83", "85", "87", "89", "91", "93", "95", "97", "98", "A2", "A3", "A6", "A8", "A9").contains(a.F30477_001))
			a.F01915_001 = defaultF01915;
		else if (Arrays.asList("A", "B", "C", "D", "E", "F").contains(a.F30476_001))
			a.F01915_001 = 30;
		else if (Arrays.asList("25", "30").contains(a.F30477_001))
			a.F01915_001 = 30;
		else if (Arrays.asList("5", "6", "C").contains(a.F30479_001))
			a.F01915_001 = 30;
		else
			a.F01915_001 = defaultF01915;
		printOutput();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
