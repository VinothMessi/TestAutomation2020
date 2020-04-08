package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver lDriver;
	protected WebDriverWait lWait;

	public BasePage(WebDriver driver) {
		this.lDriver = driver;
		PageFactory.initElements(driver, this);
		this.lWait = new WebDriverWait(driver, 30);
	}

}