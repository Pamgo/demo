package com.okali.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe("线程安全")
public class AtomicIntergerFieldUpdaterExample {

	private static AtomicIntegerFieldUpdater<AtomicIntergerFieldUpdaterExample> updater 
											= AtomicIntegerFieldUpdater.newUpdater(AtomicIntergerFieldUpdaterExample.class, "count");
	private volatile int count = 100;  // 必须volatile修饰
	
	private static AtomicIntergerFieldUpdaterExample example = new AtomicIntergerFieldUpdaterExample();
	
	public static void main(String[] args) {
		if (updater.compareAndSet(example, 100, 120)) { // 如果当前变量中的字段count = 100 ,则更像为200
			log.info("update success 1, {}", example.getCount());
		}
		if (updater.compareAndSet(example, 100, 120)) {
			log.info("update success 2, {}", example.getCount());
		} else {
			log.info("update failed, {}", example.getCount());
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
