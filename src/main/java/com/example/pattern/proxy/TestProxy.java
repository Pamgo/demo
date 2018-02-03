package com.example.pattern.proxy;

public class TestProxy {
	
	public static void main(String[] args) {
		/*Car car = new Car();
		car.move();*/
		//使用继承方式
//		Car1 car1 = new Car1();
//		car1.move();
		
		//使用聚合方式
		Car car = new Car();
		Moveable moveable = new Car2(car);
		moveable.move();
	}

}
