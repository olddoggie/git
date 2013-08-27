package tools;

import static tools.Account.*;
import static tools.MPDShared.*;
// need update for gap analysis $/Affinity/AF 3.58 (FMPD GAP)/Requirements/Change Requests
public class Test_MPD_All {

	public static void main(String[] args) {
//		System.out.println(F01016_001);
//		System.out.println(F01003);
		test3C_002();
	}
	
	
	
	/**s_GetUserData_191*/
	public static void testAll_10001(){
		//s2b
		F30476_001= "A"; 
		F01014_001 = 100;
		F02091_001 = 0;
		runAllSteps();

	}
	
	

	public static void testAll_10002(){
		//s2b
		F30695_001= "01"; 
		F01014_001 = 100;
		F02091_001 = 0;
		runAllSteps();
	}
	
	
	public static void testAll_10003(){
		//s2b
		F30477_001= "25"; 
		F01014_001 = 31;
		F02091_001 = 0;
		runAllSteps();
	}
	
	
	public static void test3C_001(){
		F01195_001= "2"; 
		F02073_001= "108CBASE"; 
		//F01014_001 = 100;
		//used in step 7b
		F02091_001 = 0;
		runAllSteps();
	}
	
	public static void test3C_002(){
		//F02073_001= "MPDPN108"; 
		F02073_001= "5100APNL"; 
		
		F01014_001 = 2000;
		F02091_001 = 0;
		runAllSteps();
	}
	
	
	
	/**s_GetUserData_191*/
	public static void testAll_191(){
		//s2a
		F02073_001= "108CBASE"; 
		F01195_001 = "2";
		runAllSteps();

	}
	
	/**s_GetUserData_130*/
	public static void testAll_130(){
		//s2a
		F02073_001= "MPDBS108"; 
		F01014_001 = 2750;
		runAllSteps();

	}
	
	

}
