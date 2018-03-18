package com.okali.concurrency.count;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import lombok.extern.slf4j.Slf4j;

/**
 * 非线程安全
 * @author OKali
 *
 */
@Slf4j
public class CountExample1 {
	
	// 请求总数
	public static int clientTotal = 5000;
	// 同时并发执行的线程数
	public static int threadTotal = 200;
	
	public static long count = 0;
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal); // 同时申请200个请求
		for (int i = 0; i < clientTotal; i++) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();// 申请一个许可
						add();
						semaphore.release(); // 释放一个许可
					} catch (InterruptedException e) {
						log.error("exception", e);
					} 
				}
			});
		}
		executorService.shutdown();
		log.info("count:{}", count);
	}

	private static void add() {
		count++ ;
	}
	
}
