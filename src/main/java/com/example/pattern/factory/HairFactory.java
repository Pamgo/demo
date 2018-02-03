package com.example.pattern.factory;

import java.util.Map;

public class HairFactory {

	/**
	 * 方式一
	 * @param name
	 * @return
	 */
	public static HairInterface drawHairDIY(String name) {
		if ("left".equals(name)) {
			return new LeftHair();
		} else if ("right".equals(name)){
			return new RightHair();
		}
		return null;
	}
	
	/**
	 * 方式二
	 * @param className
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static HairInterface drawHairDIYforClassName(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		HairInterface hairInterface = (HairInterface) Class.forName(className).newInstance();
		return hairInterface;
	}
	
	/**
	 * 方法三
	 * @param key  配置文件的key值
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static HairInterface drawHairDIYforKey(String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Map<String, String> map = new PropertiesReaderUtils().getProperties();
		HairInterface hairInterface = (HairInterface) Class.forName(map.get(key)).newInstance();
		return hairInterface;
	}
}
