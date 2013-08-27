package frameLib;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static applications.BasePage.driver;
import static frameLib.PrintTestCases.charSequence2String;
import static frameLib.PrintTestCases.print;

public class MyWebElement implements WebElement {
	private String locator;
	private String[] subLocator;
	private int index = -1;
	private int[] sub_index;
	private final int WEB_ELEMENT_WAIT_TIME = 10;    // Jun set 60
	private final int IMPLICITLY_WAIT_TIME = 5;      // Jun set 30
	private WebDriverWait wait;
	// private WebDriverWait wait=new WebDriverWait(driver,
	// WEB_ELEMENT_WAIT_TIME);;
	private String elementName = "defaultElement";
	private WebElement webElement;
	private static Logger  qaLog = Logger.getLogger(MyWebElement.class);
	
	
	public MyWebElement(String locator) {
//		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
//		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		this.locator = locator;
	}

	// public MyWebElement(WebDriver driver, String locator, String elementName)
	// {
	// this(driver, locator);
	// this.elementName = elementName;
	// }

	public MyWebElement(String locator, String elementName,
			String... subLocator) {
		this(locator);
		this.subLocator = subLocator;
		this.elementName = elementName;
	}

	// index is zero based
	public MyWebElement(String locator, String elementName, int index) {
		this(locator);
		this.index = index;
		this.elementName = elementName;
	}

    // index is zero based
    public MyWebElement(WebElement webElement) {
         this.webElement = webElement;
         locator = "WebElement" ;
         elementName = webElement.getText();
    }

    // local sub-element of MyWebelement object
    public MyWebElement(String locator, String elementName,int index, String... subLocator) {
        this(locator);
        this.subLocator = subLocator;
        this.index = index;
        this.elementName = elementName;
    }

    // local sub-element of MyWebelement object by sub-index, useful in table item list
    public MyWebElement(String locator, String elementName, int index, String[] subLocator, int[] sub_index) {
        this(locator);
        this.index = index;
        this.elementName = elementName;
        this.subLocator = subLocator;
        this.sub_index = sub_index;
    }

	// public MyWebElement getChildElement(String subLocator){
	// webElement =
	// driver.findElement(MyBy.locator(locator)).findElement(MyBy.locator(subLocator));
	// return new MyWebElement(driver,subLocator);
	// }

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	// public void setElement(WebElement element) {
	// this.webElement = element;
	// }

	public String getLocator() {
		return locator;
	}

    private WebElement getWebElementByJquery(int index) {
        WebElement we_Jquery;
        String[] lArr = locator.split(":");
        String by = lArr[0];
        //String using = lArr[1];
        String using =locator.substring(by.length()+1);
        index = (index>=0) ? index:0;
        we_Jquery = (WebElement)((JavascriptExecutor) driver).executeScript("return jQuery(\""+using+"\").get("+index+")");
        return we_Jquery;
    }

    public WebElement getWebElement() {
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		//this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		WebElement we;
        if (locator.contains("WebElement")) {
            we = webElement;
        }   else {
            if (locator.contains("jQuerySelector"))  {
                we = getWebElementByJquery(index);
            }  else {
                if (index >= 0) {
                    // the index is zero based
                    we = driver.findElements(MyBy.locator(locator)).get(index);
                } else {
                    we = driver.findElement(MyBy.locator(locator));
                }
                    //if (subLocator.length > 0) {
                    if (subLocator!=null) {
                        if ( sub_index==null) {
                            for (String sub : subLocator) {
                                we = we.findElement(MyBy.locator(sub));
                            }
                        } else {
                            for (int k = 0; k < subLocator.length; k++ ) {
                                we = we.findElements(MyBy.locator(subLocator[k])).get(sub_index[k]);
                            }
                        }
                    }
                }
            }
		return we;
	}

    public String getElementName() {
		if (elementName.equalsIgnoreCase("defaultElement")) {
			try {
				if (!(getAttribute("id").isEmpty()))
					elementName = getAttribute("id");
				else if (!(getAttribute("name").isEmpty()))
					elementName = getAttribute("name");
				return elementName;
			} catch (Exception e) {
				return elementName;
			}
		} else {
			return elementName;
		}
	}

