package tas;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import page.*;

import static page.BleumHome.*;

public class TimeSheetTest extends BasePage {
    public static String TimeSheetExcel = "TimeSheet.xlsx";
    public static String TimeSheetProperties = "TimeSheet.properties";

    public TimeSheetTest(String fileName) {
        super(fileName);
    }

    @BeforeClass
    public void setupBeforeClass() throws Exception {
        // go to hydra page
        System.setProperty("webdriver.ie.driver", "webDriverServer/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        Go("http://intranet.bleum.com");
        BleumHome.Hydra.click();

        if ( isElementPresent(By.cssSelector("input#username")) ) {
        	HydraLogin hydralogin = new HydraLogin(TimeSheetProperties);
            hydralogin.Login();
        }
        if( isPageTitlePresent("Hydra ODC:") ) {
        	 HydraHome.ts_link.click();
        }  else {
        	throw new Exception("TimeSheet home page is NOT loaded correctly!");
        }
        if( isPageTitlePresent("Me View :") ) {
            TimeSheetTest myTimeSheetTest = new TimeSheetTest(TimeSheetExcel);
            myTimeSheetTest.ReadExcel();
            if (whichWeek.equalsIgnoreCase("Last Week")) {
            	TimeSheet.ts_lastweek.click();
            	System.out.println("Starting fill in TAS for last week...");
            } else if (whichWeek.equalsIgnoreCase("Next Week")){
            	TimeSheet.ts_nextweek.click();
            	System.out.println("Starting fill in TAS for next week...");
            } else {
            	System.out.println("Starting fill in TAS for this week...");
            }
// Java 1.6 does NOT support switch a string, so change method to if else like above           
//            switch(whichWeek)  {
//                case "Last Week":
//                    TimeSheet.ts_lastweek.click();
//                    break;
//                case "Next Week":
//                    TimeSheet.ts_nextweek.click();
//                    break;
//                case "This Week":
//                default:
//                    break;
//            }
            // expand the drop down list of 1st column, then selenium can select "Bleum" successfully
            String JS ="  $j('#costcenterList').css('max-height', '800px');"  ;
            ((JavascriptExecutor)driver).executeScript(JS);
        }  else {
            throw new Exception("TimeSheet TAS page is expired or quit abnormally!");
        }
    }

//    @DataProvider (name = "input-from-upfront")
//    public Object[][] inputs () {
//        return new Object[][] {
//                new Object[] {"jk","jk","jk","jk"}  ,new Object[] {"jk1","jk1","jk1","jk1"}
//        };
//    }

    @DataProvider ( name = "input-from-excel")
    public Object[][] getData () throws Exception {
        TimeSheetTest myTimeSheetTest = new TimeSheetTest(TimeSheetExcel);
        return myTimeSheetTest.ReadExcel();
    }

    @Test (dataProvider = "input-from-excel")
    public void Fill_in_TimeSheet(String s1,String s2,String s3,String s4,String s5,String mo,String tu,String we,String th,String fr) {
            String day [] = { mo,tu,we,th,fr };
            TimeSheet timeSheet = new TimeSheet();
            timeSheet.InputData(driver,s1,s2,s3,s4,s5,day );
            //timeSheet.InputData(driver,"1FB ODC","SCM","Manage change","7249","i love yc");
            //timeSheet.InputData(driver,"1FB ODC","SCM","Manage change","1234","i love yc");
            //timeSheet.InputData(driver,"1FB ODC","SCM","Manage change","2131","i love yc");
     }

//    @Test (dependsOnMethods = { "Fill_in_TimeSheet" })
//    public void TAS_Submit() {
//        WebElement ts_submit = driver.findElement(By.id("bt-submit"));
//        TimeSheet.ts_submit.click();
//    }

    @AfterClass
    public void tearDownAfterClass() throws Exception {
        if ( isElementPresent(By.id("bt-submit")) ) {
            driver.findElement(By.id("bt-submit")).click();
        } else {
            throw new Exception("TimeSheet TAS page is expired or quit abnormally!");
        }
// Error:
// org.openqa.selenium.StaleElementReferenceException:
// Element is no longer valid (WARNING: The server did not provide any stacktrace information)
//  Cannot use below  method to call TimeSheet.java, but to just find element here then click it like above implementation
//        if( isPageTitlePresent("Me View :") ) {
//       	 TimeSheet.ts_submitlink.click();
//       }  else {
//       	throw new Exception("TimeSheet TAS page is expired or quit abnormally!");
//       }
    	System.out.println("The TimeSheet has been imported to Hydra... Thanks!");
        driver.quit();
    }
}
