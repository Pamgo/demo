package com.example.concurrency.st;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


/**
 * 展示Fork/Join框架的使用
 * @author OKali
 * ForkJoin线程池使用一个无锁的栈来管理空闲线程。如果一个工作线程暂时取不到可用的任务，则可能挂起，挂起的线程
 * 将会被压入由线程池维护的栈中。将来有任务可用时，再从栈中唤醒这些线程。
 */
public class CountTask extends RecursiveTask<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10000;
	private long start;
	private long end;
	
	public CountTask(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		Long sum = 0L;
		boolean canCompute = (end - start) < THRESHOLD;
		if (canCompute) {
			for (long i = start; i <= end; i++) {
				sum += 1;
			}
		} else {  // 分解成100个小任务
			java.util.List<CountTask> subTasks = new ArrayList<>();
			long step = (start + end) /100;
			long pos = start;
			for (int i = 0; i < 100; i++) {
				long lastOne = pos + step;
				CountTask subTask = new CountTask(pos, lastOne);
				pos += step + 1;
				
				subTasks.add(subTask);
				subTask.fork(); // 分组计算
			}
			
			for (CountTask t : subTasks) {
				sum += t.join();  // 组合
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(); // 建立ForkJoinPool线程池
		CountTask task = new CountTask(0, 200000L);
		ForkJoinTask<Long> result = forkJoinPool.submit(task); // 将计算任务提交给线程池
		try {
			Long res = result.get();
			System.out.println("sum=" + res);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
