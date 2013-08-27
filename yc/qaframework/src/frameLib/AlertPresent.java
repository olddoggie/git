package frameLib;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

//package test.aff.lib;
//
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.NotFoundException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//
///**
// * A WebDriver condition class that checks that an element is both present and
// * visible.
// */
public class AlertPresent implements ExpectedCondition<Alert> {

	public AlertPresent() {

	}
	public Alert apply(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			return alert;
		} catch (Exception e) {
			throw new NotFoundException("Alert is not displayed");
		}
		

		

	}
}
