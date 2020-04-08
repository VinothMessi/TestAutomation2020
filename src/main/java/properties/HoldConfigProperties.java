package properties;

import java.util.HashMap;
import java.util.Map;

import exception.MyException;

import static constants.Constants.*;

public class HoldConfigProperties {

	/* <---------- Map To Hold All Properties ---------> */
	public final Map<String, String> properties = new HashMap<>();

	/* <---------- Single Ton Instance ---------> */
	private static HoldConfigProperties singleInstance = null;

	private HoldConfigProperties() throws MyException {
		this.getAll();
	}

	/* <---------- Create/Return Single Ton Instance ---------> */
	public static HoldConfigProperties getInstance() throws MyException {
		if (singleInstance == null) {
			singleInstance = new HoldConfigProperties();
		}
		return singleInstance;
	}

	/* <---------- Put All The Properties Inside A Map ---------> */
	private void getAll() throws MyException {
		LoadConfigProperties loadProperties = new LoadConfigProperties(CONFIG_FILE_PATH, CONFIG_FILE_NAME);
		properties.putAll(loadProperties.fetchAll());
	}

}