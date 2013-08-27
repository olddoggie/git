package mpd;

import static mpd.MPDShared.runAllSteps;
import static tools.Account.*;
import static tools.MPDShared.runAllSteps;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tools.Tools;


public class TestStep6 {
	private Logger logger = Logger.getLogger(TestStep5.class);

	@Test
	// match first condition
	//F01900_001:35.0
	public void test6_010(){
		logger.warn("\nTestcase: test6_010 -------");
		Account a = new Account();
		a.F01202_001 = "INS3";
		a.F01014_001 = 36;
		a.F30695_001="01";
		a.setCalucatedFields();
		runAllSteps(a);
	}
	@Test
	//match second condition
	//F01900_001:5.096965753424658
	public void test6_020(){
		logger.warn("\nTestcase: test6_020 -------");
		Account a = new Account();
		a.F01014_001 = 5;
		// this will product flag=42 in s5 and set F20034_003=30
		a.F02073_001="41PFFCAF";
		a.setCalucatedFields();
		runAllSteps(a);
	}
	
	@Test
	//match both condition
	//F01900_001:5.096965753424658
	public void test6_030(){
		logger.warn("\nTestcase: test6_020 -------");
		Account a = new Account();
		a.F01014_001 = 5;
		a.F30695_001="01";
		// this will product flag=42 in s5 and set F20034_003=30
		a.F02073_001="41PFFCAF";
		a.setCalucatedFields();
		runAllSteps(a);
	}

}
