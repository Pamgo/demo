package com.example.pattern.proxy;

/**
 * 组合方式
 * @author OKali
 *
 */
public class Car2 implements Moveable {
	
	private Car car;
	
	public Car2(Car car) {
		this.car = car;
	}

	@Override
	public void move() {
		long startTime = System.currentTimeMillis();
		System.out.println("汽车开始行驶。。。。");
		car.move();
		long endTime = System.currentTimeMillis();
		System.out.println("汽车行驶结束，行驶时间:" + (endTime - startTime) + "毫秒");
	}

}
