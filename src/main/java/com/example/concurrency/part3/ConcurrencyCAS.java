package com.example.concurrency.part3;

public class ConcurrencyCAS {
	
	private volatile int index = 0;
	
	public int getIvalue() {
		return index;
	}
	
	public synchronized int count() {
		return index++;
	}
	
	public static void main(String[] args) throws InterruptedException {
		final ConcurrencyCAS cas = new ConcurrencyCAS();
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(cas.count());
				}
			});
			t.start();
			//t.join();
		}
		
		for (int i = 0; i < 33; i++) {
			System.out.println(Thread.currentThread().getName()+":"+cas.getIvalue());
		}
		
	}

}
