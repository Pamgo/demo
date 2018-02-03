package com.example.rpc;

public class RpcProvider {

	public static void main(String[] args) throws Exception {
		HelloService helloService = new HelloServiceImpl();
		RpcFramework.export(helloService, HelloService.class, 9000);
	}
}
