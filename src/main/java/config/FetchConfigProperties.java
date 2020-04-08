package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

import myexception.MyException;

public class FetchConfigProperties {

	private String lFilePath;
	private String lFileName;

	private Properties properties;
	
	/* <---------- Constructor Loads Configuration Properties ---------> */
	/* Property File Path */
	/* Property File Name */
	public FetchConfigProperties(String propertyFilePath, String propertyFileName) throws MyException {
		if (!propertyFilePath.isEmpty()) {
			this.lFilePath = propertyFilePath;
			if (!propertyFileName.isEmpty()) {
				if (FilenameUtils.getExtension(propertyFileName).equals("properties")) {
					this.lFileName = propertyFileName;
				} else {
					throw new MyException("Config Properties : File extension is incorrect");
				}
			} else {
				throw new MyException("Config Properties : File name is empty");
			}
		} else {
			throw new MyException("Config Properties : File path is empty");
		}
		try {
			this.properties = new Properties();
			this.properties.load(new FileInputStream(this.lFilePath + this.lFileName));
		} catch (IOException e) {
			throw new MyException("Unable to find:" + this.lFileName + "\n" + e.getMessage());
		}
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

}