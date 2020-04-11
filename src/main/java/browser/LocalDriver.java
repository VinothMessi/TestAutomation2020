package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
				return new ChromeDriver();
			case FIREFOX:
				return new FirefoxDriver();
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