package com.example.concurrency.st.interrupt;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 改代码会产生死循环，但是得益于锁的响应中断，解决了死循环这个问题
 * @author OKali
 *
 */
public class IntLock implements Runnable{
	
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	
	int lock;
	
	/**
	 * 控制加锁顺序，方便构造死循环
	 * @param lock
	 */
	public IntLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if (lock == 1) {
				lock1.lockInterruptibly();
				Thread.sleep(500);
				lock2.lockInterruptibly();
			} else {
				lock2.lockInterruptibly();
				Thread.sleep(500);
				lock1.lockInterruptibly();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId() + "线程退出！");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntLock intLock = new IntLock(1);
		Thread t1 = new Thread(intLock);
		Thread t2 = new Thread(intLock);
		
		t1.start();t2.start();
		Thread.sleep(1000);
		t1.interrupt();
	}
	

}
