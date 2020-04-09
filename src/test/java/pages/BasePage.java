package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.MyException;

import static constants.Constants.*;

public class BasePage {

	protected WebDriver lDriver;
	protected WebDriverWait lWait;
	private Actions action;
	private JavascriptExecutor js;

	public BasePage(WebDriver driver) {
		if (driver != null) {
			this.lDriver = driver;
			this.lWait = new WebDriverWait(driver, 30);
			js = (JavascriptExecutor) driver;
		}
	}

	/* <---------- Find Element ---------> */
	/* Parameter : Locator */
	/* @return WebElement */
	protected WebElement identify(By locator) throws MyException {
		WebElement element = null;
		try {
			element = lDriver.findElement(locator);
		} catch (Exception e) {
			throw new MyException("Failed To Identify:" + locator);
		}
		return element;
	}

	/* <---------- Find Elements ---------> */
	/* Parameter : Locator */
	/* @return List of WebElement */
	protected List<WebElement> identifyAll(By locator) throws MyException {
		List<WebElement> list = null;
		try {
			list = lDriver.findElements(locator);
		} catch (Exception e) {
			throw new MyException("Failed To Identify:" + locator);
		}
		return list;
	}

	/* <---------- Get Page Title ---------> */
	/* @return Title of the current web page */
	protected String getPageTitle() throws MyException {
		String pageTitle = "";
		try {
			pageTitle = lDriver.getTitle();
		} catch (Exception e) {
			throw new MyException("Failed To Fetch Page Title");
		}
		return pageTitle;
	}

	/* <---------- Get Current URL ---------> */
	/* @return URL of the current web page */
	protected String getURL() throws MyException {
		String url = "";
		try {
			url = lDriver.getCurrentUrl();
		} catch (Exception e) {
			throw new MyException("Failed To Fetch Current URL");
		}
		return url;
	}

	/* <---------- Navigate one step back in current web page ---------> */
	protected void navigateBrowserBack() throws MyException {
		try {
			lDriver.navigate().back();
		} catch (Exception e) {
			throw new MyException("Failed To Navigate Back In Current Browser Session");
		}
	}

	/* <---------- Navigate one step forward in current web page ---------> */
	protected void navigateBrowserForward() throws MyException {
		try {
			lDriver.navigate().forward();
		} catch (Exception e) {
			throw new MyException("Failed To Navigate Forward In Current Browser Session");
		}
	}

	/* <---------- Refresh current web page ---------> */
	protected void refresh() throws MyException {
		try {
			lDriver.navigate().refresh();
		} catch (Exception e) {
			throw new MyException("Failed To Refresh Current Browser Session");
		}
	}

