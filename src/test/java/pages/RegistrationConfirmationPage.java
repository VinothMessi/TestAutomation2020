package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import base.BasePage;
import exception.MyException;
import pageObjects.RegistrationConfirmationObjects;

public class RegistrationConfirmationPage extends BasePage implements RegistrationConfirmationObjects {

	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public void verifySigInLink() throws MyException {
		Assert.assertTrue(verify(signInLink, "isDisplayed"));
	}

	public FlightDetailsPage gotToFlightsPage() throws MyException {
		clickOn(flightsLink);
		return new FlightDetailsPage(lDriver);
	}

}