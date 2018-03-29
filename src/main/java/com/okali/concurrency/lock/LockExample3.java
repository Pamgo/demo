package com.okali.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LockExample3 {
	
	public static void main(String[] args) {
		final ReentrantLock reentrantLock = new ReentrantLock();
		final Condition condition = reentrantLock.newCondition();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					reentrantLock.lock();			
					log.info("wait signal");  		// 1.等待信号
					condition.await();
				} catch (InterruptedException e) {
					log.error("InterruptedException", e);
				}
				log.info("get signal");			// 4
				reentrantLock.unlock();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				reentrantLock.lock();
				log.info("get lock");   // 2
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				condition.signalAll();
				log.info("send signal ~");    // 发送信号
				reentrantLock.unlock();
			}
		}).start();
	}

}
