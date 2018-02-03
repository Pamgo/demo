package com.example.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理
 * @author OKali
 *
 */
public class TimerHandler implements InvocationHandler {
	
	private Object target;
	
	public TimerHandler(Object target) {
		this.target = target;
	}

	/**
	 * proxy : 被代理的对象
	 * method: 被代理对象的方法
	 * args: 方法的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶。。。。");
		method.invoke(target);
		long endTime = System.currentTimeMillis();
		System.out.println("汽车行驶结束，行驶时间:" + (endTime - startTime) + "毫秒");
		return null;
	}

}
