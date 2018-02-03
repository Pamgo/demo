package com.example.cache;

import java.util.concurrent.locks.Lock;

/**
 * TCache缓存回掉方法，如果不存在或者过期则创建
 * @author OKali
 *
 */
public abstract class CacheCallBack {
	
	private CacheContext cacheContext;
	
	public CacheCallBack(String key, long loseEffectivenessInterval, long timeType) {
		
		if (key == null || "".equals(key)) {
			throw new NullPointerException("key is null or EmptyString");
		}
		if (loseEffectivenessInterval <=0 ) {
			throw new NullPointerException("loseEffectivenessInterval <=0");
		}
		if (timeType <= 0) {
			throw new NullPointerException("timeType <= 0");
		}
		
		CacheContext cacheContext = CacheContext.Cache_Context_Map.get(key);
		if (cacheContext == null) {
			cacheContext = new CacheContext(loseEffectivenessInterval, timeType);
			cacheContext.Cache_Context_Map.put(key, cacheContext);
		}
		this.cacheContext  = cacheContext;
	}
	
	public abstract Object doWrite();
	
	public Object doWriteAndGet() {
		CacheContext cacheContext = this.cacheContext;
		long now  = System.currentTimeMillis();
		if (cacheContext.getStart_time_long_or_update_cache_time() == 0 || 
				now - cacheContext.getStart_time_long_or_update_cache_time() >=
				cacheContext.getLose_effectiveness_interval()) {
			Lock lock = cacheContext.getLock().writeLock();
			lock.lock();
			try {
				Object object = doWrite();
				cacheContext.setObject(object);
				cacheContext.setStart_time_long_or_update_cache_time(now);
				return cacheContext.getObject();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				lock.unlock();
			}
		} else {
			Lock lock = cacheContext.getLock().readLock();
			lock.lock();
			try {
				return cacheContext.getObject();
			} finally {
				lock.unlock();
			}
		}
	}
	
}
