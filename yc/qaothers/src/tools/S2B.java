package tools;

import static tools.Account.*;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

//done
public class S2B {

	protected static final List<String> misc13Pos35_36_default = Arrays.asList("79", "81", "83", "85", "87", "89", "91", "93", "95", "97", "98", "A2", "A3",
			"A6", "A8", "A9");
	protected static final List<String> misc13Pos35_36_v25_v30 = Arrays.asList("25", "30");
	protected static final List<String> misc13Pos34 = Arrays.asList("A", "B", "C", "D", "E", "F");
	protected static final List<String> misc13Pos38 = Arrays.asList("5", "6", "C");
	protected static final List<String> misc9Pos2_3 = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15");
	protected static final double defaultF01915 = 0;
	private static Logger logger = Logger.getLogger(S2B.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static void printInput(){
		logger.warn("S2B Input ------------------------- ");
		logger.warn("F30476_001:" +  F30476_001);
		logger.warn("F30477_001:" +  F30477_001);
		logger.warn("F30479_001:" +  F30479_001);
		logger.warn("F30695_001:" +  F30695_001);
	}
	public static void printOutput(){
		logger.warn("S2B Output ------------------------- ");
		logger.warn("F01915_001:" +  F01915_001);
		logger.warn("");
	}
	
	public static void process() {
		printInput();
		if (Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15").contains(F30695_001))
			F01915_001 = 35;
		else if (Arrays.asList("79", "81", "83", "85", "87", "89", "91", "93", "95", "97", "98", "A2", "A3", "A6", "A8", "A9").contains(F30477_001))
			F01915_001 = defaultF01915;
		else if (Arrays.asList("A", "B", "C", "D", "E", "F").contains(F30476_001))
			F01915_001 = 30;
		else if (Arrays.asList("25", "30").contains(F30477_001))
			F01915_001 = 30;
		else if (Arrays.asList("5", "6", "C").contains(F30479_001))
			F01915_001 = 30;
		else
			F01915_001 = defaultF01915;
		printOutput();
	}

}
