package com.okali.netty.nio;

/**
 * NIO创建时间服务器
 * @author OKali
 *
 */
public class TimeServer {

	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(port);
		}
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		new Thread(timeServer,"NIO-MultiplerTimeServer-001").start();;
	}
}
