package com.okali.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountDownLatchExample {
	
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
		latch.await();
		log.info("finish");
		executorService.shutdown();
	}
	
	private static void test(int threadNum) throws InterruptedException {
		Thread.sleep(100);
		log.info("{}", threadNum);	
	}

}
