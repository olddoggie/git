package aff.pages;
import static frameLib.PrintTestCases.print;
import org.openqa.selenium.WebDriver;
import frameLib.MyWebElement;
import applications.BasePage;


public class LandingPage extends AffinityBasePage {

	public static MyWebElement ntLoginEditBox =  myWebelement("name:ntLogin", "ntLoginEditBox");
	public static MyWebElement submitButton =  myWebelement("cssSelector:input[type='submit']", "submitButton");	

	
	public static LandingPage load() {
		pageTitle = "Affinity Landing Page";
		//pageTitle = appConfigures.getProperty("aff_landingPage_title");		
		//verifyPageTitle(pageTitle);
		//pageTitle = driver.getTitle().replace("Affinity - ", "");
		print("On " + pageTitle + " page");
		return new LandingPage();
	}

		
	public static void submitForm() {
		submitButton.waitUntilElementFound();
		ntLoginEditBox.clearSet("ad\\jyu");
		submitButton.submit();
	}

}
