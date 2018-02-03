package com.example.pattern.strategy.impl;

import com.example.pattern.strategy.FlyingStragety;
 
public class FlyingWithWin implements FlyingStragety {

	@Override
	public void performfly() {
		System.out.println("用翅膀飞行，展翅高飞");
	}

}
