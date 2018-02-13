package com.example.concurrency.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存检测
 * @author OKali
 *
 */
public class CacheHealthChecker extends BaseHealthChecker {

	public CacheHealthChecker(String serviceNmae, CountDownLatch latch) {
		super(serviceNmae, latch);
	}

	@Override
	protected void verifyService() {
		System.out.println("Checking :" + this.getServiceName());
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(this.getServiceName() + "is UP");
	}

}
