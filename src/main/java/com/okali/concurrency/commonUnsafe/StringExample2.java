package com.okali.concurrency.commonUnsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import com.annotation.NotThreadSafe;
import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程不安全类以及写法
 * @author OKali
 *
 */
@Slf4j
@ThreadSafe
public class StringExample2 {

		// 请求总数
		public static int clientTotal = 5000;
		// 并发数
		public static int threeadTotal = 200;
		
		public static StringBuffer stringBuffer = new StringBuffer();
		
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
							update();
							semaphore.release();
						} catch (InterruptedException e) {
							log.error("exception", e);
						}
						latch.countDown();  // 每一个请求减一
					}
				});
			}
			
			latch.await(); // latch为0的时候输出结果
			exec.shutdown();
			log.info("stringBuffer.length:{}", stringBuffer.length());
		}
		
		public static void update () {
			stringBuffer.append("1");
		}
}
