package pageObjects;

import org.openqa.selenium.By;

public interface HomePageObjects {

	By signOnLink = By.linkText("SIGN-ON");
	By registerLink = By.linkText("REGISTER");
	By logo = By.xpath("//img[@alt='Mercury Tours']");

}