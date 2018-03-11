package com.example.concurrency.st;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 打印堆栈信息
 * @author OKali
 *
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	public void execute(Runnable task) {
		super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
	}
	
	private Exception clientTrace() {
		return new Exception("Client stack trace");
	}

	private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
		return new Runnable() {
			
			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception ex) {
					clientStack.printStackTrace();
					throw ex;
				}
			}
		};
	}
	
	public static void main(String[] args) {
		ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, 
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		/**
		 * 错误堆栈中可以看到是在哪里提交的任务
		 */
		for (int i = 0; i < 5; i++) {
			pools.execute(new DivTsk(100, i));
		}
	}
}
