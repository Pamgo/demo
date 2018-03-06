package com.example.concurrency.st;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * newCondition锁样例
 * @author OKali
 *
 */
public class ReenterLockCondition implements Runnable {
	
	public static ReentrantLock lock = new ReentrantLock();
	
	public static Condition condition = lock.newCondition();

	@Override
	public void run() {
		try {
			lock.lock();
			condition.await();
			System.out.println("Thread is going on!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReenterLockCondition t1 = new ReenterLockCondition();
		Thread thread = new Thread(t1);
		thread.start();
		Thread.sleep(2000);
		// 通知线程t1继续执行
		lock.lock();
		condition.signal(); // 唤醒之后需要释放相关的锁，谦让给被唤醒的线程
		lock.unlock();  // 如果省略改行，将无法获得重入锁，将无法继续执行
	}
}
