package com.example.pattern.factory;

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//		HairInterface hairInterface = HairFactory.drawHairDIY("left");
//		hairInterface.draw();
		
//		HairInterface hairInterface = HairFactory.drawHairDIYforClassName("com.example.factory.LeftHair");
//		hairInterface.draw();
	
		HairInterface hairInterface = HairFactory.drawHairDIYforKey("left");
		hairInterface.draw();
	}
}
