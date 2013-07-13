package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimeSheet {
    public static WebElement ts_date = BasePage.driver.findElement(By.cssSelector("span.timesheet-date"));
    public static WebElement ts_lastweek = BasePage.driver.findElement(By.cssSelector("a:contains(Previous Timesheet)"));
    public static WebElement ts_nextweek = BasePage.driver.findElement(By.cssSelector("a:contains(Next Timesheet)"));

    //public static WebElement usrname = driver.findElement(By.cssSelector("input#username"));
    //public static WebElement passwd = driver.findElement(By.cssSelector("input#password"));
    //public static WebElement loginbtn = driver.findElement(By.cssSelector("input[type='image']"));

    public void InputData(WebDriver driver,String first, String sec_1,String sec_2,
                             String third, String fourth, String[] weekday  ){
        WebElement addBtn = BasePage.driver.findElement(By.cssSelector("a#addBtn"));
        addBtn.click();
        WebElement col_1 = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td:first-child"));
        col_1.click();
        WebElement _col_1 = driver.findElement(By.id("selecta"));
        _col_1.click();
        WebElement __col_1 = driver.findElement(By.linkText("WS:"+first));
        __col_1.click();
        //_col_1.click();
        //String a = first.getText();
        //System.out.println(a);
        //_first.selectByVisibleText("WS:1FB Test");
        //driver.switchTo().frame(driver.findElement(By.cssSelector("div[type=projectList]")));
        //List<WebElement> project = driver.findElements(By.cssSelector("div[id=projectList]"));
        //Actions actions = new Actions(driver);
        //Action _col_1 = actions.click(driver.findElement(By.cssSelector("a[type=project][title=ODC:Falcon]"))).build();
        //_col_1.perform();
        WebElement col_2 = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td:nth-child(2)"));
        col_2.click();
        WebElement _col_2 = driver.findElement(By.cssSelector("h5:contains("+sec_1+") + div.activity-pool span:contains("+sec_2+")"));
        _col_2.click();
        if ( ! first.equalsIgnoreCase("Bleum")) {
            WebElement col_3 = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td:nth-child(3)"));
            col_3.click();
            WebElement _col_3 = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td[type=wo] span"));
            _col_3.sendKeys(third);
        }
        WebElement col_4 = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td:nth-child(4)"));
        col_4.click();
        WebElement _col_4 = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td[type=comment] span"));
        _col_4.sendKeys(fourth);
        for (int index = 1; index < 6; index++){
            WebElement timeslot = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td[type=timeslot][index="+index+"]"));
            timeslot.click();
            WebElement _timeslot = driver.findElement(By.cssSelector("tbody[id=timeLogBody] tr:last-child td[type=timeslot][index="+index+"] span"));
            _timeslot.sendKeys(weekday[index-1]);
        }
    }

    public void MultiClick (int num,WebElement element) {
        for( int i = 0; i< num; i++ ){
            element.click();
        }
    }

}
