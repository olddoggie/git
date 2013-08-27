package rtbLib;

import java.text.DecimalFormat;
import junit.framework.*;

public class MyAssertArrays extends TestCase {

	public final static String NOTCOMPARE = "";

	public static void assertArrayEquals(String[] expResult,
			String[] actResult, String myTestcase, String myScenario,
			String decimalPatteren) {
		String expStr = "";
		String actStr = "";
		// DecimalFormat fourDForm = new DecimalFormat("#.####");
		DecimalFormat myForm = new DecimalFormat(decimalPatteren);
		for (int i = 0; i < expResult.length; i++) {
			if (isNum(expResult[i].trim())) {
				expResult[i] = myForm.format(Double.parseDouble((expResult[i]
						.trim())));
			} else {
				expResult[i] = expResult[i].trim();
				if (expResult[i] == NOTCOMPARE) {
					actResult[i] = NOTCOMPARE;
				}
			}
			expStr = expStr + expResult[i] + "###";
		}

		for (int i = 0; i < actResult.length; i++) {
			if (isNum(actResult[i].trim())) {
				actResult[i] = myForm.format(Double.parseDouble((actResult[i]
						.trim())));
			} else {
				actResult[i] = actResult[i].trim();
			}
			actStr = actStr + actResult[i] + "###";
		}

		assertEquals(myTestcase + "---" + myScenario + "\n\r expectResult:"
				+ expStr + "\n\r actualResult:" + actStr + "\n\r", expStr,
				actStr);

	}

	public static boolean isNum(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/*
	 * public static void main(String[] args) { String[] a = { " 111 ",
	 * " bbb 1. 1", "10.5   " }; String[] b = { " 111.0001 ", " bbb 1. 1",
	 * " 10.5003   " }; assertArrayEquals(a, b, "test_neg", "aaa");
	 * System.out.println(Arrays.toString(a));
	 * 
	 * }
	 */

}
