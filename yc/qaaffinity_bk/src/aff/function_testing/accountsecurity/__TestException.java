package aff.function_testing.accountsecurity;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import aff.pages.SearchPage;

import org.testng.annotations.*;

import frameLib.listener.*;
import applications.*;

@Listeners({ MyTestCaseStatusListener.class, MyTestVerifyAssertListener.class, MyGroupListener.class })
public class __TestException extends TestAffCsrAccountSecurityBase {

	@DataProvider(name = "securityWordDp")
	public Object[][] createSecurityWordDp() {
		return new Object[][] { { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 },
				{ 1 }, { 1 } };
	}

	@Test(dataProvider = "securityWordDp")
	public void testException(int i) {
		new SearchPage(driver).searchByAcctNumber("5432270003000144");
		int a = 1 / 0;
	}

	@Test
	public void testException2() {
		new SearchPage(driver).searchByAcctNumber("5432270003000144");
		int a = 1 / 0;
	}
}