	public void click() {
		//webElement = getWebElement();
		// System.out.println(webElement.getAttribute("disabled"));
		// System.out.println(getWebElement().getAttribute("disabled"));
		// System.out.println(driver.findElement(MyBy.locator(locator)).getAttribute("disabled"));
		print(" Click " + getElementName());
		getWebElement().click();
	}

	// this is an experientment function if waitUntilElementEnbled doesn't work
//	private void safeClick() {
//		
//	}

	public void check() {
		int tryNumber = 0;
		//webElement = getWebElement();
		print(" Click " + getElementName());

		while (!getWebElement().isSelected() && tryNumber < 3) {
			getWebElement().click();
			tryNumber++;
		}
		// if (!getWebElement().isSelected()) {
		// getWebElement().click();
		// }
	}

	public void uncheck() {
		int tryNumber = 0;
		//webElement = getWebElement();
		print(" Uncheck " + getElementName());
		while (getWebElement().isSelected() && tryNumber < 3) {
			getWebElement().click();
			tryNumber++;
		}
		// if (webElement.isSelected()) {
		// webElement.click();
		// }

	}

	public boolean isSelected() {
		//webElement = getWebElement();
		return getWebElement().isSelected();
	}

	public void clearSet(String s) {
		webElement = getWebElement();
		print(" Type " + s + " in " + getElementName());
		webElement.clear();
		webElement.sendKeys(s);
	}

	public String getText() {
		//webElement = getWebElement();
		return getAttribute("innerHTML").replaceAll("\\<.*?>","").replaceAll("&amp;","&");
		//return webElement.getText();
	}

	public String getTextNoNewLine() {
		//webElement = getWebElement();
		return getText().replaceAll("\\n", "");
	}

	public void submit() {
		//webElement = getWebElement();
		print(" Submit " + getElementName());
		getWebElement().submit();
	}

	public WebElement findElement(By by) {
		return getWebElement().findElement(by);
	}

	public WebElement findElement(By by, String regEx) {
		List<WebElement> webElements = findElements(by);
		for (WebElement webElement : webElements) {

			if (webElement.getText().matches(regEx)) {
				return webElement;
			}
		}

		throw new NotFoundException("Element is not selected");
	}

	public List<WebElement> findElements(By by) {
		return getWebElement().findElements(by);
	}

	public Dimension getSize() {
		return getWebElement().getSize();
	}

	public void clear() {
		getWebElement().clear();
	}

	// this elapsetim is to prevent stale webelement, 
	//happen when you find a WebElement, and then the DOM changes, and then you try to do something to that WebElement, 
	public String getAttribute(String name) {
		String attribute="";
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
		while (elapsedTime < 30*1000) {
		    try{
		    	attribute = getWebElement().getAttribute(name);
		    	break;
		    }catch (StaleElementReferenceException e){
		    	print("stale attribute found. Name is:" + name);
		    	//System.out.println("find staled element");
		    }
		    elapsedTime = System.currentTimeMillis() - startTime;
		}
		return attribute;
	}

	public String getCssValue(String propertyName) {
		return getWebElement().getCssValue(propertyName);
	}

	public Point getLocation() {
		return getWebElement().getLocation();
	}

	public String getTagName() {
		return getWebElement().getTagName();
	}

	public boolean isDisplayed() {
		return getWebElement().isDisplayed();
	}

	public boolean isEnabled() {
		return getWebElement().isEnabled();
	}

	public void sendKeys(CharSequence... keysToSend) {
		//webElement = getWebElement();
		print(" Type " + charSequence2String(keysToSend) + " in "
				+ getElementName());
		getWebElement().sendKeys(keysToSend);
	}

