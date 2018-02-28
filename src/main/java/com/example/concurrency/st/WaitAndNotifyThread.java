package com.example.concurrency.st;

/**
 * 等待以及唤醒wait()/notify()
 * @author OKali
 *
 */
public class WaitAndNotifyThread {

	final static Object key = new Object();
	
	public static void main(String[] args) {
		Thread t1 = new T1();
		Thread t2 = new T2();
		t1.start();
		t2.start();
	}
	
	public static class T1 extends Thread {
		@Override
		public void run() {
			synchronized (key) {
				System.out.println(System.currentTimeMillis() + ":T1 start");
				try {
					System.out.println(System.currentTimeMillis() + ":T1 wait for object");
					key.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(System.currentTimeMillis() + ":T1 end !");
		}
	}
	
	public static class T2 extends Thread {
		@Override
		public void run() {
			synchronized (key) {
				try {
					System.out.println(System.currentTimeMillis() + ":T2 start ! notify one thread");
					key.notify();
					System.out.println(System.currentTimeMillis() + ":T2 end !");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
