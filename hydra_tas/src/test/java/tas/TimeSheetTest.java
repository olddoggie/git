package tas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import page.*;

public class TimeSheetTest {
    @BeforeClass
    public void setupBeforeClass() {
        // go to hydra page
        System.setProperty("webdriver.ie.driver", "webDriverServer/IEDriverServer.exe");
        BasePage.driver = new InternetExplorerDriver();
        BasePage.Go("http://intranet.bleum.com");
        BleumHome.Hydra.click();
        HydraHome.ts_link.click();
        TimeSheet.ts_lastweek.click();

        // input usrname and passwd, then log in, if using your hostPC, just Skip..
        //page.TimeSheet.usrname.sendKeys("jack.wang");
        //page.TimeSheet.passwd.sendKeys("jkjk,66666");
        //page.TimeSheet.loginbtn.click();
        // Click time sheet link and go to the main page
        //page.TimeSheet.ts_link.click();
    }

//    @DataProvider (name = "input-from-upfront")
//    public Object[][] inputs () {
//        return new Object[][] {
//                new Object[] {"jk","jk","jk","jk"}  ,new Object[] {"jk1","jk1","jk1","jk1"}
//        };
//    }

    @DataProvider ( name = "input-from-excel")
    public Object[][] getData () throws Exception {
        DoExcel doExcel = new DoExcel("TimeSheet.xlsx");
        return doExcel.ReadExcel();
    }

    @Test (dataProvider = "input-from-excel")
    public void Fill_in_TimeSheet(String s1,String s2,String s3,String s4,String s5,String mo,String tu,String we,String th,String fr){
          String day [] = { mo,tu,we,th,fr };
          TimeSheet timeSheet = new TimeSheet();
          timeSheet.InputData(BasePage.driver,s1,s2,s3,s4,s5,day );
//        timeSheet.InputData(BasePage.driver,"1FB ODC","SCM","Manage change","7249","i love yc");
//        timeSheet.InputData(BasePage.driver,"1FB ODC","SCM","Manage change","1234","i love yc");
//        timeSheet.InputData(BasePage.driver,"1FB ODC","SCM","Manage change","2131","i love yc");
     }

//    @Test (dependsOnMethods = { "Fill_in_TimeSheet" })
//    public void TAS_Submit() {
//        WebElement ts_submit = BasePage.driver.findElement(By.id("bt-submit"));
//        TimeSheet.ts_submit.click();
//    }

    @AfterClass
    public void tearDownAfterClass() {
        WebElement ts_submit = BasePage.driver.findElement(By.id("bt-submit"));
        ts_submit.click();
        System.out.println("The TimeSheet has been imported to Hydra... Thanks!");
        BasePage.driver.quit();
    }

}
