package com.example.pattern.strategy;

public class DuckTest {

	public static void main(String[] args) {
		Duck duck = null;
		duck = new YellowDuck();
		//duck = new BrackDuck();
		
		duck.display();
		duck.speek();
		duck.fly();
	}
}
