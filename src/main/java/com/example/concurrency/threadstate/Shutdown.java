package com.example.concurrency.threadstate;

import com.example.concurrency.SleepUtil;

/**
 * 安全终止一个线程
 * 利用boolean变量来控制
 * @author OKali
 *
 */
public class Shutdown {
	
	public static void main(String[] args) {
		Runner one = new Runner();
		Thread countRunner = new Thread(one, "CounThread");
		countRunner.start();
		
		// 睡眠一秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
		SleepUtil.second(2);
		countRunner.interrupt();
		
		Runner two = new Runner();
		countRunner = new Thread(two,"countThread");
		countRunner.start();
		SleepUtil.second(2);
		two.cancel();
	}

	
	private static class Runner implements Runnable {
		
		private long i;
		private volatile boolean on = true;

		@Override
		public void run() {
			while (on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println(Thread.currentThread().getName()+"Count i = " + i);
		}
		
		public void cancel() {
			on = false;
		}
		
	}
	
}
