package com.example.concurrency.cyclicBarrier;

import java.util.Map.Entry;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 银行流水处理服务类
 * @author OKali
 *
 */
public class BankWaterService implements Runnable {

	/**
	 * 创建4个屏障，处理完之后执行当前类的run方法
	 */
	private CyclicBarrier c = new CyclicBarrier(4, this);
	
	/**
	 * 假设只有4个sheet，所以只有启动4个线程
	 */
	private Executor excutor = Executors.newFixedThreadPool(4);
	
	private AtomicInteger threadCount = new AtomicInteger(1);
	
	/**
	 * 保持每个sheet计算出的银流结果
	 */
	private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();
	
	private void count() {
		for (int i = 0; i < 4; i++) {
			excutor.execute(new Runnable() {
				
				@Override
				public void run() {
					// 计算每一个sheet的结果（忽略该处代码）
					sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
					try {
						// 银流计算完插入一个屏障
						c.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	
	@Override
	public void run() {
		int result = 0;
		// 汇总每个sheet计算出结果
		for (Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
			result += sheet.getValue();
		}
		// 将结果输出
		sheetBankWaterCount.put("result", result);
		System.out.println("result :" + result);
	}

	public static void main(String[] args) {
		BankWaterService bankWaterService = new BankWaterService();
		bankWaterService.count();
	}
}
