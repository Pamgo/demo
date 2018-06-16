package com.okali.netty.course.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler {
	
	private static final int MAX_DATA_LEN = 1024;

	private Socket socket;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void start() {
		log.info("新客户端接入");
		new Thread(new Runnable() {
			@Override
			public void run() {
				doStart();
			}
		}).start();
	}

	private void doStart() {
		try {
			InputStream inputStream = socket.getInputStream();
			while (true) {
				byte[] data = new byte[MAX_DATA_LEN];
				int len;
				while ((len = inputStream.read(data)) !=-1) {
					String messge = new String(data, 0, len);
					log.info("客户端传来的消息：" + messge);
					socket.getOutputStream().write(data);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
