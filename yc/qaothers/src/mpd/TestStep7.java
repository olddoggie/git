package mpd;

import static mpd.MPDShared.runAllSteps;
import static tools.Account.*;
import static tools.MPDShared.runAllSteps;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tools.Tools;


public class TestStep7 {
	private Logger logger = Logger.getLogger(TestStep7.class);

	@Test
	//default low balance -- F01912_001:LS FFC+$15 Ovrd
	//set in 7A
	public void test7A_010(){
		logger.warn("\nTestcase: test7_010 -------");
		Account a = new Account();
//		a.F01202_001 = "INS3";
		a.F01014_001 = 36;
//		a.F30695_001="01";
		a.setCalucatedFields();
		runAllSteps(a);
	}
	
	
	@Test	
	//F20034:003=35 - 2b;
	//F01900_001:35.0
	public void test7A_020(){
		logger.warn("\nTestcase: test7_020 -------");
		Account a = new Account();
//		a.F01202_001 = "INS3";
		a.F01014_001 = 36;
		a.F30695_001="01";
		a.setCalucatedFields();
		runAllSteps(a);
	}

		
	@Test
	//F20034:003=10 --- 3c
	//F01900_001:15.0
	public void test7A_030(){
		logger.warn("\nTestcase: test7_030 -------");
		Account a = new Account();
		a.F02073_001 = "TRAD";
		a.F02100_001 = "2.2M10";		
		a.F01014_001 = 36;
//		a.F30695_001="01";
		a.setCalucatedFields();
		runAllSteps(a);
	}
	
	
	@Test	
	//F20034:003=35;
	//F01900_001:29.56
	public void test7C_040(){
		logger.warn("\nTestcase: test7_040 -------");
		Account a = new Account();
		a.F02073_001 = "CATCH$30";
		a.F01014_001 = 36;
		a.setCalucatedFields();
		runAllSteps(a);
	}
	
	
	@Test	
	//F20034:003=35;
	public void test7c_010(){
		logger.warn("\nTestcase: test7_020 -------");
		Account a = new Account();
		a.F02073_001 = "CATCH$30";
		a.F01014_001 = 29;
		a.setCalucatedFields();
		runAllSteps(a);
	}
}
