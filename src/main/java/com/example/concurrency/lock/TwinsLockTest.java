package com.example.concurrency.lock;

import java.util.concurrent.locks.Lock;

import com.example.concurrency.SleepUtil;

/**
 * 队列同步器
 * @author OKali
 *
 */
public class TwinsLockTest {

	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		final Lock lock = new TwinsLock();
		
		class Worker extends Thread {
			
			@Override
			public void run() {
				while (true) {
					lock.lock();
					try {
						SleepUtil.second(1);
						System.out.println(Thread.currentThread().getName());
						SleepUtil.second(1);
					} finally {
						lock.unlock();
					}
				}
			}
		}
		// ----------------------------------------------
		for (int i = 0; i < 10; i++) {
			Worker worker = new Worker();
			worker.setDaemon(true);
			worker.start();
		}
		
		for (int i = 0; i < 10; i++) {
			SleepUtil.second(1);
			System.out.println();
		}
	}
	
}