	// public void verifyMatches(String expected,boolean... noNewLine) {
	// String elementName = getElementName();
	// String actual = getTextNoNewLine() ;
	// if(noNewLine.length>0)
	// {
	// actual = this.getText();
	// }
	// TestBase.verifyMatches("\nVerify " + elementName + " matches:" +
	// "\nexpected:" + expected + "\n" + "actual:" + actual + "\n", actual,
	// expected);
	// }

    public void input(CharSequence... keysToSend){

        webElement = getWebElement();
        print(" Type " + charSequence2String(keysToSend) + " in " + getElementName());
        webElement.sendKeys(keysToSend);
        }

	public void verifyAttributeContains(String attribute, String... expectedArr) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = getAttribute(attribute);
		String actualReplace = actual.replaceAll("\r|\n", "");
		String expected = "";
		for (String s : expectedArr) {
			expected = expected + s;
			MyVerifyAssert.verifyContains(driver, "<br/>Verify " + elementName
					+ " contains:" + "<br/>expected:" + expected
					+ "<br/>actual:" + actual, actualReplace, expected);
			expected = "";
		}
	}
	
	
	public void verifyMatches(String expected) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = getTextNoNewLine();
		MyVerifyAssert.verifyMatches(driver, "\nVerify " + elementName
				+ " matches:" + "\nexpected:" + expected + "\n" + "actual:"
				+ actual + "\n", actual, expected);
	}

	public void verifyMatchesByLowerCase(String expected) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = getTextNoNewLine().toLowerCase();
		MyVerifyAssert.verifyMatches(driver, "\nVerify " + elementName
				+ " matches:" + "\nexpected:" + expected + "\n" + "actual:"
				+ actual + "\n", actual, expected);
	}

	public void verifyContains(String... expectedArr) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = getText();
		if (actual.length() == 0 && webElement.getAttribute("value") != null) {
			actual = webElement.getAttribute("value");
		}
		String actualReplace = actual.replaceAll("\r|\n", " ");
		String expected = "";
		for (String s : expectedArr) {
			expected = expected + s;
			MyVerifyAssert.verifyContains(" Verify " + elementName + " contains: "
                    + "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: "  + actualReplace
                    , actualReplace, expected);
         	expected = "";
		}
	}

    // this method is used to compare expected value with element's attribute value, if no attribute specified, then compare the getText();
    public void verifyContainsAttribute(String expected, String... attributeNames) {
        //webElement = getWebElement();
        String elementName = getElementName();
        String actual = "";
        if (attributeNames.length == 0) {
            actual = getText().replaceAll("\r|\n", " ");
        } else {
            for (String s : attributeNames) {
                actual = actual + getAttribute(s);
            }
        }
        MyVerifyAssert.verifyContains(" Verify " + elementName
                + " equals: " + "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: "
                + actual, actual, expected);
    }

	public void verifyNotContains(String... expectedArr) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = getText();
		if (actual.length() == 0 && getAttribute("value") != null) {
			actual = getAttribute("value");
		}
		String actualReplace = actual.replaceAll("\r|\n", " ");
		String expected = "";
		for (String s : expectedArr){
			expected = expected + s;
            MyVerifyAssert.verifyNotContains(" Verify " + elementName + " NOT contains: "
                    + "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: "  + actualReplace
                    , actualReplace, expected);
            expected = "";
		}
	}

	public void verifyContainsIgnoreSpaces(String... expectedArr) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = getText();
		if (actual.length() == 0 && getAttribute("value") != null) {
			actual = getAttribute("value");
		}
		String actualReplace = actual.replaceAll("\r|\n|\\s+", "");
		String expected = "";
		for (String s : expectedArr)
	    expected = expected + s;
		String expectedReplace = expected.replaceAll("\\s+", "");
		MyVerifyAssert.verifyContains(" Verify " + elementName + " contains(ignore spaces): "
				+ "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: " + actual.replaceAll("\r|\n|\\s+", " ")
				, actualReplace, expectedReplace);
	}

	public void verifyNotContainsIgnoreSpaces(String... expectedArr) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = getText();
		if (actual.length() == 0 && getAttribute("value") != null) {
			actual = getAttribute("value");
		}
		String actualReplace = actual.replaceAll("\r|\n|\\s+", "");
		String expected = "";
		for (String s : expectedArr)
			expected = expected + s;
		String expectedReplace = expected.replaceAll("\\s+", "");
		MyVerifyAssert.verifyNotContains(" Verify " + elementName + " NOT contains(ignore spaces): "
                + "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: " + actual.replaceAll("\r|\n|\\s+", " ")
                , actualReplace, expectedReplace);
	}

	// public void verifyEquals(String expected) {
	// String elementName = getElementName();
	// String actual = webElement().getText();
	// TestBase.verifyEquals("\nVerify " + elementName + "equals:" +
	// "\nexpected:" + expected + "\n" + "actual:" + actual + "\n", actual,
	// expected);
	// }

	public void verifySelected(boolean expected) {
		//webElement = getWebElement();
		String elementName = getElementName();
		boolean actual = isSelected();
		MyVerifyAssert.verifyEquals(driver, "\nVerify " + elementName
				+ " isSelected:" + "\nexpected:" + expected + "\n" + "actual:"
				+ actual + "\n", actual, expected);
	}

	public void verifyEnabled(boolean expected) {
		//webElement = getWebElement();
		String elementName = getElementName();
		boolean actual = isEnabled();
		MyVerifyAssert.verifyEquals(driver, "\nVerify " + elementName
				+ " isEnabled:" + "\nexpected:" + expected + "\n" + "actual:"
				+ actual + "\n", actual, expected);
	}

	public void verifyDisplayed(boolean expected) {
		webElement = driver.findElement(MyBy.locator(locator));
		String elementName = getElementName();
		boolean actual = webElement.isDisplayed();
		MyVerifyAssert.verifyEquals(driver, "\nVerify " + elementName
				+ " isDisplayed:" + "\nexpected:" + expected + "\n" + "actual:"
				+ actual + "\n", actual, expected);
	}

	public void verifyEquals(String expected, String... attributeNames) {
		//webElement = getWebElement();
		String elementName = getElementName();
		String actual = "";
		if (attributeNames.length == 0) {
			actual = getText().replaceAll("\r|\n", " ");
		} else {
			for (String s : attributeNames) {
				actual = actual + getAttribute(s);
			}
		}

		MyVerifyAssert.verifyEquals(" Verify " + elementName
				+ " equals: " + "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: "
				+ actual, actual, expected);
	}

	public void selectByVisibleText(String text) {
		webElement = getWebElement();
		print(" Select " + text + " in dropdown " + getElementName());
		new Select(webElement).selectByVisibleText(text);
	}

	public void deselectByVisibleText(String text) {
		webElement = getWebElement();
		print(" Deselect " + text + " in dropdown " + getElementName());
		new Select(webElement).deselectByVisibleText(text);
	}

	public void selectByIndex(int index) {
		webElement = getWebElement();
		new Select(webElement).selectByIndex(index);
		print(" Select index" + index + " in dropdown " + getElementName());
	}

	public void deselectByIndex(int index) {
		webElement = getWebElement();
		print(" Deselect index" + index + " in dropdown " + getElementName());
		new Select(webElement).deselectByIndex(index);
	}

	public void deselectAll() {
		webElement = getWebElement();
		print(" Deselect all" + " in dropdown " + getElementName());
		new Select(webElement).deselectAll();

	}

	public WebElement getFirstSelectedOption() {
		webElement = getWebElement();
		print(" Get first selected option" + " in dropdown " + getElementName());
		return new Select(webElement).getFirstSelectedOption();
	}

	public List<WebElement> getAllSelectedOptions() {
		webElement = getWebElement();
		print(" Get all selected option" + " in dropdown " + getElementName());
		return new Select(webElement).getAllSelectedOptions();
	}

    public List<WebElement> getOptions() {
        webElement = getWebElement();
        return new Select(webElement).getOptions();
    }

    public void verifySelectDropDownListSize(int expected) {
        //webElement = getWebElement();
        String elementName = getElementName();
        int actual = this.getOptions().size();
        MyVerifyAssert.verifyEquals("Verify the total number in the select dropdown list: " + elementName
                + " equals to:" + "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: "
                + actual, actual, expected);
    }

    public void verifyFirstSelectedOption(String expected) {
        //webElement = getWebElement();
        String elementName = getElementName();
        String actual = this.getFirstSelectedOption().getText();
        MyVerifyAssert.verifyEquals("Verify First selected element options in Select box: " + elementName
                + " equals:" + "<br/>&nbsp; -----expected---: " + expected + "<br/>&nbsp; -----actual---: "
                + actual, actual, expected);
    }

    public void verifyAllSelectedOptions(String... expected) {
        webElement = getWebElement();
        String elementName = getElementName();
        List<String> exp_sel_options = Arrays.asList(expected);
        List<String> act_sel_options = new ArrayList<String>();
        for(WebElement option : this.getAllSelectedOptions())
            act_sel_options.add(option.getText());
        //Verify expected array for selected options match with actual
        //options selected
        MyVerifyAssert.verifyArrayEquals(" Verify All selected element options in Select box: " + elementName
                + " equals: " + "<br/>&nbsp; -----expected---: " + exp_sel_options.toArray() + "<br/>&nbsp; -----actual---: "
                + act_sel_options.toArray(), act_sel_options.toArray(), exp_sel_options.toArray() );
    }

    public void verifyAllOptions(String[] expected) {
        webElement = getWebElement();
        String elementName = getElementName();
        List<String> exp_sel_options = Arrays.asList(expected);
        List<String> act_sel_options = new ArrayList<String>();
        for(WebElement option : this.getOptions())
            act_sel_options.add(option.getText());
        //Verify expected array for selected options match with actual options selected
        MyVerifyAssert.verifyArrayEquals(" Verify All visible element options in Select box: " + elementName
                + " equals: " + "<br/>&nbsp; -----expected---: " + exp_sel_options.toString() + "<br/>&nbsp; -----actual---: "
                + act_sel_options.toString(), act_sel_options.toArray(), exp_sel_options.toArray() );
    }

	public MyWebElement waitUntilElementFound() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
                    return getWebElement();
                }
                catch (NoSuchElementException e){
                    throw new NoSuchElementException("Element is not found");
                }
			}
		});
		this.setElementName(getElementName());
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

	public MyWebElement waitUntilElementPresent() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = getWebElement();
				if ( element == null || !element.isDisplayed()) {
                    // add element == null into judgement, because once login submit, signOut Link will be 'null' at first
                    // so that the element.isDisplayed in waitUntilElementPresent method will always throw java.null.lang.exception which cause cases wrong
                    // another workaround is just to use waitUntilElementFound method, which works well every time..
					throw new NotFoundException("Element is not displayed");
				}
				return element;
			}
		});
		this.setElementName(getElementName());
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

    public MyWebElement waitUntilElementPresentByTime(int t) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(driver, t);
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = getWebElement();
                if ( element == null || !element.isDisplayed()) {
                    // add element == null into judgement, because once login submit, signOut Link will be 'null' at first
                    // so that the element.isDisplayed in waitUntilElementPresent method will always throw java.null.lang.exception which cause cases wrong
                    // another workaround is just to use waitUntilElementFound method, which works well every time..
                    throw new NotFoundException("Element is not displayed");
                }
                return element;
            }
        });
        this.setElementName(getElementName());
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
        return this;
    }

	public MyWebElement waitUntilElementPresentInWindow(String windowName) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		final String myWindowName = windowName;		
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					Set<String> handlers = driver.getWindowHandles();
					for (String handler : handlers) {
						driver.switchTo().window(handler);
						if (driver.getTitle().contains(myWindowName)) {
							qaLog.info(myWindowName);
							break;
						}
					}
					//driver.findElement(By.id("username"))
					WebElement element = getWebElement();
					if (!element.isDisplayed()) {
						throw new NotFoundException("Element is not displayed");
					}
					return element;

				} catch (Exception e) {
					throw new NotFoundException("Element is not displayed");
				}
			}
		});
		this.setElementName(getElementName());
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

	public MyWebElement waitUntilElementEnabled() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = getWebElement();
				if (!element.isDisplayed()) {
					throw new NotFoundException("Element is not displayed");
				}
				if (!element.isEnabled()) {
					throw new NotFoundException("Element is not enabled");
				}
				return element;
			}
		});
		this.setElementName(getElementName());
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

	public void waitUntilAjaxCallsComplete() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				
				boolean result = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
				if (!result) {
					throw new NotFoundException("Ajax calls have not completed");
				}				
				return result;
			}
		});
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);		
	}
	
	
	public MyWebElement waitUntilElementChecked() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = getWebElement();
				if (!element.isDisplayed()) {
					throw new NotFoundException("Element is not displayed");
				}
				if (!element.isSelected()) {
					throw new NotFoundException("Element is not selected");
				}
				return element;
			}
		});
		this.setElementName(getElementName());
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

	public MyWebElement waitUntilElementEnabledInFrame(String frameName) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		final String myFrameName = frameName;
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					driver.switchTo().defaultContent().switchTo()
							.frame(myFrameName);
					WebElement element = getWebElement();
					if (!element.isDisplayed()) {
						throw new NotFoundException("Element is not displayed");
					}
					if (!element.isEnabled()) {
						throw new NotFoundException("Element is not enabled");
					}
					return element;
				} catch (Exception e) {
					throw new NotFoundException("Frame is not found");
				}
			}
		});
		this.setElementName(getElementName());
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

	public MyWebElement waitUntilElementPresentInFrame(String frameName) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, WEB_ELEMENT_WAIT_TIME);
		final String myFrameName = frameName;
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					driver.switchTo().defaultContent().switchTo()
							.frame(myFrameName);
					WebElement element = getWebElement();
					if (!element.isDisplayed()) {
						throw new NotFoundException("Element is not displayed");
					}
					return element;
				} catch (Exception e) {
					throw new NotFoundException("Frame is not found");
				}
			}
		});
		this.setElementName(getElementName());
		driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

    public void verifyElementFound() {
        try{
            print(" Verify whether element " + getElementName() + " found");
            waitUntilElementFound();
        }
        catch (Exception e) {
            //print(" Verify whether element " + myWebElement.getElementName() + " found");
            throw new NoSuchElementException ("Element " + getElementName() + " Not Found!");
        }
    }

    public void verifyElementPresent() {
        try{
            print(" Verify whether element " + getElementName() + " displayed");
            waitUntilElementPresent();
        }
        catch (Exception e) {
            print(" -" + getElementName() + " NOT displayed");
            throw new NotFoundException("Element " + getElementName() + " Not Displayed!");
        }
    }

    public void verifyElementNotPresent() {
        print(" Verify whether element " + getElementName() + " NOT displayed");
        Boolean flag = false;
        try {
            waitUntilElementPresentByTime(1);    // wait for 1 second to see whether element disappeared
        }  catch (Exception e) {
            flag = true;
        }
        if ( !flag  ) {
            print(" Element " + getElementName() + " still displayed!");
            throw new IllegalStateException("Element " + getElementName() + " still displayed!");
        }
    }

    public MyWebElement waitUntilElementDisappearByTime(int t) throws InterruptedException {
        for(int i=0; i<t; i++)
        {
            try {
                verifyElementNotPresent();
                return this;
            } catch (IllegalStateException e) {
                Thread.sleep(1000);
                continue;
            }
        }
        print(" Element " + getElementName() + " still displayed after " + t + "seconds!");
        throw new IllegalStateException("Element " + getElementName() + " still displayed!");
    }

//    public static boolean isElementPresent(MyWebElement myWebElement) {
//        try {
//            myWebElement.getWebElement();
//             return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
//    public static void verifyElementPresent() {
//        if(! myWebElement.isElementPresent ) {
//            print("Element "  + getElementName() + " Not found!");
//            throw new NoSuchElementException("Element "  + getElementName() + " Not found!");
//        }
//    }

}
