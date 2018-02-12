package com.example.concurrency.join;

import com.example.concurrency.SleepUtil;

public class Join {

	public static void main(String[] args) {
		Thread previous = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			// 每一个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
			Thread thread = new Thread(new Domino(previous),String.valueOf(i));
			thread.start();
			previous = thread;
		}
		SleepUtil.second(5);
		System.out.print(Thread.currentThread().getName() + "terminate.");
	}
	
	static class Domino implements Runnable {
		private Thread thread;
		
		public Domino(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "terminate.");
		}
		
	}
	
}
