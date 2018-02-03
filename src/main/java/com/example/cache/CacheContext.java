package com.example.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TCache缓存上下文，记录缓存的开始时间和失效时间
 * @author OKali
 *
 */
public class CacheContext {

	public static final ConcurrentHashMap<String, CacheContext> Cache_Context_Map = new ConcurrentHashMap<>();
	
	public static final long SECOND = 1000;     //一秒
	public static final long MINUTE = SECOND * 60; //一分
	public static final long HOUR = MINUTE * 60;   // 一小时
	public static final long DAY = HOUR * 24;		// 一天
	
	private long start_time_long_or_update_cache_time = 0;   //当前缓存的时间
	private long lose_effectiveness_interval = 0;            // 缓存过期时间
	
	public CacheContext(long loseEffectivenessInterval, long timeType) {
		this.lose_effectiveness_interval = loseEffectivenessInterval * timeType;
	}
	
	/**
	 * 支持读写锁
	 */
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private Object object = null; //缓存值
	
	
	public long getStart_time_long_or_update_cache_time() {
		return start_time_long_or_update_cache_time;
	}
	public void setStart_time_long_or_update_cache_time(long start_time_long_or_update_cache_time) {
		this.start_time_long_or_update_cache_time = start_time_long_or_update_cache_time;
	}
	public long getLose_effectiveness_interval() {
		return lose_effectiveness_interval;
	}
	public void setLose_effectiveness_interval(long lose_effectiveness_interval) {
		this.lose_effectiveness_interval = lose_effectiveness_interval;
	}
	public ReadWriteLock getLock() {
		return lock;
	}
	public void setLock(ReadWriteLock lock) {
		this.lock = lock;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	 
}
