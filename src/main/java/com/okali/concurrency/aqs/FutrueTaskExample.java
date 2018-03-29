package com.okali.concurrency.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FutrueTaskExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> future = new FutureTask<>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				log.info("do something in callable");
				Thread.sleep(5000);
				return "Done";
			}
		});
		
		new Thread(future).start();
		log.info("do something in main");
		Thread.sleep(1000);
		String result = future.get();
		log.info("result: {}", result);
	}
}
