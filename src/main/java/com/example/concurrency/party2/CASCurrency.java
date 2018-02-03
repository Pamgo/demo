package com.example.concurrency.party2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 使用CAS算法实现原子性操作
 * @author OKali
 *
 */
public class CASCurrency {

	private AtomicInteger atomic = new AtomicInteger(0);
	private int i = 0;
	
	public static void main(String args[]) {
		final CASCurrency cas = new CASCurrency();
		List<Thread> threads = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		for (int j = 0; j < 100; j++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						cas.count();
						cas.safeCount();
					}
				}
			});
			threads.add(t);
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		// 等待所有线程都执行完
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(cas.i);
		System.out.println(cas.atomic.get());
		System.out.println((System.currentTimeMillis() - startTime)+"ms");
	}
	
	/**
	 * 使用cas实现线程安全计数器
	 */
	private void safeCount() {
		for(;;) {
			int i = atomic.get();
			boolean suc = atomic.compareAndSet(i, ++i);
			if (suc) {
				break;
			}
		}
	}
	
	/**
	 * 非线程安全计数器
	 */
	private void count() {
		i++;
	}
	
}
