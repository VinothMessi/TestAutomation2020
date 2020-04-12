package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;
import exception.MyException;
import pageObjects.FlightDetailsPageObjects;

public class FlightDetailsPage extends BasePage implements FlightDetailsPageObjects {

	public FlightDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void selectNoOfPassengers(String noOfPassengers) throws MyException {
		lWait.until(ExpectedConditions.elementToBeClickable(noOfPassengersDropDown));
		selectText(noOfPassengers, noOfPassengersDropDown);
	}

	public SelectFlightsPage goToSelectFlightsPage() throws MyException {
		lWait.until(ExpectedConditions.elementToBeClickable(continueButton));
		clickOn(continueButton);
		return new SelectFlightsPage(lDriver);
	}

}