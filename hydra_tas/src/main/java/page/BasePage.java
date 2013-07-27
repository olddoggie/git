package page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Date;
import static junit.framework.Assert.fail;

public class BasePage extends DoExcel {
    public static WebDriver driver;
    public static boolean acceptNextAlert = true;
    public static StringBuffer verificationErrors = new StringBuffer();

    public BasePage(String fileName) {
        super(fileName);
    }

    public static void Go(String URL) {
        driver.get(URL);
        driver.manage().window().maximize();
    }

    public static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    
    public static boolean isPageTitlePresent(String title) {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, 3);
            return wait.until(ExpectedConditions.titleContains(title));
        } catch (Exception e) {
            return false;
        }
    }

    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public static String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
