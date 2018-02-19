package com.example.concurrency.ThreadPool.diy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 定义默认线程池
 * @author OKali
 *
 * @param <Job>
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
	
	// 线程池最大限制数
	private static final int MAX_WORKER_NUMBERS     = 10;
	// 线程池默认的数量
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	// 线程池最小数量
	private static final int MIN_WORKER_NUMBERS     = 1;
	// 这是一个工作列表，将会向里面插入工作
	private final LinkedList<Job> jobs = new LinkedList<>();
	// 工作者列表
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	// 工作者线程数量
	private int workerNum = DEFAULT_WORKER_NUMBERS;
	// 线程编号生成
	private AtomicLong threadNum = new AtomicLong();
	
	public DefaultThreadPool() {
		initializeWorkers(DEFAULT_WORKER_NUMBERS);
	}
	
	public DefaultThreadPool(int num) {
		
		workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS 
				? MIN_WORKER_NUMBERS : num; 
		
		initializeWorkers(workerNum);
	}	
	/**
	 * 初始化工作线程
	 * @param num
	 */
	private void initializeWorkers(int num) {
		for (int i = 0; i < num; i++) {
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker, "Thread-worker-" + threadNum.incrementAndGet());
			thread.start();
		}
	}

	@Override
	public void execute(Job job) {
		if (job != null) {
			synchronized (jobs) {
				jobs.addLast(job);
				jobs.notify();      // 从队列中随机唤醒一个线程，随着大量的任务提交，会有更多的线程被唤醒
			}
		}
	}

	@Override
	public void shutdown() {
		for (Worker worker : workers) {
			worker.shutdonw();
		}
	}

	@Override
	public void addWorkers(int num) {
		synchronized (jobs) {
			// 限制新增的worker数量不能超过最大值
			if (num + this.workerNum > MAX_WORKER_NUMBERS) {
				num = MAX_WORKER_NUMBERS - this.workerNum;
			}
			initializeWorkers(num);
			this.workerNum += num;
		}
	}

	@Override
	public void removeWorker(int num) {
		synchronized (jobs) {
			if (num >= this.workerNum) {
				throw new IllegalArgumentException("beyond workNum");
			}
			// 按照给定的数量停止Worker
			int count = 0;
			while (count < num) {
				Worker worker = workers.get(count);
				if (workers.remove(worker)) {
					worker.shutdonw();
					count ++;
				}
			}
			this.workerNum -= count;
		}
	}

	@Override
	public int getJobSize() {
		return jobs.size();
	}
	
	// 工作者，负责消费任务
	class Worker implements Runnable {

		// 是否工作
		private volatile boolean running = true;
		
		@Override
		public void run() {
			while(running) {
				Job job = null;
				synchronized (jobs) {
					// 如果工作者列表为空的，那么就wait
					while (jobs.isEmpty()) {
						try {
							jobs.wait();    // 队列中线程为空时，需要等待
						} catch (InterruptedException e) {
							// 感知到外部对workerThread的中断操作，返回
							Thread.currentThread().interrupt();	
							return;
						}
					}
					// 取出一个job
					job = jobs.removeFirst();
				}
				if (job != null) {
					try {
						job.run();
					} catch (Exception ex) {} // 忽略job执行的exception
				}
			}
		}
		
		// 停止
		public void shutdonw() {
			running = false;
		}
		
	}

}