	/* <---------- Verify Element Present or Not ---------> */
	/* Parameter : Locator */
	/* @return True (or) False */
	protected boolean isElementPresent(By locator) throws MyException {
		List<WebElement> list = null;
		try {
			list = identifyAll(locator);
			int size = list.size();
			if (size > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyException("Failed To Verify Presence Of Element:" + locator);
		}
	}

	/*
	 * <---------- Verify Element isDisplayed (or) IsEnabled (or) IsSelected
	 * --------->
	 */
	/* Parameter : Locator and Verification Type */
	/* @return True (or) False */
	protected boolean check(By locator, String verificationType) throws MyException {
		try {
			if (lDriver.findElements(locator).size() > 0) {
				lWait.until(ExpectedConditions.presenceOfElementLocated(locator));
				isElementPresent(locator);
				switch (verificationType) {
				case DISPLAYED:
					identify(locator).isDisplayed();
					return true;
				case ENABLED:
					identify(locator).isEnabled();
					return true;
				case SELECTED:
					identify(locator).isSelected();
					return true;
				default:
					throw new MyException("Invalid Verification Type");
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new MyException("Failed To Check Whether " + locator + " " + verificationType);
		}
	}

	/*
	 * <---------- Select Element ByVisibleText (or) ByValue (or) ByIndex --------->
	 */
	/* Parameter : Locator, Choice type and Value */
	protected void choose(String choiceType, String value, By locator) throws MyException {
		Select dropDown;
		try {
			dropDown = new Select(identify(locator));
			switch (choiceType) {
			case TEXT:
				dropDown.selectByVisibleText(value);
			case VALUE:
				dropDown.selectByValue(value);
			case INDEX:
				dropDown.selectByIndex(Integer.parseInt(value));
			default:
				throw new MyException("Invalid Verification Type");
			}
		} catch (Exception e) {
			throw new MyException("Failed To Choose " + choiceType + ":" + value + " From:" + locator);
		}
	}

	/* <---------- Click On WebElement ---------> */
	/* Parameter : Locator */
	protected void clickOn(By locator) throws MyException {
		try {
			if (isElementPresent(locator) == true) {
				lWait.until(ExpectedConditions.elementToBeClickable(locator));
				identify(locator).click();
			}
		} catch (Exception e) {
			throw new MyException("Failed To Click On:" + locator);
		}
	}

	/* <---------- Click On WebElement ---------> */
	/* Parameter : Locator */
	public void usingJavaScriptClickOn(By locator) {
		try {
			if (isElementPresent(locator) == true) {
				lWait.until(ExpectedConditions.elementToBeClickable(locator));
				js.executeScript("arguments[0].click();", identify(locator));
			}
		} catch (Exception e) {
			System.out.println("Failed To Click On:" + locator);
		}
	}

	/* <---------- SendKeys for to type ---------> */
	/* Parameter : Locator and Text */
	protected void type(String text, By locator) throws MyException {
		try {
			if (isElementPresent(locator) == true) {
				identify(locator).clear();
				identify(locator).sendKeys(text);
			}
		} catch (Exception e) {
			throw new MyException("Failed To Type:" + text + " Inside:" + locator);
		}
	}

	/* <---------- Get Attribute of WebElement ---------> */
	/* Parameter : Locator and Attribute */
	/* @return String : Value of given attribute */
	public String getElementAttributeValue(By locator, String attribute) throws MyException {
		String value = "";
		try {
			value = identify(locator).getAttribute(attribute);
		} catch (Exception e) {
			throw new MyException("Failed To Get The Value Of Attribute: " + attribute + " From:" + locator);
		}
		return value;
	}

	/* <---------- Get Text From WebElement ---------> */
	/* Parameter : Locator */
	/* @return String : Text from element */
	protected String getTextFrom(By locator) throws MyException {
		String text = null;
		try {
			text = identify(locator).getText();
			if (text.length() == 0) {
				text = getElementAttributeValue(locator, "innerText");
			}
			if (!text.isEmpty()) {
				text = text.trim();
			} else {
				throw new MyException("Text Not Found");
			}
		} catch (Exception e) {
			throw new MyException("Failed To Get The Text From: " + locator);
		}
		return text;
	}

	/* <---------- Check (check box) ---------> */
	/* Parameter : Locator */
	public void Check(By locator, String info) throws MyException {
		try {
			if (!check(locator, "isSelected")) {
				clickOn(locator);
			}
		} catch (Exception e) {
			throw new MyException("Unable to check:" + locator);
		}
	}

	/* <---------- Un-Check (check box) ---------> */
	/* Parameter : Locator */
	public void UnCheck(By locator, String info) throws MyException {
		try {
			if (check(locator, "isSelected")) {
				clickOn(locator);
			}
		} catch (Exception e) {
			throw new MyException("Unable to uncheck:" + locator);
		}
	}

	/* <---------- Element Submit ---------> */
	/* Parameter : Locator */
	public Boolean Submit(By locator) throws MyException {
		if (identify(locator) != null) {
			identify(locator).submit();
			return true;
		} else
			return false;
	}

	/* <---------- Mouse over ---------> */
	/* Parameter : Locator */
	public void mouseHover(By locator) throws MyException {
		try {
			WebElement element = identify(locator);
			Actions action = new Actions(lDriver);
			action.moveToElement(element).perform();
		} catch (Exception e) {
			throw new MyException("Unable to mouse over: " + locator);
		}
	}

	/* <---------- Mouse over and Select ---------> */
	/* Parameter : Element locator and Item Locator */
	public void mouseHoverAndSelect(By elementLocator, By itemLocator) throws MyException {
		try {
			WebElement element = identify(elementLocator);
			Actions action = new Actions(lDriver);
			action.moveToElement(element).perform();
			action.click(identify(itemLocator)).perform();
		} catch (Exception e) {
			throw new MyException("Unable to mouse over:" + elementLocator + " " + "and select:" + itemLocator);
		}
	}

	/* <---------- Double Click ---------> */
	/* Parameter : Locator */
	public void doubleClick(By locator) throws MyException {
		try {
			action = new Actions(lDriver);
			action.doubleClick(identify(locator));
			action.perform();
		} catch (Exception e) {
			throw new MyException("Unable to double click on: " + locator);
		}
	}

	/* <---------- Right Click ---------> */
	/* Parameter : Locator */
	public void rightClick(By locator) throws MyException {
		try {
			WebElement element = identify(locator);
			action = new Actions(lDriver);
			action.contextClick(element).build().perform();
		} catch (Exception e) {
			throw new MyException("Unable to right click on:" + locator);
		}
	}

	/* <---------- Right click and Select ---------> */
	/* Parameter : Element locator and Item Locator */
	public void rightClickAndSelect(By elementLocator, By itemLocator) throws MyException {
		try {
			WebElement element = identify(elementLocator);
			Actions action = new Actions(lDriver);
			action.contextClick(element).build().perform();
			identify(itemLocator);
			clickOn(itemLocator);
		} catch (Exception e) {
			throw new MyException("Unable to right click on:" + elementLocator + " " + " and select:" + itemLocator);
		}
	}

	/* <---------- Key Press ---------> */
	/* Parameter : Key */
	public void press(Keys key) throws MyException {
		try {
			action = new Actions(lDriver);
			action.keyDown(key).build().perform();
		} catch (Exception e) {
			throw new MyException("Unable to Press:" + key);
		}
	}

}