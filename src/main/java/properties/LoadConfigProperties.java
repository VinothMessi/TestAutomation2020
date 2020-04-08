package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

import exception.MyException;

public class LoadConfigProperties {

	private String lFilePath;
	private String lFileName;

	private Properties properties;

	/* <---------- Constructor Loads Configuration Properties ---------> */
	/* Property File Path */
	/* Property File Name */
	public LoadConfigProperties(String propertyFilePath, String propertyFileName) throws MyException {
		verify(propertyFilePath, propertyFileName, "properties");
		this.lFilePath = propertyFilePath;
		this.lFileName = propertyFileName;
		loadProperties();
	}

	/* <---------- Get Specific Value For Given Key ---------> */
	/* Property Key */
	public String fetch(String key) throws MyException {
		String value = "";
		if (!key.isEmpty()) {
			value = this.properties.getProperty(key);
			if (value == null) {
				throw new MyException("Property value returned is null, please check the key");
			}
		} else {
			throw new MyException("Property Key is empty");
		}
		return value;
	}

	/* <---------- Get All Values From Property File ---------> */
	public Map<String, String> fetchAll() throws MyException {
		HashMap<String, String> values = new HashMap<>();
		for (Object key : fetchAllKeys()) {
			/* <---------- Change Object type to String type ---------> */
			String k = (String) key;
			values.put(k, fetch(k));
		}
		return values;
	}

	/* <---------- Get All Keys From Property File ---------> */
	private Set<Object> fetchAllKeys() throws MyException {
		if (this.properties.keySet() == null) {
			throw new MyException("Property Key set is null");
		}
		return this.properties.keySet();
	}

	private void loadProperties() throws MyException {
		try {
			initializeProperties();
			this.properties.load(new FileInputStream(this.lFilePath + this.lFileName));
		} catch (IOException e) {
			throw new MyException("Unable to find:" + this.lFileName + "\n" + e.getMessage());
		}
	}

	private void initializeProperties() {
		this.properties = new Properties();
	}

	private static boolean verify(String filePath, String fileName, String fileExtension) throws MyException {
		boolean flag = false;
		if (!filePath.isEmpty()) {
			if (!fileName.isEmpty()) {
				if (FilenameUtils.getExtension(fileName).equals(fileExtension)) {
					flag = true;
				} else {
					throw new MyException(fileName + " " + "File extension is incorrect");
				}
			} else {
				throw new MyException("File name is empty");
			}
		} else {
			throw new MyException("File path is empty");
		}
		return flag;
	}

}