package com.example.concurrency.countDownLatch;

public class Test {

	public static void main(String[] args) {
		boolean result = false;
		try {
			result = ApplicationStartUpUtil.checkExternalServices();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("External services validation completed !! Result was :" + result);
	}
}
