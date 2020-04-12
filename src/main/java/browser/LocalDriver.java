package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import exception.MyException;

import static constants.Constants.*;

public class LocalDriver implements IDriver {

	private String lDriverFilePath;

	public LocalDriver(String driverFilePath) throws MyException {
		verify(driverFilePath);
		this.lDriverFilePath = driverFilePath;
	}

	/* <---------- Launching Local Browser Session ---------> */
	/* Parameters : Chrome (Or) Firefox */
	@Override
	public WebDriver launch(String browserName) throws MyException {
		if (!browserName.isEmpty()) {
			setDriverProperty(browserName);
			switch (browserName) {
			case CHROME:
				ChromeOptions chromeOptions = setChromeOptions();
				return new ChromeDriver(chromeOptions);
			case FIREFOX:
				FirefoxOptions ffOptions = setFFOptions();
				return new FirefoxDriver(ffOptions);
			default:
				throw new MyException("Local Bowser : " + browserName + " Not Supported");
			}

		} else {
			throw new MyException("Browser Name Is Empty");
		}
	}

	/* <---------- Setting Driver Properties and File Path ---------> */
	/* Parameters : Chrome (Or) Firefox */
	private void setDriverProperty(String browserName) throws MyException {
		String os = OS.toLowerCase().substring(0, 3);
		switch (browserName) {
		case CHROME:
			System.setProperty(CHROME_DRIVER_PROPERTY,
					this.lDriverFilePath + CHROME_DRIVER_FILE_NAME + (os.equals("win") ? ".exe" : ""));
			break;
		case FIREFOX:
			System.setProperty(FIREFOX_DRIVER_PROPERTY,
					this.lDriverFilePath + FIREFOX_DRIVER_FILE_NAME + (os.equals("win") ? ".exe" : ""));
			break;
		default:
			throw new MyException("Bowser : " + browserName + " Not Supported");
		}
	}

	/* <---------- Setting ChromeOptions ---------> */
	/* Returns : ChromeOptions */
	private ChromeOptions setChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		return options;
	}

	/* <---------- Setting FirefoxOptions ---------> */
	/* Returns : FirefoxOptions */
	private FirefoxOptions setFFOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
		return options;
	}

	/* <---------- Setting InternetExplorerOptions ---------> */
	/* Returns : InternetExplorerOptions */
	private InternetExplorerOptions setIEOptions() {
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		return options;
	}

	private static boolean verify(String filePath) throws MyException {
		boolean flag = false;
		if (!filePath.isEmpty()) {
			flag = true;
		} else {
			throw new MyException("Driver File Path Is Empty");
		}
		return flag;
	}

}