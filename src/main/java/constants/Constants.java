package constants;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class Constants {

	private Constants() {
	}
	
	public static final String OS = System.getProperty("os.name");
	public static final String SELENIUM_VERSION = "3.141.59";
	
	/* <---------- Property Details ---------> */
	public static final String CONFIG_FILE_PATH = "./resources/config/";
	public static final String CONFIG_FILE_NAME = "config.properties";

	/* <---------- Driver Properties ---------> */
	public static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	public static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";

	/* <---------- Driver File Names ---------> */
	public static final String CHROME_DRIVER_FILE_NAME = "chromedriver";
	public static final String FIREFOX_DRIVER_FILE_NAME = "geckodriver";

	/* <---------- Browser Details ---------> */
	public static final String LOCAL = "Local";
	public static final String REMOTE = "Remote";
	public static final String CHROME = "Chrome";
	public static final String FIREFOX = "Firefox";
	
	/* <---------- Custom Utilities ---------> */
	public static final String DISPLAYED = "isDisplayed";
	public static final String ENABLED = "isEnabled";
	public static final String SELECTED = "isSelected";
	
	/* <---------- Wait Time Unit ---------> */
	public static final int WAIT_TIME = 50;
	
	/* <---------- File Extensions ---------> */
	public static final String IMAGE_EXT = "png";
	public static final String REPORT_EXT = "html";
	
	/* <---------- Test Report Details ---------> */
	public static final String REPORT_ENCODING = "utf-8";
	public static final String REPORT_NAME = "Automation Execution";
	public static final String DOC_NAME = "AutomationTesting2020";
	public static final Theme REPORT_THEME = Theme.STANDARD;
	
}