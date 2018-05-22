package com.okali.netty.aio;


/**
 * AIO时间服务器服务端
 * @author OKali
 *
 */
public class TimeServer {

	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		
		// 启动AIO时间服务
		AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
		new Thread(timeServerHandler,"AIO-AsyncTimeServer").start();
	}
}
