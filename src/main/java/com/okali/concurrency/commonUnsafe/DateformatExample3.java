package com.okali.concurrency.commonUnsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class DateformatExample3 {

	// 请求总数
	public static int clientTotal = 5000;
	// 并发数
	public static int threadTotal = 200;
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
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
				}
			});
			latch.countDown();  // 每一个请求减一
		}
		
		latch.await(); // latch为0的时候输出结果
		exec.shutdown();
	}
	
	private static void update () {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			dateFormat.parse("20180324");
			log.info("输出：{}", dateFormat.getCalendar().getTime());
		} catch (ParseException e) {
			log.error("parse Exception", e);
		}
	}
}
