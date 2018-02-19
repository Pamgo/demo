package com.example.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义独占锁
 * @author OKali
 *
 */
public class Mutex implements Lock{
	
	// 静态内部类，自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer {
		
		// 是否处于占用状态
		@Override
		protected boolean isHeldExclusively() {
			return this.getState() == 1;
		}
		
		// 当前状态为0 的时候获取锁
		@Override
		protected boolean tryAcquire(int acquire) {
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		// 释放锁，将状态设置为0
		@Override
		protected boolean tryRelease(int release) {
			if (getState() == 0) throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		
		Condition newCondition() {
			return new ConditionObject();
		}
	}

	private final Sync sync = new Sync();
	
	@Override
	public void lock() {
		sync.acquire(1);
	}
	
	public boolean isLocked() {
		return sync.isHeldExclusively();
	}
	
	public boolean hasQueueThreads() {
		return sync.hasQueuedThreads();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

}
