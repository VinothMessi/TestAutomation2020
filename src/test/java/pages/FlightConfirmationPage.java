package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.BasePage;
import exception.MyException;
import pageObjects.FlightConfirmationPageObjects;

public class FlightConfirmationPage extends BasePage implements FlightConfirmationPageObjects {

	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public void printFlightConfirmation(String expPrice) throws MyException {
		lWait.until(ExpectedConditions.visibilityOfElementLocated(flightConfirmationMessage));
		System.out.println(getTextFrom(flightConfirmationMessage));
		System.out.println(getTextFrom(identifyAll(prices).get(1)));
		Assert.assertEquals(expPrice, getTextFrom(identifyAll(prices).get(1)));
	}

	public SignOnPage signOff() throws MyException {
		clickOn(signOffLink);
		return new SignOnPage(lDriver);
	}

}