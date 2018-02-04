package com.example.concurrency.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPools {
	
	public static void main(String[] args) {
		//createCacheThreadPools();
		//createNewFixedThreadPools();
		//createScheduledThreadPools();
		createNewSingleThreadExecutor();
	}
	
	/**
	 * 创建一个可缓存线程池，应用中存在的线程数可以无限大
	 */
	private static void createCacheThreadPools() {
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		System.out.println("----------------------newCacheThreadPoll Start------------------------------");
		for (int i = 0; i <= 4; i++) {
			final int index = i;
			newCachedThreadPool.execute(new ThreadForPools(index));
		}
	}
	
	/**
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
	 */
	private static void createNewFixedThreadPools() {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		System.out.println("----------------------newFixedThreadPool Start------------------------------");
		for (int i = 0; i < 4; i++) {
			newFixedThreadPool.execute(new ThreadForPools(i));
		}
	}
	
	/**
	 * 创建一个定长线程池，支持定时及周期性任务执行
	 */
	private static void createScheduledThreadPools() {
		ScheduledExecutorService newScheduledThreadPools = Executors.newScheduledThreadPool(2);
		System.out.println("----------------------newScheduledThreadPool Start------------------------------");
		for (int i = 0; i < 4; i++) {
			
			// 周期线程池
			newScheduledThreadPools.scheduleWithFixedDelay(new ThreadForPools(i), 0, 3, TimeUnit.SECONDS);
			
			// 延迟线程
			//newScheduledThreadPools.schedule(new ThreadForPools(i), 3, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
	 */
	private static void createNewSingleThreadExecutor() {
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		System.out.println("----------------------newScheduledThreadPool Start------------------------------");
		for (int i = 0; i < 4; i++) {
			newSingleThreadExecutor.execute(new ThreadForPools(i));
		}
	}
	

}
