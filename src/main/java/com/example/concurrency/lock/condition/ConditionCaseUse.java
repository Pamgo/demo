package com.example.concurrency.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示condition 的等待和唤醒
 * @author OKali
 * condition依赖lock接口
 *
 */
public class ConditionCaseUse {

	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public void conditionWait() throws InterruptedException {
		lock.lock();
		try {
			condition.await();
		} finally {
			lock.unlock();
		}
	}
	
	public void conditionoSigal() {
		lock.lock();
		try {
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
