package com.example.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.example.pattern.proxy.Car;
import com.example.pattern.proxy.Moveable;

public class JdkproxyTest {

	public static void main(String[] args) {
		
		Car car = new Car(); //实现了Moveable接口
		InvocationHandler h = new TimerHandler(car);
		Class<?> cls = car.getClass();
		
		/**
		 * loader: 类加载器
		 * interfaces: 实现接口
		 * h: invocationHandler
		 */
		Moveable moveable = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), 
				cls.getInterfaces(), h);
		moveable.move();
	}
}
