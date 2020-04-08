package pages;

import org.openqa.selenium.WebDriver;

import pageObjects.RegistrationPageObjects;

public class RegistrationPage extends BasePage implements RegistrationPageObjects {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public void enterContactInformations(String firstName, String lastName, String phone, String email) {

	}

	public void enterMailingInformations(String address1, String address2, String city, String state, String postalCode,
			String country) {

	}

	public void enterUserInformations(String userName, String passWord, String confirmPassword) {

	}
	
	public void submit() {
		
	}

}