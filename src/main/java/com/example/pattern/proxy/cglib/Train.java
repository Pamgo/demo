package com.example.pattern.proxy.cglib;

import java.util.Random;

public class Train {

	public void move() {
		try {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println("火车行驶中....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
