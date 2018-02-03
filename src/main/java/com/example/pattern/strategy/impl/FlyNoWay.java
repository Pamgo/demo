package com.example.pattern.strategy.impl;

import com.example.pattern.strategy.FlyingStragety;

public class FlyNoWay implements FlyingStragety {

	@Override
	public void performfly() {
		System.out.println("不会飞行");
	}

}
