package com.okali.concurrency.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * ArrayList
 * @author OKali
 *
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {

	// 请求总数
	public static int clientTotal = 5000;
	// 并发数
	public static int threadTotal = 200;
	
	private static List<Integer> list = new CopyOnWriteArrayList<>();
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch latch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						update(count);
						semaphore.release();
					} catch (InterruptedException e) {
						log.error("exception", e);
					}
					latch.countDown();  // 每一个请求减一
				}
			});
		}
		
		latch.await(); // latch为0的时候输出结果
		exec.shutdown();
		log.info("size:{}",list.size());
	}
	
	private static void update (int i) {
		list.add(i);
		
		/**
		 *  public boolean add(E e) {
		        final ReentrantLock lock = this.lock;
		        lock.lock();
		        try {
		            Object[] elements = getArray();
		            int len = elements.length;
		            Object[] newElements = Arrays.copyOf(elements, len + 1);
		            newElements[len] = e;
		            setArray(newElements);
		            return true;
		        } finally {
		            lock.unlock();
		        }
	    	}
		 */
	}
}
