package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class HydraLogin {		
	  public static String Pfile;
	  public static String Uname;
	  public static String Passwd;
      
	  public HydraLogin(String Pfile){
    	  this.Pfile = Pfile;
      }
      public void Login() {
          	String FilePath = System.getProperty("user.dir") + "\\"+ Pfile ;
            try {
            	BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(FilePath));
            	ResourceBundle rb = new PropertyResourceBundle(inputStream);
            	Uname = rb.getString("username");
            	Passwd = rb.getString("passwd");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // input parameters from properties file and log in...
           	WebElement usrname =  BasePage.driver.findElement(By.cssSelector("input#username"));
            WebElement passwd = BasePage.driver.findElement(By.cssSelector("input#password"));
            WebElement loginbtn = BasePage.driver.findElement(By.cssSelector("input[type='image']"));
            usrname.sendKeys(Uname);
            passwd.sendKeys(Passwd);
            loginbtn.click();
      }
}
