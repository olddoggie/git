package fs.scenarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.*;

import static myconstant.AccountDefaultValues.*;


import applications.BasePage;
import static frameLib.FrameUtilities.*;
import static frameLib.MyVerifyAssert.*;
import static fs.FsConstants.*;
import fs.BaseTestSuiteFs;
import fs.pages.*;

import utilityLib.Tools;
public class TestJun extends BaseTestSuiteFs{

	@BeforeMethod(alwaysRun = true)
	private void setUpBeforeMethod() throws Exception {
		
	}
	
	@AfterMethod(alwaysRun = true)
	private void setUpAfterMethod() throws Exception {
		
	}
	
	@Test()
	public void test(){		
		System.out.println("test111");
	}

}
