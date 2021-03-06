package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import exception.MyException;

import static constants.Constants.*;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriver implements IDriver {

	DesiredCapabilities cap;
	String remoteHUBURL;

	public RemoteDriver(String hubURL) throws MyException {
		verify(hubURL);
		this.remoteHUBURL = hubURL;
	}

	/* <---------- Launching Remote Browser Session ---------> */
	/* Parameters : Chrome (Or) Firefox */
	@Override
	public WebDriver launch(String browserName) throws MyException, MalformedURLException {
		if (!browserName.isEmpty()) {
			switch (browserName) {
			case CHROME:
				cap = DesiredCapabilities.chrome();
				return new RemoteWebDriver(new URL(this.remoteHUBURL), cap);
			case FIREFOX:
				cap = DesiredCapabilities.firefox();
				return new RemoteWebDriver(new URL(this.remoteHUBURL), cap);
			default:
				throw new MyException("Remote Bowser : " + browserName + " Not Supported");
			}
		} else {
			throw new MyException("Browser Name Is Empty");
		}
	}

	private static boolean verify(String hubURL) throws MyException {
		boolean flag = false;
		if (!hubURL.isEmpty()) {
			flag = true;
		} else {
			throw new MyException("Hub URL Is Empty");
		}
		return flag;
	}

}