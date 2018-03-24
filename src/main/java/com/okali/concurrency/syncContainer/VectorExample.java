package com.okali.concurrency.syncContainer;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * Vector
 * @author OKali
 *
 */
@Slf4j
@ThreadSafe
public class VectorExample {

	// 请求总数
	public static int clientTotal = 5000;
	// 并发数
	public static int threadTotal = 200;
	
	final static Semaphore semaphore = new Semaphore(threadTotal);
	
	private static Vector<Integer> vector = new Vector<>();
	
	public static void main(String[] args) throws InterruptedException {
		
		final CountDownLatch latch = new CountDownLatch(clientTotal);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			exec.execute(new JobRunnable(latch, count));
		}
		latch.await(); // latch为0的时候输出结果
		exec.shutdown();
		log.info("size:{}",vector.size());
	}
	
	private static class JobRunnable implements Runnable {
		
		private int count;
		
		private CountDownLatch latch;
		
		public JobRunnable(CountDownLatch latch, int count) {
			this.count = count;
			this.latch = latch;
		}
		
		@Override
		public void run() {
			try {
				semaphore.acquire();
				update(count);
				semaphore.release();
			} catch (InterruptedException e) {
				log.info("Error", e);
			}
			latch.countDown();
		}
	}
	
	private static void update (int i) {
		vector.add(i);
	}
}
