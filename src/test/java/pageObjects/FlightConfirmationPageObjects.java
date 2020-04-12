package pageObjects;

import org.openqa.selenium.By;

public interface FlightConfirmationPageObjects {

	By flightConfirmationMessage = By.xpath("//font[(contains(text(),'Flight'))]");
	By prices = By.xpath("//font[contains(text(),'USD')]");
	By signOffLink = By.linkText("SIGN-OFF");
	
}