package pages;

import org.openqa.selenium.WebDriver;

import base.BasePage;
import pageObjects.SignOnPageObjects;

public class SignOnPage extends BasePage implements SignOnPageObjects {

	public SignOnPage(WebDriver driver) {
		super(driver);
	}

	public HomePage testingDone() {
		return new HomePage(lDriver);
	}

}