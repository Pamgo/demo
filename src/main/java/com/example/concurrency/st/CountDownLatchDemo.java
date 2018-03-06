package com.example.concurrency.st;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟火箭发射设备检查
 * @author OKali
 *
 */
public class CountDownLatchDemo implements Runnable {
	
	private static CountDownLatch end = new CountDownLatch(10);

	private static CountDownLatchDemo demo = new CountDownLatchDemo();
	
	@Override
	public void run() {
		// 模拟检查任务
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete!");
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executorService.submit(demo);
		}
		// 等待检查
		end.await();
		// 发射
		System.out.println("fire!!!!");
		executorService.shutdown();
	}

}
