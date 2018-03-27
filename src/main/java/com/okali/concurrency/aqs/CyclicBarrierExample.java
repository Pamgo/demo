package com.okali.concurrency.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample {
	
	private static CyclicBarrier barrier = new CyclicBarrier(5);
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						race(threadNum);
					} catch (InterruptedException | BrokenBarrierException e) {
						log.error("Exception", e);
					}
				}
			});
		}
		executorService.shutdown();
	}
	
	private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
		Thread.sleep(1000);
		log.info("{} is ready!", threadNum);
		barrier.await();
		log.info("{} is continue!", threadNum);
	}

}
