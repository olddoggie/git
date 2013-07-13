package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HydraHome {
    public static WebElement ts_link = BasePage.driver.findElement(By.cssSelector("a.timesheet"));
}
