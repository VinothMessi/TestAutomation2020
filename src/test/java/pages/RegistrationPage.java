package pages;

import org.openqa.selenium.WebDriver;

import exception.MyException;
import pageObjects.RegistrationPageObjects;

public class RegistrationPage extends BasePage implements RegistrationPageObjects {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public void enterContactInformations(String firstName, String lastName, String phone, String email)
			throws MyException {
		type(firstName, firstNameTextBox);
		type(lastName, lastNameTextBox);
		type(phone, phoneTextBox);
		type(email, emailTextBox);
	}

	public void enterMailingInformations(String address1, String address2, String city, String state, String postalCode,
			String country) throws MyException {
		type(address1, address1TextBox);
		type(address2, address2TextBox);
		type(city, cityTextBox);
		type(state, stateTextBox);
		type(postalCode, postalCodeTextBox);
		selectText(country, countryDropDown);
	}

	public void enterUserInformations(String userName, String passWord, String confirmPassword) throws MyException {
		type(userName, userNameTextBox);
		type(passWord, passwordTextBox);
		type(confirmPassword, confirmPasswordTextBox);
	}

	public RegistrationConfirmationPage confirmRegistration() throws MyException {
		clickOn(submitButton);
		return new RegistrationConfirmationPage(lDriver);
	}

}