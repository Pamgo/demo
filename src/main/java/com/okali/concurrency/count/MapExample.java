package com.okali.concurrency.count;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 多线程并发情况下非线程安全
 * @author OKali
 *
 */
public class MapExample {
	
	private static final Logger LOG = Logger.getLogger(CountExample1.class);

	private static Map<Integer, Integer> map = Maps.newHashMap();
	
	private static int threadNum = 200; // 同时并发执行的线程数
	private static int clientNum = 5000;
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadNum); // 同时申请200个请求
		for (int index = 0; index < clientNum; index ++) {
			final int threadNum = index;
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						func(threadNum);
						semaphore.release();
					} catch (InterruptedException e) {
						LOG.error("Execption", e);
					}
				}
			});
		}
		executorService.shutdown();
		LOG.info("size:" + map.size());
	}

	public static void func(int threadNum) {
		map.put(threadNum, threadNum);
	}
}
