package com.example.concurrency.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore(信号量)是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源
 * @author OKali
 *
 */
public class SemaphoreTest {
	
	private static final int THREAD_COUNT = 30;
	
	private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
	
	//接受一个整型的数字，表示可用的许可证数量
	// Semaphore(10)表示允许10个线程获取许可，也就是最大并发数为10
	private static Semaphore semaphore = new Semaphore(10);
	
	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() { 
					try {
						semaphore.acquire(); // 获取一个许可
						System.out.println("save datas");
						semaphore.release(); // 归还一个许可
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
	}

}
