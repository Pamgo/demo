package com.okali.concurrency.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample3 {
	
	private static CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
		@Override
		public void run() {
			log.info("callback is coming!");
		}
	});
	
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
		try {
			barrier.await(2000, TimeUnit.MILLISECONDS);
		} catch (BrokenBarrierException | TimeoutException e) {
			log.warn("BrokenBarrierException", e);
		}
		log.info("{} continue!", threadNum);
	}

}
