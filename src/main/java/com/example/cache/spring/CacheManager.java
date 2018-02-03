package com.example.cache.spring;

/**
 * 缓存管理
 * @author OKali
 *
 */
public interface CacheManager<K, V> {
	
	/**
	 * 获取缓存
	 * @param name
	 * @return
	 */
	Cache<K, V> getCache(String name);
	
	/**
	 * 清除缓存
	 * @param name
	 */
	void flushCache(String name);
	
}
