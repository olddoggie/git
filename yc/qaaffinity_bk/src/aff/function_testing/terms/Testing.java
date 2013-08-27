package aff.function_testing.terms;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Testing {

	public static void main(String[] args) throws Exception {
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenShot, "JPG", new File("screenShot.jpg"));
	}
	// @Test
	// public void myTest() throws Exception {
	// WebDriver driver = new InternetExplorerDriver();
	//
	// driver.get("http://www.google.com");
	//
	// // RemoteWebDriver does not implement the TakesScreenshot class
	// // if the driver does have the Capabilities to take a screenshot
	// // then Augmenter will add the TakesScreenshot methods to the instance
	// // WebDriver augmentedDriver = new Augmenter().augment(driver);
	// File screenshot = ((TakesScreenshot)driver).
	// getScreenshotAs(OutputType.FILE);
	// // File screenshot = ((TakesScreenshot)augmentedDriver).
	// // getScreenshotAs(OutputType.FILE);
	// }
}
