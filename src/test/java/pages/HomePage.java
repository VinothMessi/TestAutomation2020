package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.BasePage;
import exception.MyException;
import pageObjects.HomePageObjects;

public class HomePage extends BasePage implements HomePageObjects {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void visit(String url) throws MyException {
		open(url);
	}

	public void verifyLogo() throws MyException {
		Assert.assertTrue(verify(logo, "isDisplayed"));
	}

	public SignOnPage goToSignInPage() throws MyException {
		clickOn(signOnLink);
		return new SignOnPage(lDriver);
	}

	public RegistrationPage goToRegistrationPage() throws MyException {
		clickOn(registerLink);
		return new RegistrationPage(lDriver);
	}

}