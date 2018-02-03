package com.example.pattern.factory.in;

public class MCFactory implements Fac {

	@Override
	public Boy createBoy() {
		
		return new MCBoy();
	}

	@Override
	public Girl createGirl() {
		
		return new MCGirl();
	}

}
