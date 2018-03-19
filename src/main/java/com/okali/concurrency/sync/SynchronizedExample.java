package com.okali.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedExample {
	
	private static Object lock = new Object();
	
	// 修饰代码块,如果使用两个不同的对象则不会顺序输出
	public void test1 () {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				log.info("test1 - {}", i);
			}
		}
	}
	
	// 修饰方法 (作用范围，整个方法)
	public synchronized void test2 () {
		for (int i = 0; i < 10; i++) {
			log.info("test1 - {}", i);
		}
	}
	
	// 锁为类成员
	public void test3 () {
		synchronized (lock) {
			for (int i = 0; i < 10; i++) {
				log.info("test1 - {}", i);
			}
		}
	}
	
	// 修饰类
	public void test3_1 () {
		synchronized (SynchronizedExample.class) {
			for (int i = 0; i < 10; i++) {
				log.info("test1 - {}", i);
			}
		}
	}
	
	// 修饰一个静态方法
	public static synchronized void test4 () {
		synchronized (lock) {
			for (int i = 0; i < 10; i++) {
				log.info("test1 - {}", i);
			}
		}
	}
	
	public static void main(String[] args) {
		final SynchronizedExample example = new SynchronizedExample();
		final SynchronizedExample example2 = new SynchronizedExample();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Runnable() {
			@Override
			public void run() {
				example.test4();
			}
		});
		
		exec.execute(new Runnable() {
			@Override
			public void run() {
				example2.test4();
			}
		});
	}
	
}
