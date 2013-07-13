package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BleumHome {
   public static WebElement Hydra = BasePage.driver.findElement(By.cssSelector("li.main_nav span:contains(Hydra)"));
}
