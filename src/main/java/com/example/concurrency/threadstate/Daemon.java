package com.example.concurrency.threadstate;

import com.example.concurrency.SleepUtil;

public class Daemon {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonRunner(),"DaemonRunner");
		thread.setDaemon(true);
		thread.start();
	}

	// java虚拟机中已经没有非daemon线程，虚拟机需要退出。
	// java虚拟机中所有daemon线程都需要立即终止，因此DaemonRunner立即终止，但是finally块并没有执行
	static class DaemonRunner implements Runnable {

		@Override
		public void run() {
			try {
				SleepUtil.second(100);
			} finally {
				System.out.println("DaemonThread finally run.");
			}
		}
		
	}
}
