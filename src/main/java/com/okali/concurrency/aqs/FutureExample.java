package com.okali.concurrency.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FutureExample {

	static class MyCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			log.info("do something in callable");
			Thread.sleep(5000);
			return "Done";
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> futrue = executorService.submit(new MyCallable());
		log.info("do something in main");
		Thread.sleep(1000);
		String result = futrue.get();
		log.info("result: {}", result);
		executorService.shutdown();
	}
}
