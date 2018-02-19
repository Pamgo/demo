package com.example.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 利用分割方式计算
 * @author OKali
 *
 */
public class CountTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 2; // 阈值
	private int start;
	private int end;
	
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		// 如果任务足够小，则直接计算任务
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int middle  = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);
			// 执行子任务
			leftTask.fork();
			rightTask.fork();
			
			// 等待子任务执行完，并得到其结果
			int leftResult = leftTask.join();
			int rightResult = rightTask.join();
			
			// 合并子任务
			sum = leftResult + rightResult;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// 生成一个计算任务，负责计算1+2+3+4
		CountTask countTask = new CountTask(1, 8);
		// 执行一个任务
		Future<Integer> result = forkJoinPool.submit(countTask);
		try {
			System.out.println(result.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
