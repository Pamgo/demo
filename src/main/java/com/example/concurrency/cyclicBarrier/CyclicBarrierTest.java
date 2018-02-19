package com.example.concurrency.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier : 其参数表示屏障拦截的线程数
 * 量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞. 等到所有线程都到达了屏障，则执行
 * 
 * @author OKali
 *
 */
public class CyclicBarrierTest {

	static CyclicBarrier barrier = new CyclicBarrier(2);

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
		System.out.println(2);
	}

	// 主线程和子线程的调度是由CPU决定的，两个线程都有可能先执行，所以会产生两种
	// 输出

}