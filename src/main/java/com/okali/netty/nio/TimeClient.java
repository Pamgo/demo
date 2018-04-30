package com.okali.netty.nio;

/**
 * 测试
 * @author OKali
 *
 */
public class TimeClient {

	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		
		new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();;
	}
}
