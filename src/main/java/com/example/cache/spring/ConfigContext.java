package com.example.cache.spring;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存升级版，支持根据缓存名称使用不同缓存实例
 * TODO 后期加入失效时间等内容
 * @author OKali
 *
 */
public class ConfigContext implements Cache<String, Object> {
	
	/**
	 * 缓存名称
	 */
	private String name;
	
	public final ConcurrentHashMap<String, Object> store = new ConcurrentHashMap<>();
	
	public ConfigContext(String name) {
		this.name = name;
	}
	
	public void setCacheObject(String key, Object value) {
		synchronized (this.store) {
			this.store.put(key, value);
		}
	}
	
	public Object getCacheValue(String key) {
		return this.store.get(key);
	}
	
	public void clearCache(String name) {
		this.store.clear();
	}

	@Override
	public final String getName() {
		return this.name;
	}
	
}
