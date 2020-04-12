package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import base.TestBase;
import exception.MyException;

public class SanityTest2 extends TestBase {

	@Test(priority = 1)
	public void open_Registration() throws MyException {
		page.visit(appURL);
		page.verifyLogo();
		registerPage = page.goToRegistrationPage();
	}

	//@Test(priority = 2)
	public void user_Registration() throws IOException, MyException {
		registerPage.enterContactInformations("test", "user1", "0123456789", "testuser1@gmail.com");
		registerPage.enterMailingInformations("user1AddressLine1", "user1AddressLine2", "Chennai", "TAMIL NADU",
				"600080", "INDIA");
		registerPage.enterUserInformations("testuser1", "testuser1", "testuser1");
		registerConfirmationPage = registerPage.confirmRegistration();
	}

	//@Test(priority = 3)
	public void confirm_User_Registration() throws IOException, MyException {
		registerConfirmationPage.verifySigInLink();
		flightDetailsPage = registerConfirmationPage.gotToFlightsPage();
	}

	//@Test(priority = 4)
	public void flight_Reservation() throws IOException, MyException {
		flightDetailsPage.selectNoOfPassengers("2");
		selectFlights = flightDetailsPage.goToSelectFlightsPage();
		selectFlights.reserveFlights();
		flightConfirmationPage = selectFlights.gotToFlightsConfirmationPage();
	}

	//@Test(priority = 5)
	public void flight_Confirmation() throws IOException, MyException {
		flightConfirmationPage.printFlightConfirmation("$1169 USD");
		signOnPage = flightConfirmationPage.signOff();
		page = signOnPage.testingDone();
	}

}