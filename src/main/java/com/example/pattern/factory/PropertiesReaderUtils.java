package com.example.pattern.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReaderUtils {

	public Map<String, String> getProperties() {
		
		Properties props = new Properties();
		Map<String, String> map = new HashMap<>();
		try {
			InputStream inputStream = getClass().getResourceAsStream("type.properties");
			props.load(inputStream);
			Enumeration<?> en = props.propertyNames();
			while(en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String property = props.getProperty(key);
				map.put(key, property);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
