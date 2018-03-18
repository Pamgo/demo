package com.okali.concurrency.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe("线程安全")
public class AtomicBooleanExample {
	
	private static AtomicBoolean isHappened = new AtomicBoolean(false);
	
	public static int threadNum = 200;
	
	public static int clientNum = 5000;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadNum);
		final CountDownLatch latch = new CountDownLatch(clientNum);
		for (int i = 0; i < clientNum; i++) {
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						test();
						semaphore.release();
					} catch (InterruptedException e) {
						log.error("exception", e);
					}
					latch.countDown();
				}
			});
		}
		
		latch.await();
		exec.shutdown();
		log.info("isHappened:{}", isHappened.get());
	}
	
	private static void test () {
		if (isHappened.compareAndSet(false, true)) {
			log.info("execute");
		}
	}
}
