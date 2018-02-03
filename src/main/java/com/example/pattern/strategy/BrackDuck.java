package com.example.pattern.strategy;

import com.example.pattern.strategy.impl.FlyingWithWin;

public class BrackDuck extends Duck {
	
	public BrackDuck() {
		super();
		super.setFlyingStragety(new FlyingWithWin());
	}


	@Override
	public void display() {
		System.out.println("黑色");
	}

	
}
