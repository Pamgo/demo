package com.example.cache.spring;

/**
 * 定义缓存
 * @author OKali
 *
 */
public interface Cache<K, V> {

	// 返回缓存实例名称
	String getName();
	
	Object getCacheValue(K key);
	
	void setCacheObject(K key, V value);
	
	void clearCache(String name);
}
