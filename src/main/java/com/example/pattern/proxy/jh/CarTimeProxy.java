package com.example.pattern.proxy.jh;

import com.example.pattern.proxy.Moveable;

public class CarTimeProxy implements Moveable {
	
	private Moveable m;
	
	public CarTimeProxy(Moveable m) {
		this.m = m;
	}

	@Override
	public void move() {
		System.out.println("时间开始。。。");
		m.move();
		System.out.println("时间结束。。。");
	}

}
