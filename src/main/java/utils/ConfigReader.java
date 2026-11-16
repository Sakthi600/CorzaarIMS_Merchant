
package utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;

	static {
		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {

			if (input == null) {
				throw new IllegalStateException("config.properties file not found!");
			}

			properties = new Properties();
			properties.load(input);

		} catch (IOException e) {
			throw new RuntimeException("Unable to load config.properties", e);
		}
	}

	public static String getProperty(String key) {

		String value = System.getProperty(key);

		if (value != null && !value.trim().isEmpty()) {
			return value;
		}

		return properties.getProperty(key);
	}
}
