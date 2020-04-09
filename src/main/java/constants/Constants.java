package constants;

public class Constants {

	private Constants() {
	}

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
	
	public static final String TEXT = "text";
	public static final String VALUE = "value";
	public static final String INDEX = "index";
	
	/* <---------- Custom Utilities ---------> */
	public static final String MINUTE = "minutes";
	public static final String SECOND = "seconds";
	public static final String MILLISECOND = "milliseconds";

}