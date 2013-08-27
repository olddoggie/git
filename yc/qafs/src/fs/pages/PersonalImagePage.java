package fs.pages;
import static frameLib.PrintTestCases.print;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import frameLib.MyWebElement;

import applications.BasePage;


public class PersonalImagePage extends FsBasePage {

	//<a href="#" jQuery1346172822335="3">
	public static MyWebElement basketBallPersonalImage = myWebelement("3", "basketBallPersonalImage");	
	//public MyWebElement basketBallPersonalImage = myWebelement("xPath://a[img/@id='3']", "basketBallPersonalImage");
	//public MyWebElement basketBallPersonalImage = myWebelement("xPath://img[@title='Basketball']", "basketBallPersonalImage");
	//public MyWebElement basketBallPersonalImage = myWebelement("xPath://a[img[@id='3']]", "basketBallPersonalImage");
	
	public static MyWebElement continueButton =  myWebelement("cssSelector:button[title='Continue']", "continueButton");	
	public static MyWebElement forgotLink =  myWebelement("linkText:Forgot", "forgotLink");	
	
	public static PersonalImagePage load() {
		pageTitle = "1FBUSA Online Services Personal Image";
		basketBallPersonalImage.waitUntilElementPresent();
		print("On " + pageTitle + " page");
		return new PersonalImagePage();
	}

		
	public static void selectBasketImage() {
		basketBallPersonalImage.click();
		continueButton.submit();
	}

}
