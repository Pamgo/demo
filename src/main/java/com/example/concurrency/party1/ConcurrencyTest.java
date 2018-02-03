package com.example.concurrency.party1;

/**
 * 比较串行和并行累加操作时间
 * @author OKali
 *
 */
public class ConcurrencyTest {

	private static final long count = 1000L; //不断调大数值比较并行和串行的效率开销
	
	public static void main(String[] args) throws InterruptedException {
		concurrency();
		serial();
	}
	
	/**
	 * 并行
	 * @throws InterruptedException 
	 */
	private static void concurrency() throws InterruptedException {
		long start = System.currentTimeMillis();
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int a = 0;
				for (long i = 0; i < count; i++) {
					a += 5;
				}
			}
		});
		
		thread.start();
		
		int b = 0;
		for (long i = 0; i < count ; i++) {
			b--;
		}
		
		long time = System.currentTimeMillis();
		thread.join();   // 父线程等待thread执行完再执行
		System.out.println("concurrency : " + (time-start) + "ms,b=" + b );
	}
	
	/**
	 * 串行
	 */
	private static void serial() {
		long start = System.currentTimeMillis();
		int a = 0;
		for (long i = 0; i < count ; i++) {
			a += 5;
		}
		int b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis();
		System.out.println("Serial:" + (time-start) + "ms,b="+b+",a="+a);
	}
}
