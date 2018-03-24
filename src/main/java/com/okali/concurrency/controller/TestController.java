package com.okali.concurrency.controller;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {
	

	// 请求总数
	public  int clientTotal = 5000;
	// 并发数
	public  int threadTotal = 200;
	
	//private Vector<Integer> vector =null;

	@RequestMapping("/test")
	@ResponseBody
	public String test() throws InterruptedException {
        final Vector<Integer> vector = new Vector<>();
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
						update(vector,count);
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
		log.info("size:{}",vector.size());
		log.info("test!");
		return "test";
	}
	
	private void update (Vector<Integer> vector, int i) {
		vector.add(i);
	}
}
