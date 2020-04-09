package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import base.TestBase;
import exception.MyException;

public class SanityTest extends TestBase {

	@Test(priority = 1)
	public void openRegistration() throws MyException {
		page.visit(appURL);
		page.verifyLogo();
		registerPage = page.goToRegistrationPage();
	}

	@Test(priority = 2)
	public void userRegistration() throws IOException, MyException {
		registerPage.enterContactInformations("test", "user1", "0123456789", "testuser1@gmail.com");
		registerPage.enterMailingInformations("user1AddressLine1", "user1AddressLine2", "Chennai", "TAMIL NADU",
				"600080", "INDIA");
		registerPage.enterUserInformations("testuser1", "testuser1", "testuser1");
		registerConfirmationPage = registerPage.confirmRegistration();
	}

	@Test(priority = 3)
	public void confirmUserRegistration() throws IOException, MyException {
		registerConfirmationPage.verifySigInLink();
		flightDetailsPage = registerConfirmationPage.gotToFlightsPage();
	}

}