package com.okali.concurrency.count;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import com.annotation.NotThreadSafe;
import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class CountExample2 {
	// 请求总数
	public static int clientTotal = 5000;
	// 并发数
	public static int threeadTotal = 200;
	
	public static AtomicInteger  count = new AtomicInteger(0); // 线程安全（原子性操作）
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threeadTotal);
		final CountDownLatch latch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						add();
						semaphore.release();
					} catch (InterruptedException e) {
						log.error("exception", e);
					}
				}
			});
			latch.countDown();  // 每一个请求减一
		}
		
		latch.await(); // latch为0的时候输出结果
		exec.shutdown();
		log.info("count:{}", count.get());
	}
	
	public static void add () {
		count.incrementAndGet();
	}
}
