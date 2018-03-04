package com.example.concurrency.st;

/**
 * 可靠的suspend,使用wait/notify
 * @author OKali
 *
 */
public class GoodSuspend {
	
	public static Object mutex = new Object();

	public static class ChangeObjectThread extends Thread {
		volatile boolean suspendme = false;
		
		public void suspendMe() {
			suspendme = true;
		}
		
		public void resumeMe() {
			suspendme = false;
			synchronized (this) {
				notify();
			}
		}
		
		@Override
		public void run() {
			while (true) {
				
				synchronized (this) {
					while (suspendme) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				synchronized (mutex) {
					System.out.println("in ChangeObjectThread");
				}
				Thread.yield();
			}
			
		}
	}
	
	public static class ReadObjectThread extends Thread {
		
		@Override
		public void run() {
			while(true) {
				synchronized (mutex) {
					System.out.println("in ReadObjectThread");
				}
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ChangeObjectThread t1 = new ChangeObjectThread();
		t1.setName("t1");
		ReadObjectThread   t2 = new ReadObjectThread();
		t2.setName("t2");
		t1.start();
		t2.start();
		Thread.sleep(1000);
		
		t1.suspendMe();
		System.out.println("suspend t1 2 sec");
		
		Thread.sleep(2000);
		System.out.println("resume t1");
		t1.resumeMe();
	}
}
