package com.example.concurrency.threadPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程的优先级不能作为程序正确性的依赖
 * 从这个结果可以看出，操作系统并没有理会我们设置的线程优先级
 * @author OKali
 *
 */
public class Priority {
	
	private volatile static boolean notStart = true;
	
	private volatile static boolean notEnd = true;
	
	public static void main(String[] args) throws InterruptedException {
		List<Job> jobs = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
			Job job = new Job(priority);
			jobs.add(job);
			Thread thread = new Thread(job, "Thread:" + i);
			thread.setPriority(priority);
			thread.start();
			System.out.print(thread.getName());
		} // 使用循环启动10个线程
		notStart = false; // 十个线程启动完成后设置
		TimeUnit.SECONDS.sleep(10); // main线程沉睡10s，使得10个小线程执行结束
		notEnd = false;
		for (Job job : jobs) {
			System.out.println("Job Priority : " + job.priority + ", Count : " + job.jobCount);
		}
		System.out.println(Thread.currentThread().getPriority());
	}

	
	static class Job implements Runnable {
		
		private int priority;
		
		private long jobCount;
		
		public Job(int priority) {
			this.priority = priority;
		}

		@Override
		public void run() {
			while (notStart) {
				Thread.yield(); // 这里确保main线程将10个小线程启动成功
				System.out.println("notStart");
			}
			while (notEnd) {
				Thread.yield(); // 这里让出CPU资源，使得10个线程自由竞争。
				jobCount ++;    // 记录竞争状态，反映线程的优先级
				System.out.println("notEnd");
			}
		}
		
	}
}
