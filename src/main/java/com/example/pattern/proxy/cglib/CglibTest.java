package com.example.pattern.proxy.cglib;

public class CglibTest {

	public static void main(String[] args) {
		CglibProxy cglibProxy = new CglibProxy();
		Train train = (Train) cglibProxy.getProxy(Train.class);
		train.move();
	}
}
