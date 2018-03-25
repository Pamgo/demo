package com.okali.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreExample2 {
	
	private static int threadCount = 200;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3);  // 一次三个
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire(3); // 获取多个许可
						test(threadNum);
						semaphore.release(3); // 释放多个许可
					} catch (InterruptedException e) {
						log.error("exception", e);
					}
				}
			});
		}
		executorService.shutdown();
	}
	
	private static void test(int threadNum) throws InterruptedException {
		Thread.sleep(1000);
		log.info("{}", threadNum);	
	}

}
