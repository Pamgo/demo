package com.example.pattern.proxy;

import java.util.Random;

public class Car implements Moveable {

	@Override
	public void move() {
//		long startTime = System.currentTimeMillis();
//		System.out.println("汽车开始行驶。。。。");
		
		try {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println("汽车行驶中....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		long endTime = System.currentTimeMillis();
//		System.out.println("汽车行驶结束，行驶时间:" + (endTime - startTime) + "毫秒");
		
	}

}
