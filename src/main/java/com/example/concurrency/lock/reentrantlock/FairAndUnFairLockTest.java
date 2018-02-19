package com.example.concurrency.lock.reentrantlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class FairAndUnFairLockTest {

	private static Lock fairLock = new ReentrantLock2(true);
	private static Lock unFairLock = new ReentrantLock2(false);
	
	@Test
	public void fair() {
		testLock(fairLock);
	}
	
	@Test
	public void unfair() {
		testLock(unFairLock);
	}
	
	private void testLock(Lock lock) {
		for (int i = 0; i < 4; i++) {
			Job job = new Job(lock);
			job.setDaemon(true);
			job.start();
		}
	}
	
	private static class Job extends Thread {
		
		private Lock lock;
		
		public Job(Lock lock) {
			this.lock = lock;
		}
		
		@Override
		public void run() {
			System.out.print("threadName: "+Thread.currentThread().getId());
			System.out.print("waiting Threads:" + ((ReentrantLock2)lock).getQueueThreads().toString());
			System.out.println();
			
			System.out.print("threadName: "+Thread.currentThread().getId());
			System.out.print("waiting Threads:" + ((ReentrantLock2)lock).getQueueThreads().toString());
			System.out.println();
		}
	}
	
	private static class ReentrantLock2 extends ReentrantLock {
		public ReentrantLock2(boolean fair) {
			super(fair);
		}
		
		public Collection<Thread> getQueueThreads() {
			List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
			Collections.reverse(arrayList);
			return arrayList;
		}
	}
}
