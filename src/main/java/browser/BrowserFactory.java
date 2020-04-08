package browser;

import static constants.Constants.*;

import exception.MyException;

public class BrowserFactory {

	private static BrowserFactory singleInstance = null;

	private BrowserFactory() {
	}

	/* <---------- Create/Return Single Ton Instance ---------> */
	public static BrowserFactory getInstance() {
		if (singleInstance == null) {
			singleInstance = new BrowserFactory();
		}
		return singleInstance;
	}

	/* <---------- Get Local/Remote Browser Session ---------> */
	/* Parameters : Local (Or) Remote */
	/* Parameter : Driver File Path for Local */
	/* Parameter : HUB URL for Remote */
	public IDriver getDriver(String driverType, String filePathOrHubUrl) throws MyException {
		if (!driverType.isEmpty()) {
			switch (driverType) {
			case LOCAL:
				return new LocalDriver(filePathOrHubUrl);
			case REMOTE:
				return new RemoteDriver(filePathOrHubUrl);
			default:
				throw new MyException("Driver Type : " + driverType + " Not Supported");
			}
		} else {
			throw new MyException("Driver Type Is Empty");
		}
	}

}