package com.example.pattern.factory.in;

public class HNFactory implements Fac {

	@Override
	public Boy createBoy() {
		
		return new HNBoy();
	}

	@Override
	public Girl createGirl() {
		
		return new HNGirl();
	}

}
