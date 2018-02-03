package com.example.rpc;

public class RpcConsumer {

	public static void main(String[] args) throws Exception {
		HelloService helloService = RpcFramework.refer(HelloService.class, "127.0.0.1", 9000);
		String result = helloService.hello("alison");
		System.out.println(result);
	}
}
