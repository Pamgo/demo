package com.example.pattern.strategy;

public abstract class Duck {

	public void speek() {
		System.out.println("gagaga~~~~");
	}
	
	public abstract void display();
	 
	//组合模式
	private FlyingStragety flyingStragety;
	
	public void setFlyingStragety(FlyingStragety flyingStragety) {
		this.flyingStragety = flyingStragety;
	}
	
	public void fly() {
		flyingStragety.performfly();
	}
}
