package com.example.cache.spring;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单缓存
 * @author OKali
 *
 */
public class ConfigCache_v1 {
	
	private ConfigCache_v1() {}
	
	private static final ConfigCache_v1 CONFIG_CACHE = new ConfigCache_v1();
	
	private static ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<>();
	
	public static ConfigCache_v1 getConfigCache () {
		return CONFIG_CACHE;
	}
	
	public static ConcurrentHashMap<String, Object> getCacheInstance() {
		return cacheMap;
	}
	
	public void setCacheObject(String key, Object value) {
		cacheMap.put(key, value);
	}
	
	public Object getCacheValue(String key) {
		return cacheMap.get(key);
	}
	
	public void clearCache() {
		cacheMap.clear();
		cacheMap = new ConcurrentHashMap<>();
	}
	
}
