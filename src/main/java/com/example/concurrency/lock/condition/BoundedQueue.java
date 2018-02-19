package com.example.concurrency.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列
 * @author OKali
 *
 */
public class BoundedQueue<T> {

	private Object[] items;
	// 添加的下标，删除的下标和数组当前数量
	private int addIndex,removeIndex,count;
	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();
	
	public BoundedQueue(int size) {
		items = new Object[size];
		this.addIndex = 0;
		this.count = 0;
		this.removeIndex  = 0;
	}
	
	// 添加一个元素，如果数组满，则添加线程进入等待状态，直到有空位
	public void add(T t) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {  // 如果有界队列满，则入队列等待
				notFull.await();
			}
			items[addIndex] = t;
			if (++addIndex == items.length) {
				addIndex = 0;
			}
			++count;
			notEmpty.signal(); // 唤醒继续可以删除
		} finally {
			lock.unlock();
		}
	}
	
	// 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新增元素
	@SuppressWarnings("unchecked")
	public T remove() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			Object x = items[removeIndex];
			if (++removeIndex == items.length) {
				removeIndex = 0;
			}
			--count;
			notFull.signal(); // 唤醒继续可以添加
			
			return (T) x;
		} finally {
			lock.unlock();
		}
	}
	
}
