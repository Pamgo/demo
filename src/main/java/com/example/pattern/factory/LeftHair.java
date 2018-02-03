package com.example.pattern.factory;

public class LeftHair implements HairInterface {

	@Override
	public void draw() {
		System.out.println("===向左偏===");
	}

}
