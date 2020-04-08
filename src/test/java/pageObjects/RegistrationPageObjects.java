package pageObjects;

import org.openqa.selenium.By;

public interface RegistrationPageObjects {

	By firstNameTextBox = By.name("firstName");
	By lastNameTextBox = By.name("lastName");
	By phoneTextBox = By.name("phone");
	By emailTextBox = By.name("userName");

	By address1TextBox = By.name("address1");
	By address2TextBox = By.name("address2");
	By cityTextBox = By.name("city");
	By stateTextBox = By.name("state");
	By postalCodeTextBox = By.name("postalCode");
	By countryDropDown = By.name("country");

	By userNameTextBox = By.name("email");
	By passwordTextBox = By.name("password");
	By confirmPasswordTextBox = By.name("confirmPassword");

	By submitButton = By.name("register");

}