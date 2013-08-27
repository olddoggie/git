package mpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tools.findPricingStrategyDiffFromSPS();
		//Tools.findPricingStrategyForS5(37);

	}

	// String s = " 2 OR 11 ";
	// String[] sArr = s.split("OR");
	// for(String s1 : sArr){
	// System.out.println(s1.trim());
	// }

	// boolean a = ArrayUtils.contains(new int[]{1, 2,3}, 4);
	// boolean b = ArrayUtils.contains(new String[]{"b", "b","c"}, " ");
	//
	// System.out.println(b);
	//
	// List list = Arrays.asList("b", "b","c");
	// System.out.println(list.contains(" "));
}
