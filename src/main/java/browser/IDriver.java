package browser;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import exception.MyException;

public interface IDriver {

	WebDriver launch(String browserName) throws MyException, MalformedURLException;

}