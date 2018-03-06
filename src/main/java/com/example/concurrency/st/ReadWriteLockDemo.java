package com.example.concurrency.st;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock模拟耗时操作
 * @author OKali
 *
 */
public class ReadWriteLockDemo {

	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private int value;
	
	// 处理耗时的读操作
	public Object handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	// 处理写操作
	public void handleWrite(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			value = index;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
		Runnable readRunnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					readWriteLockDemo.handleRead(readLock); // 读写锁处理读
					//readWriteLockDemo.handleRead(lock); // 不使用读写锁，处理时间将会接近20秒
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable writeRunnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					readWriteLockDemo.handleWrite(writeLock, new Random().nextInt());
					//readWriteLockDemo.handleWrite(lock, new Random().nextInt()); // 不使用读写锁，处理时间将会接近20秒
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		for (int i= 0; i < 18; i++) {
			new Thread(readRunnable).start();
		}
		
		for (int i = 18; i < 20; i++) {
			new Thread(writeRunnable).start();
			
		}
	
	}
}
