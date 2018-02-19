package com.example.concurrency.lock.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 通过读写锁演示缓存
 * @author OKali
 * DIYCache组合了一个非线程安全的HashMap作为缓存实现，
 * 同时使用读写锁的读锁和写锁来保证DIYCache的线程安全
 *
 */
public class DIYCache {
	
	static Map<String, Object> map = new HashMap<>();
	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	static Lock r = rwl.readLock();
	static Lock w = rwl.writeLock();
	
	// 获取一个key对应的value
	public static final Object get(String key) {
		r.lock();
		try {
			return map.get(key);
		} finally {
			r.unlock();
		}
	}
	
	// 设置key对应的value,并返回旧的value
	public static final Object put(String key, Object value) {
		w.lock();
		try {
			return map.put(key, value);
		} finally {
			w.unlock();
		}
	}
	
	// 清空所有的内容
	public static final void clear() {
		w.lock();
		try {
			map.clear();
		} finally {
			w.unlock();
		}
	}

}
