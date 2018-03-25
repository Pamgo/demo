package com.okali.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountDownLatchExample2 {
	
	private static int threadCount = 200;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		final CountDownLatch latch = new CountDownLatch(threadCount);
		
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						test(threadNum);
					} catch (InterruptedException e) {
						log.error("exception", e);
					} finally {
						latch.countDown();
					}
				}
			});
		}
		latch.await(10, TimeUnit.MILLISECONDS);  // 等待10毫秒后执行，所以造成了先打印finish，给定时间等待（过了等待时间久会执行）
		log.info("finish");
		executorService.shutdown();
	}
	
	private static void test(int threadNum) throws InterruptedException {
		Thread.sleep(100);
		log.info("{}", threadNum);	
	}

}
