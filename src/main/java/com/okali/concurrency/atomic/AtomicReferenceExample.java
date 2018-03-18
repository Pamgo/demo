package com.okali.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

import com.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe("线程安全")
public class AtomicReferenceExample {

	private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);
	
	public static void main(String[] args) {
		count.compareAndSet(0, 2);  // 如果期望值expect = 0 ，则count = 2
		count.compareAndSet(0, 1);  // no
		count.compareAndSet(1, 3);  // no
		count.compareAndSet(2, 4);  // count = 4
		count.compareAndSet(3, 5);  // no
		log.info("count:{}", count);
	}
}
