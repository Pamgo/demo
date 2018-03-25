package com.okali.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreExample3 {
	
	private static int threadCount = 200;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						if (semaphore.tryAcquire()) { // 尝试获取许可，其它拿不到许可的线程将不会再次执行
							test(threadNum);
							semaphore.release();
						}
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
