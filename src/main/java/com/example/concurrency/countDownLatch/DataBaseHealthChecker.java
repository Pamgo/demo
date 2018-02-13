package com.example.concurrency.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 数据库连接检测
 * @author OKali
 *
 */
public class DataBaseHealthChecker extends BaseHealthChecker {

	public DataBaseHealthChecker(String serviceNmae, CountDownLatch latch) {
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
