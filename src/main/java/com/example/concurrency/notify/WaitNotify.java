package com.example.concurrency.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.concurrency.SleepUtil;


/**
 * 生产者消费者演示
 * @author OKali
 *
 */
public class WaitNotify {

	static boolean flag = true;
	static Object lock = new Object();
	
	public static void main(String[] args) {
		Thread waitThread = new Thread(new Wait(),"WaitThread");
		waitThread.start();
		
		SleepUtil.second(1);
		
		Thread notifyThread = new Thread(new Notify(),"notifyThread");
		notifyThread.start();
	}
	
	static class Wait implements Runnable {

		@Override
		public void run() {
			// 加锁，拥有lock的monitor
			synchronized (lock) {
				while(flag) {
					try {
						System.out.println(Thread.currentThread() + "flag is true, wait@ " 
					+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 条件满足时，完成工作
				System.out.println(Thread.currentThread() +" flag is false,running @ " 
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}
	
	// ------------------------------------------
	
	static class Notify implements Runnable {

		@Override
		public void run() {
			// 加锁，拥有lock的Monitor
			synchronized (lock) {
				// 获取lock的锁，然后进行通知，通知时不会释放lock的锁
				// 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
				System.out.println(Thread.currentThread() + " hold lock. notify @ " 
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				SleepUtil.second(5);
			}
			// 再次加锁
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock again. sleep @ "
						+new SimpleDateFormat("HH:mm:ss").format(new Date()));
				SleepUtil.second(5);
			}
		}
		
	}
	
	
}
