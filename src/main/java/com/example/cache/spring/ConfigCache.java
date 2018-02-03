package com.example.cache.spring;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * ConfigCache 缓存管理
 * （饿汉单例模式--线程安全）
 * @author OKali
 *
 */
public class ConfigCache implements CacheManager<String, Object> {
	
	private final ConcurrentMap<String, Cache<String, Object>> cacheMap = new ConcurrentHashMap<String, Cache<String, Object>>(16);
	private static final ConfigCache CONFIG_CACHE = new ConfigCache();
	
	private ConfigCache() {}
	
	public static final ConfigCache getConfigCacheManger() {
		return CONFIG_CACHE;
	}

	@Override
	public Cache<String, Object> getCache(String name) {
		synchronized (this.cacheMap) { // 非必需
			Cache<String, Object> cache = this.cacheMap.get(name);
			if (cache == null) {
				// 创建缓存
				cache = new ConfigContext(name);
				this.cacheMap.put(name, cache);
			}
		return cache;
		}
	}
	
	@Override
	public void flushCache(String name) {
		Cache<String, Object> cache = this.cacheMap.get(name);
		cache.clearCache(name);
	}

}
