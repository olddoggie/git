package page;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public static WebDriver driver;

    public static void Go(String URL) {
        driver.get(URL);
        driver.manage().window().maximize();
    }
}
