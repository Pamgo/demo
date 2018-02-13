package com.example.concurrency.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主启动类，负责闭锁，然后等待，直到所有服务都被检测完
 * @author OKali
 *
 */
public class ApplicationStartUpUtil {
	
	private static List<BaseHealthChecker> services;
	
	private static CountDownLatch latch;
	
	private ApplicationStartUpUtil() {}
	
	private final static ApplicationStartUpUtil START_UP_UTIL = new ApplicationStartUpUtil();
	
	public static ApplicationStartUpUtil getInstance() {
		return START_UP_UTIL;
	}
	
	public static boolean checkExternalServices() throws InterruptedException {
		latch = new CountDownLatch(3);
		services = new ArrayList<>();
		services.add(new NetWorkHealthChecker("NetWorkHealth", latch));
		services.add(new DataBaseHealthChecker("DataBaseHealth", latch));
		services.add(new CacheHealthChecker("CacheHealth", latch));
		
		ExecutorService executorService = Executors.newFixedThreadPool(services.size());
		
		for (final BaseHealthChecker checker : services) {
			executorService.execute(checker);
		}
		
		latch.await();
		
		for (final BaseHealthChecker healthChecker : services) {
			if (!healthChecker.isServiceUp()) {
				return false;
			}
		}
		return true;
	}

}
