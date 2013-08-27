package aff.function_testing.accountsecurity;


import org.testng.annotations.*;

import frameLib.listener.*;

import applications.*;
import static frameLib.MyVerifyAssert.*;
@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })
public class __TestClass {
	@Test(priority = 99060)
	public void t1() {
		verifyMatches("ccccc", "aa","bb");
		verifyEquals("aa","bb");
		System.out.println("t1");
	}

	@Test(groups ={"uc1050_020"})
	public void t2() {
		verifyEquals("cc","dd");
	}

	@Test(priority = -1)
	public void t3() {
		System.out.println("t3");
	}

	@Test(groups ={"uc1050_010"})
	public void t4() {
		System.out.println("t4");
	}

	@Test(groups ={"uc1050_030"})
	public void t6() {
		System.out.println("t6");
	}

	@Test(priority = 99030)
	public void t5() {
		System.out.println("t5");
	}
}
