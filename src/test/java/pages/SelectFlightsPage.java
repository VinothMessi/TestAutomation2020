package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import exception.MyException;
import pageObjects.SelectFlightsPageObjects;

public class SelectFlightsPage extends BasePage implements SelectFlightsPageObjects {

	public SelectFlightsPage(WebDriver driver) {
		super(driver);
	}

	public void reserveFlights() throws MyException {
		lWait.until(ExpectedConditions.elementToBeClickable(reserveFlights));
		clickOn(reserveFlights);
	}

	public FlightConfirmationPage gotToFlightsConfirmationPage() throws MyException {
		lWait.until(ExpectedConditions.elementToBeClickable(buyFlights));
		clickOn(buyFlights);
		return new FlightConfirmationPage(lDriver);
	}

}