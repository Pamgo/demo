package com.example.concurrency.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 因为CyclicBarrier设置了拦截线程的数量是2，所有必须等待代码中的第一个线程和线程A都执行完之后，
 * 才会继续执行主线程，然后输出2。
 * @author OKali
 *
 */
public class CyclicBarrierTest2 {

	static CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
		@Override
		public void run() {
			System.out.println(3);
		}
	});
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println(1);
			}
		}).start();
		
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.err.println(2);
	}
	
}
