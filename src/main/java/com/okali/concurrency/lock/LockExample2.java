package com.okali.concurrency.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LockExample2 {
	
	/**
	 * 比如说一个类里面封装了一个内部类map，而这个map，我们不希望把所有函数都暴露给别人，
	 * 它们能做的事情完全根据我提供的方法来使用，这个时候我们就可以单独封装一些方法来给外部用，
	 * 而用的时候，我们不用担心什么并发问题，这个时候我们就可以加上锁。
	 */
	private final Map<String, Data> map = new HashMap<>();
	
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private final Lock readLock = readWriteLock.readLock();
	
	private final Lock writeLock = readWriteLock.writeLock();
	
	public Data get(String key) {
		readLock.lock();
		try {
			return map.get(key);
		} finally {
			readLock.unlock();
		}
	}
	
	public Set<String> getAllKeys() {
		readLock.lock();
		try {
			return map.keySet();
		} finally {
			readLock.unlock();
		}
	}
	
	public Data put(String key, Data value) {
		writeLock.lock();
		try {
			return map.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}
	
	class Data {
		
	}
}
