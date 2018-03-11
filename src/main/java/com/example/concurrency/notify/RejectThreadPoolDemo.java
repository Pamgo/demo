package com.example.concurrency.notify;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 自定义线程池和拒绝策略的使用
 * @author OKali
 * 例子：yugong项目
 * / 全局线程池
 *           extractorExecutor = new ThreadPoolExecutor(extractorSize,
 *              extractorSize,
 *               60,
 *              TimeUnit.SECONDS,
 *               new ArrayBlockingQueue<Runnable>(extractorSize * 2), // 建议使用有界队列，防止任务积压导致系统不可用
 *               new NamedThreadFactory("Global-Extractor"), // 定制线程
 *               new ThreadPoolExecutor.CallerRunsPolicy()); // 只用调用者所在线程来运行任务
 *
 */
public class RejectThreadPoolDemo {
	
	public static void main(String[] args) throws InterruptedException {
		MyTask myTask = new MyTask();
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, 
				new LinkedBlockingQueue<Runnable>(10),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.toString() + "is Discard!");
					}
				});
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			executorService.submit(myTask);
			Thread.sleep(10);
		}
	}

	public static class MyTask implements Runnable {

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:"
					+ Thread.currentThread().getId());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
