package tools;

import static tools.Account.*;
import static tools.MPDShared.runAllSteps;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tools.Tools;

/** Set a high current balance and set other fees to 0 */
public class TestStep5 {
	
	
	

	//SN_aff_getPricingStrategyInfo to find the Minimum_Payment_Due_Calculation_Flag

	//39
	//F01207_001 > percentageOfBalnancePlusFeePlusFC--15.0:3.8
	//S7:32 - F01912_001:1%x(bal less FFC) +FFC OR $15/CU 1Stmt
	//S7:33 - F01900_001:15.0
	//@Test
	public static void test5_001(){
		F01195_001= "2"; 
		F02073_001= "108CBASE"; 
		F01014_001 = 100;
		//F01202_001= "R998";
		//used in step 7b
		//F02091_001 = 0;
		runAllSteps();
	}
	
	//1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 34, 35, 36, 37
	//S5:23 - F01921_001 * F01206_001 < F01207_001--0.84:10.0
	//@Test
	public static void test5_002(){
		F01195_001= "2"; 
		F02073_001= "P738"; 
		// current balance should always > 35 to avoid using current balance as the mpd
		F01014_001 = 36;
		runAllSteps();
	}
	
	//1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 34, 35, 36, 37
	//S5:26 - F01921_001 * F01206_001 > F01207_001--46.74:10.0
	//@Test
	public static void test5_0021(){
		F01195_001= "2"; 
		F02073_001= " P738"; 
		F01014_001 = 2000;
		runAllSteps();
	}
	
	//11,12
	//S5:26 - F01921_001 * F01206_001 < minimumDueAmtPlusFC--40.64:62.984280011259145
	//S7:32 - F01912_001:2% or $15+FC or $30
	//S7:33 - F01900_001:62.98
	//@Test
	public static void test5_003(){
		F01195_001= ""; 
		F02073_001= ""; 
		F02100_001= "PA$30"; 
		F01014_001 = 2000;
		runAllSteps();
	}
	
	//11,12
	//F01921_001 * F01206_001 < minimumDueAmtPlusFC--0.73:30.59
	//F01912_001:2% or $15+FC or $30
	//F01912_001:2% or $15+FC or $30
	//@Test
	public static void test5_0031(){
		F01195_001= ""; 
		F02073_001= ""; 
		F02100_001= "PA$30"; 
		F01014_001 = 36;
		runAllSteps();
	}
	
	//13
	//F01921_001 - F01021_001 < minimumDueamtPlusFCPlusFees---313.47:10.52
	//F01912_001:F01912_001:LS FFC+$15 Ovrd
	//F01900_001:15.0
	//@Test
	public static void test5_004(){
		F01195_001= ""; 
		F02073_001= "";
		F02100_001= ""; 
		F01014_001 = 36;
		F01202_001= "Z074"; //2074
		runAllSteps();
	}
	
	//13
	//F01921_001 - F01021_001 < minimumDueamtPlusFCPlusFees---313.47:10.52
	//F01912_001:F01912_001:LS FFC+$15 Ovrd
	//F01900_001:15.0
	//@Test
	public static void test5_0041(){
		F01195_001= ""; 
		F02073_001= "";
		F02100_001= ""; 
		F01014_001 = 2000;
		F01202_001= "B311";//2074
		runAllSteps();
	}
	
	
}
