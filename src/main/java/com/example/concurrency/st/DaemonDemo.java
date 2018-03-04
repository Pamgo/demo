package com.example.concurrency.st;

/**
 * 守护线程，但一个java运用只有守护线程的时候，java虚拟机就会自然退出
 * @author OKali
 *
 */
public class DaemonDemo {

	public static class DaemonT extends Thread {
		
		@Override
		public void run() {
			while (true) {
				System.out.println("I am alive");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new DaemonT();
		t.setDaemon(true);    // 如果不设置为守护线程，则会一直打印输出
		t.start();
		Thread.sleep(2000);
	}
}
