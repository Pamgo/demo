package com.example.concurrency.st;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore演示
 * @author OKali
 *
 */
public class SemapDemo implements Runnable {
	
	final Semaphore semaphore = new Semaphore(5);

	@Override
	public void run() {
		try {
			semaphore.acquire();
			// 耗时操作
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId() + ":done!");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		SemapDemo task = new SemapDemo();
		for (int i = 0; i < 20; i++) {
			executorService.submit(task); // 每组5个
		}
		executorService.shutdown();
	}

}
