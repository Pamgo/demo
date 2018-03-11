package com.example.concurrency.st;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport是一个非常实用的线程阻塞工具，它可以在线程的任何位置让线程阻塞
 * @author OKali
 * LockSupport的静态方法park()可以阻塞当前线程，类似有prakNanos()/parkUntil()等。它们
 * 实现了一个限时等待
 */
public class LockSupportDemo {
	public static Object u = new Object();
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread {
		
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (u) {
				System.out.println("in " + getName());
				LockSupport.park();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		LockSupport.unpark(t1);
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}
	
}
