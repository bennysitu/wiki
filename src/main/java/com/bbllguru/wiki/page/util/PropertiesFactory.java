package com.bbllguru.wiki.page.util;

import com.bbllguru.wiki.page.exception.NoSuchPropertyException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public final class PropertiesFactory {

	private static HashMap<String, Properties> propertiesMap = new HashMap<String, Properties>();

	/**
	 * Private constructor to prevent instantiation
	 */
	private PropertiesFactory() {}

	public static Properties getProperties(String propertyName) {
		if (!propertiesMap.containsKey(propertyName)) {
			initProperty(propertyName);
		}
		return propertiesMap.get(propertyName);
	}

	private static void initProperty(String propertyName) {
		Properties properties = new java.util.Properties();

		try {
			ClassLoader classLoader = PropertiesFactory.class.getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(propertyName + ".properties");
			properties.load(inputStream);
		} catch (IOException e) {
			throw new NoSuchPropertyException("Could not find property file for: " + propertyName + ".properties");
		}

		propertiesMap.put(propertyName, properties);
	}

}
