package frameLib;

import applications.BaseTestSuite;

/**
 * This class is created to log information in testng report
 */
public class PrintTestCases {

	private static boolean functionPrintFlag = false;

//	public static void main(String[] args) {
//		
//
//	}
	
	public static void funcPrintOn(String s)
	{
		functionPrintFlag = true;
		System.out.println(s);
	}
	public static void funcPrintOff()
	{
		functionPrintFlag = false;

	}
	
	public static void print(String s)
	{
		if(!functionPrintFlag)
		//System.out.println(s);		
		BaseTestSuite.testInfo = BaseTestSuite.testInfo + "<pre>" +  s +  "</pre>" ;//"<br/>";

	}
	public static String charSequence2String(CharSequence... keysToSend)
	{
		StringBuilder sb = new StringBuilder();
		for(CharSequence c : keysToSend)
			sb.append(c);	
		return sb.toString();
	}	
}
