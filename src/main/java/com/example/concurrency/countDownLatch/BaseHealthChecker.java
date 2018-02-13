package com.example.concurrency.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 这个类是一个Runnable，负责所有特定的外部服务健康的检测。它删除了重复的代码和闭锁的中心控制代码
 * @author OKali
 * 在这个例子中，我模拟了一个应用程序启动类，它开始时启动了n个线程类，
 * 这些线程将检查外部系统并通知闭锁，并且启动类一直在闭锁上等待着。
 * 一旦验证和检查了所有外部服务，那么启动类恢复执行
 *
 */
public abstract class BaseHealthChecker implements Runnable {
	private CountDownLatch latch;
	private String serviceName;
	private boolean serviceUp;
	
	// get latch Object in construtor so that after completing the task, 
	// thread can countDown() the latch
	public BaseHealthChecker(String serviceNmae, CountDownLatch latch) {
		this.serviceName = serviceNmae;
		this.latch = latch;
		this.serviceUp = false;
	}

	@Override
	public void run() {
		try {
			verifyService();
			serviceUp = true;
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			if (latch != null) {
				latch.countDown();
			}
		}
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public boolean isServiceUp() {
		return serviceUp;
	}
	
	// This method needs to be implemented by all specific service checker
	protected abstract void verifyService();

}
