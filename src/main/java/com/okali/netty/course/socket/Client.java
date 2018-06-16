package com.okali.netty.course.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

@Slf4j
public class Client {
	
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 8000;
	private static final int SLEEP_TIME = 5000;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		final Socket socket = new Socket(HOST, PORT);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				log.info("客户端启动成功！");
				while(true) {
					try {
						String message = "hello world";
						log.info("客户端发送数据：" + message);
						socket.getOutputStream().write(message.getBytes());
					} catch (IOException e) {
						log.error("写数据出错", e);
					}
					sleep();
				}
			}
		}).start();
	}
	
	private static void sleep() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
