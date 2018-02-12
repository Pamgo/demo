package com.example.concurrency.threadstate;

import com.example.concurrency.SleepUtil;

/**
 * 深入理解线程状态
 * @author OKali
 *
 */
public class ThreadState {

	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"TimeWaitingThread").start();
		new Thread(new Waiting(),"WaitingThread").start();
		
		// 使用两个Blocked线程，一个获取锁成功(休眠)，另一个被阻塞（监视）
		new Thread(new Blocked(),"blockedThread-1").start();
		new Thread(new Blocked(),"blockedThread-2").start();
	}
	
	// 该线程不停地进行睡眠
	static class TimeWaiting implements Runnable {

		@Override
		public void run() {
			while(true) {
				SleepUtil.second(100);
			}
		}
		
	}
	
	// 该线程不在waiting.class上等待
	static class Waiting implements Runnable {

		@Override
		public void run() {
			while (true) {
				synchronized (Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	static class Blocked implements Runnable {

		@Override
		public void run() {
			synchronized (Blocked.class) {
				while(true) {
					SleepUtil.second(100);
				}
			}
		}
		
	}
}
