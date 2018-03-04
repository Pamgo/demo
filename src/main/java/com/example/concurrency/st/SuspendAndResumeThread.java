package com.example.concurrency.st;

/**
 * 线程挂起Suspend以及继续执行Resume（已废除，不推荐使用）
 * @author OKali
 *  因为Suspend在导致线程暂停的同时，并不会释放任何锁资源
 */
public class SuspendAndResumeThread {
	
	public static Object lock = new Object();
	
	static ChangeObjectThread thread1 = new ChangeObjectThread("T1");
	
	static ChangeObjectThread thread2 = new ChangeObjectThread("T2");
	
	public static class ChangeObjectThread extends Thread {
		
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("in " + getName());
				Thread.currentThread().suspend();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		thread1.start();
		thread2.start();
		Thread.sleep(100);
		thread1.resume();
		thread2.resume();
		thread1.join();
		thread2.join();
	}
}
