package com.okali.concurrency.threadlocal;

/**
 * 线程封闭(ThreadLocal)
 * @author OKali
 *
 */
public class RequestHolder {

	private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();
	
	public static void add(Long id) {
		requestHolder.set(id);
	}
	
	public static Long getId() {
		return requestHolder.get();
	}
	
	public static void remove() {
		requestHolder.remove();
	}
}
