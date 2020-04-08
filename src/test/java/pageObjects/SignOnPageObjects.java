package pageObjects;

import org.openqa.selenium.By;

public interface SignOnPageObjects {

	By userNameTextBox = By.name("userName");
	By passwordTextBox = By.name("password");		
	By submitButton = By.name("login");
	By message = By.xpath("//b[contains(text(),'Welcome back')]");

}