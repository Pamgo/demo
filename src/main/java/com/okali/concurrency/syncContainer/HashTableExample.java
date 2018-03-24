package com.okali.concurrency.syncContainer;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * HashSet
 * @author OKali
 *
 */
@Slf4j
@ThreadSafe
public class HashTableExample {

	// 请求总数
	public static int clientTotal = 5000;
	// 并发数
	public static int threadTotal = 200;
	
	private static Map<Integer, Integer> map = new Hashtable<>();
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch latch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						update(count);
						semaphore.release();
					} catch (InterruptedException e) {
						log.error("exception", e);
					}
					latch.countDown();  // 线程池实际执行完之后调用，每一个请求减一
				}
			});
		}
		
		latch.await(); // latch为0的时候输出结果
		exec.shutdown();
		log.info("size:{}",map.entrySet().size());
	}
	
	private static void update (int i) {
		map.put(i, i);
	}
}
