package com.example.concurrency.st;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import scala.util.Random;

/**
 * CyclicBarrier是另一种多线程并发控制使用工具(与CountdownLatch类似)
 * @author OKali
 * 描述：比CountDownLatch略微大一些，CyclicBarrier可以接收一个参数作为barrierAction。所谓barrierAction
 * 就是当计数器一次计数完成后，系统会执行的动作。
 */
public class CyclicBarrierDemo {
	
	public static void main(String[] args) {
		final int N = 10;
		Thread[] allSoldier = new Thread[N];
		boolean flag = false;
		CyclicBarrier barrier = new CyclicBarrier(N, new BarrierRun(flag, N));
		//设置屏蔽点，主要是为了执行这个方法
		System.out.println("集合队伍！");
		for (int i = 0; i < N; ++ i) {
			System.out.println("士兵 " +i+ " 报道！");
			allSoldier[i] = new Thread(new Soldier(barrier, "士兵  " + i));
			allSoldier[i].start();
		}
	}

	public static class Soldier implements Runnable {
		
		private String soldier;
		
		private CyclicBarrier cyclic;
		
		public Soldier(CyclicBarrier cyclicBarrier, String soldierName) {
			this.cyclic = cyclicBarrier;
			this.soldier = soldierName;
		}
		
		@Override
		public void run() {
			try {
				// 等待所有士兵到齐
				cyclic.await();
				doWork();
				// 等待所有士兵完成工作
				cyclic.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			} 
			
		}
		
		void doWork() {
			try {
				Thread.sleep(Math.abs(new Random().nextInt() % 10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("任务完成!!");
		}
		
	}
	
	public static class BarrierRun implements Runnable {
		boolean flag;
		int N;
		
		public BarrierRun(boolean flag, int N) {
			this.flag = flag;
			this.N = N;
		}

		@Override
		public void run() {
			if (flag) {
				System.out.println("司令:[士兵" + N + "个，任务完成！");
			} else {
				System.out.println("司令:[士兵" + N + "个，集合完毕！");
				flag = true;
			}
		}
		
	}
}
