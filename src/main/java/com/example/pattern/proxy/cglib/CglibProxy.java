package com.example.pattern.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib动态代理
 * @author OKali
 *
 */
public class CglibProxy implements MethodInterceptor {
	
	private Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class clazz) {
		//设置创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	/**
	 * target: 目标类实例
	 * method: 目标方法的反射对象
	 * args:   方法参数
	 * proxy:  代理类的实例
	 */
	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		long startTime = System.currentTimeMillis();
		System.out.println("火车开始行驶。。。。");
		proxy.invokeSuper(target, args);
		long endTime = System.currentTimeMillis();
		System.out.println("火车行驶结束，行驶时间:" + (endTime - startTime) + "毫秒");
		return null;
	}

}
