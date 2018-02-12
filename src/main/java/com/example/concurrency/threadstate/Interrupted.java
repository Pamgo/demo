package com.example.concurrency.threadstate;

import com.example.concurrency.SleepUtil;

/**
 * 睡眠的线程会把中断标志位清楚
 * @author OKali
 *
 */
public class Interrupted {

	
	public static void main(String[] args) {
		// 不停地睡眠
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);
		sleepThread.start();
		busyThread.start();
		
		// 休眠5秒，让sleepThread和BusyThread线程充分运行
		SleepUtil.second(5);
		
		sleepThread.interrupt();
		busyThread.interrupt();
		
		System.out.println("sleepThread:" + sleepThread.isInterrupted());
		System.out.println("busyThread:" + busyThread.isInterrupted());
		SleepUtil.second(10);
	}
	
	static class SleepRunner implements Runnable {

		@Override
		public void run() {
			while (true) {
				SleepUtil.second(100);
			}
		}
		
	}
	
	static class BusyRunner implements Runnable {

		@Override
		public void run() {
			while (true) {
			}
		}
		
	}
}
