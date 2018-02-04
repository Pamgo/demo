package com.example.concurrency.ThreadPool;

/**
 * 业务线程
 * @author OKali
 *
 */
public class ThreadForPools implements Runnable {
	
	private Integer index;
	
	public ThreadForPools(Integer index) {
		this.index = index;
	}

	@Override
	public void run() {
		/*
		 * 业务。。。。。。省略
		 */
		try {
			System.out.println("开始处理线程！！！");
			Thread.sleep(index*100*2);
			System.out.println("我的线程标识时：" +Thread.currentThread().getName() +"->"+ this.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
