package com.example.rpc;

public class HelloServiceImpl implements HelloService {

	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "hello";
	}

	@Override
	public String hello(String name) {
		// TODO Auto-generated method stub
		return "hello," + name;
	}

}
