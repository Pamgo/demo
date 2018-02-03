package com.example.pattern.strategy;

import com.example.pattern.strategy.impl.FlyNoWay;

public class YellowDuck extends Duck {
	
	public YellowDuck() {
		super();
		super.setFlyingStragety(new FlyNoWay());
	}

	@Override
	public void display() {
		System.out.println("黄色");
	}

}
