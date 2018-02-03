package com.example.pattern.proxy.jh;

import com.example.pattern.proxy.Car;

/**
 * 使用聚合方式代理
 * @author OKali
 *
 */
public class Testjh {

	public static void main(String[] args) {
		Car car = new Car();
		CarTimeProxy carTimeProxy = new CarTimeProxy(car);
		CarLogProxy carLogProxy = new CarLogProxy(carTimeProxy);
		carLogProxy.move();
		
	}
}
